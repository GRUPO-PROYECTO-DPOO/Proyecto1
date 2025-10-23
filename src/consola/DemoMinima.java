package consola;

import run.*;

import app.*;
import dtos.*;
import enums.*;
import escenariosyventas.*;
import usuarios.*;
import reportesyfiltros.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DemoMinima {
  public static void main(String[] args) {
   
    AppModule app = new AppModule()
        .setCargoServicio(TipoEvento.MUSICAL, 0.15)   // 15%
        .setCuotaEmisionFija(3.0);                    // 3.00

    TiqueteraContext ctx = app.getContext();

    //Usuarios
    Cliente cli = new Cliente("cami","123","Camila","cami@gmail.com");
    Organizador org = new Organizador("org1","123","Org Uno","org@gmail.com");
    Administrador admin = new Administrador("admin","123","Admin","a@gmail.com");
    ctx.usuarios.put(cli.getLogin(), cli);
    ctx.usuarios.put(org.getLogin(), org);
    ctx.usuarios.put(admin.getLogin(), admin);

    //Venue, proponer y aprobar
    VenueService vSrv = app.venues();
    CmdVenue cv = new CmdVenue();
    cv.setIdSolicitud("VEN-001");
    cv.setNombrePropuesto("Teatro Central");
    cv.setFecha(LocalDate.now());
    var sol = vSrv.proponerVenue(cv);
    vSrv.aprobarSolicitud(sol.getId());
    Venue venue = vSrv.listarVenues().get(0);
    System.out.println("[OK] Venue aprobado: " + venue.getNombre());

    //Evento y Localidad
    EventoService eSrv = app.evento();
    CmdCrearEvento ce = new CmdCrearEvento();
    ce.setIdEvento("EVT-001");
    ce.setNombre("Concierto Rock");
    ce.setFechaHora(LocalDateTime.now().plusDays(7));
    ce.setTipo(TipoEvento.MUSICAL);
    ce.setVenue(venue);
    Evento ev = eSrv.crearEvento(ce);
    ev.setOrganizador(org);

    CmdLocalidad cl = new CmdLocalidad();
    cl.setIdLocalidad("LOC-A");
    cl.setEvento(ev);
    cl.setNombre("Platea");
    cl.setPrecioBase(50.0);
    cl.setNumerada(true);
    cl.setCapacidad(3);
    eSrv.agregarLocalidad(ev.getId(), cl);
    int inventario = eSrv.generarTiquetes(ev.getId(), "LOC-A");
    System.out.println("[OK] Inventario generado: " + inventario + " tiquetes");

    // Compra
    cli.acreditarSaldo(100.0); // saldo inicial
    CompraService cSrv = app.compras();
    CmdCompra cmd = new CmdCompra();
    cmd.setLoginCliente("cami");
    cmd.setIdEvento(ev.getId());
    cmd.setIdLocalidad("LOC-A");
    cmd.setCantidad(1);
    cmd.setAsiento("1");
    var cot = cSrv.cotizar(cmd);
    System.out.printf("[COTIZACION] base=%.2f cargo=%.2f cuota=%.2f total=%.2f%n",
        cot.getPrecioBase(), cot.getCargoServicio(), cot.getCuotaEmisionFija(), cot.getTotal());
    var recibo = cSrv.comprar(cmd);
    System.out.println("[COMPRA] total=" + recibo.getTotal() + " | saldo cliente=" + cli.consultarSaldo());

    //Reportes 
    ReporteService rep = app.reportes();

    FiltroFinanzas ff = new FiltroFinanzas();
    ff.setDesde(null);
    ff.setHasta(null);
    EstadoFinanciero ef = rep.finanzasOrganizador("org1", ff);
    System.out.println("[FINANZAS ORG] ingresos base=" + ef.getIngresosBase() + " | tiquetesVendidos=" + ef.getTiquetesVendidos());

    FiltroGanancias fg = new FiltroGanancias();
    fg.setDesde(null);
    fg.setHasta(null);
    GananciasGenerales gg = rep.gananciasAdministrador(fg);
    System.out.println("[GANANCIAS ADMIN] cargos=" + gg.getTotalCargoServicio() +
        " | cuotas=" + gg.getTotalCuotaEmision() +
        " | total=" + gg.getTotalPlataforma());

    
    // Reembolso por cancelación de admin
    TransferenciaService tSrv = app.transferencias();
    tSrv.reembolsarPorAdmin(ev.getId());
    System.out.println("[REEMBOLSO ADMIN] saldo cliente=" + cli.consultarSaldo());

    //Guardar archivo plano 
    new PlainTextWriter().writeSnapshot(ctx, "archivo.txt");

    
    // Resumen
    System.out.println("\n== RESUMEN ==");
    System.out.println("Evento: " + ev.getId() + " | Estado: " + ev.getEstado());
    System.out.println("Transacciones registradas: " + ctx.transacciones.size());
    System.out.println("Tiquetes en memoria: " + ctx.tiquetes.size());
    System.out.println("Archivo guardado en: data/archivo.txt");
    System.out.println("== FIN DEMO ==");
  }
}