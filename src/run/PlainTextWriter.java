package run;

import escenariosyventas.*;
import tiquetes.*;
import transacciones.*;
import usuarios.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.*;                 
import dtos.LineaTransaccion;

public class PlainTextWriter {

  public void writeSnapshot(TiqueteraContext ctx, String fileName) {
    Path projectRoot = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
    Path dataDir = projectRoot.resolve("data");
    writeSnapshot(ctx, dataDir, fileName);
  }


  public void writeSnapshot(TiqueteraContext ctx, Path folder, String fileName) {
    try {
      Files.createDirectories(folder);
      Path out = folder.resolve(fileName);

      StringBuilder sb = new StringBuilder(16_384);
      var dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

      // Encabezado
      sb.append("Boletamaster Archivo\n");
      sb.append("Archivo: ").append(out.toAbsolutePath()).append('\n');
      sb.append("----\n\n");

      // Parámetros
      sb.append("[PARAMETROS]\n");
      sb.append("cuotaEmisionFija=")
        .append(ctx.cuotaEmisionFija==null?"<no-configurada>":ctx.cuotaEmisionFija)
        .append('\n');
      sb.append("cargoPorTipo:\n");
      ctx.cargoPorTipo.entrySet().stream()
        .sorted(Comparator.comparing(e->e.getKey().name()))
        .forEach(e -> sb.append("  - ").append(e.getKey().name()).append(" = ").append(e.getValue()).append('\n'));
      sb.append('\n');

      // Usuarios
      sb.append("[USUARIOS] total=").append(ctx.usuarios.size()).append('\n');
      ctx.usuarios.values().stream()
        .sorted(Comparator.comparing(Usuario::getLogin))
        .forEach(u -> {
          String rol = (u.getRol()!=null) ? u.getRol().name() : "<null>";
          double saldo = (u instanceof Cliente c && c.getSaldoVirtual()!=null) ? c.getSaldoVirtual().consultarSaldo() : 0.0;
          sb.append("  - ").append(u.getLogin()).append(" | ").append(rol)
            .append(" | ").append(u.getNombre()).append(" | saldo=").append(saldo).append('\n');
        });
      sb.append('\n');

      //Venues
      sb.append("[VENUES] total=").append(ctx.venues.size()).append('\n');
      ctx.venues.values().stream()
        .sorted(Comparator.comparing(Venue::getId))
        .forEach(v -> sb.append("  - ").append(v.getId()).append(" | ").append(v.getNombre())
            .append(" | aprobado=").append(v.isAprobado()).append('\n'));
      sb.append('\n');

      // Eventos 
      sb.append("[EVENTOS] total=").append(ctx.eventos.size()).append('\n');
      ctx.eventos.values().stream()
        .sorted(Comparator.comparing(Evento::getId))
        .forEach(e -> sb.append("  - ").append(e.getId()).append(" | ").append(e.getNombre())
            .append(" | ").append(e.getTipo()).append(" | estado=").append(e.getEstado())
            .append(" | ").append(e.getFechaHora()!=null?dtf.format(e.getFechaHora()):"<sin-fecha>")
            .append(" | venue=").append(e.getVenue()!=null?e.getVenue().getNombre():"<null>").append('\n'));
      sb.append('\n');

      // Localidades
      sb.append("[LOCALIDADES] total=").append(ctx.localidades.size()).append('\n');
      ctx.localidades.values().stream()
        .sorted(Comparator.comparing(Localidad::getId))
        .forEach(l -> sb.append("  - ").append(l.getEvento().getId()).append("#").append(l.getId())
            .append(" | ").append(l.getNombre()).append(" | precioBase=").append(l.getPrecioBase())
            .append(" | numerada=").append(l.isNumerada()).append(" | cap=").append(l.getCapacidad()).append('\n'));
      sb.append('\n');

      // Tiquetes
      sb.append("[TIQUETES] total=").append(ctx.tiquetes.size()).append('\n');
      ctx.tiquetes.values().stream()
        .sorted(Comparator.comparing(Tiquete::getId))
        .forEach(t -> {
          String tipo = (t instanceof TiqueteNumerado) ? "NUMERADO" : "SIMPLE";
          String asiento = (t instanceof TiqueteNumerado n) ? n.getAsiento() : "-";
          String dueno = (t.getDueño()!=null) ? t.getDueño().getLogin() : "<null>";
          sb.append("  - ").append(t.getId()).append(" | ev=").append(t.getEvento().getId())
              .append(" | loc=").append(t.getLocalidad().getId())
              .append(" | ").append(tipo).append(" | asiento=").append(asiento)
              .append(" | estado=").append(t.getEstado())
              .append(" | base=").append(t.getPrecioBase())
              .append(" | pagado=").append(t.getPrecioPagado())
              .append(" | cuota=").append(t.getCuotaEmisionFija())
              .append(" | dueño=").append(dueno)
              .append('\n');
        });
      sb.append('\n');

      // Transacciones
      sb.append("[TRANSACCIONES] total=").append(ctx.transacciones.size()).append('\n');
      ctx.transacciones.stream()
        .sorted(Comparator.comparing(TransaccionCompra::getId))
        .forEach(tx -> {
          sb.append("  - tx=").append(tx.getId()).append(" | fecha=").append(tx.getFecha())
              .append(" | cliente=").append(tx.getCliente()!=null?tx.getCliente().getLogin():"<null>")
              .append(" | total=").append(tx.getTotal()).append('\n');
          for (LineaTransaccion li : tx.getLineas()){
            sb.append("      * ").append(li.getTipoItem()).append(" ref=").append(li.getRefItem())
                .append(" | cant=").append(li.getCantidad())
                .append(" | base=").append(li.getPrecioBase())
                .append(" | desc=").append(li.getDescuento())
                .append(" | cargoPct=").append(li.getCargoServicio())
                .append(" | cuotaFija=").append(li.getCuotaEmisionFija())
                .append(" | subtotal=").append(li.getSubtotal())
                .append('\n');
          }
        });
      sb.append('\n');
      
   // REPORTES 
   double totalCargos = 0.0;
   double totalCuotas = 0.0;
   long totalTickets = 0L;

   Map<String, Double> basePorOrg = new HashMap<>();
   Map<String, Long> vendidosPorOrg = new HashMap<>();

   for (TransaccionCompra tx : ctx.transacciones) {
     for (LineaTransaccion li : tx.getLineas()) {

       double baseNeta = li.getPrecioBase() - li.getDescuento();
       if (baseNeta < 0) baseNeta = 0.0;

       // Ganancias administrador
       double cargoLinea = baseNeta * li.getCargoServicio();
       double cuotasLinea = li.getCuotaEmisionFija() * li.getCantidad();
       totalCargos += cargoLinea;
       totalCuotas += cuotasLinea;
       totalTickets += li.getCantidad();

       // Estado financiero por organizador
       Localidad loc = ctx.localidades.values().stream()
           .filter(L -> Objects.equals(L.getId(), li.getRefItem()))
           .findFirst().orElse(null);
       if (loc != null && loc.getEvento() != null && loc.getEvento().getOrganizador() != null) {
         String loginOrg = loc.getEvento().getOrganizador().getLogin();
         basePorOrg.merge(loginOrg, baseNeta, Double::sum);
         vendidosPorOrg.merge(loginOrg, (long) li.getCantidad(), Long::sum);
       }
     }
   }

   sb.append("[REPORTES]\n");
   sb.append("GananciasGenerales\n");
   sb.append("  cargos=").append(totalCargos)
     .append(" | cuotas=").append(totalCuotas)
     .append(" | totalPlataforma=").append(totalCargos + totalCuotas)
     .append(" | tickets=").append(totalTickets)
     .append('\n');

   if (!basePorOrg.isEmpty()) {
     sb.append("EstadoFinanciero\n");
     basePorOrg.entrySet().stream()
       .sorted(Map.Entry.comparingByKey())
       .forEach(e -> {
         String org = e.getKey();
         double base = e.getValue();
         long vend = vendidosPorOrg.getOrDefault(org, 0L);
         double neto = base; 
         sb.append("  - ").append(org)
           .append(" | base=").append(base)
           .append(" | neto=").append(neto)
           .append(" | vendidos=").append(vend)
           .append('\n');
       });
   }
   sb.append('\n');
   
      // Escritura atomica
      byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
      Path tmp = Paths.get(out.toString() + ".tmp");
      Files.write(tmp, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
      Files.move(tmp, out, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);

      System.out.println("[ARCHIVO] Guardado en: " + out.toAbsolutePath());

    } catch (IOException e) {
      throw new RuntimeException("Error guardando archivo", e);
    }
  }
}

