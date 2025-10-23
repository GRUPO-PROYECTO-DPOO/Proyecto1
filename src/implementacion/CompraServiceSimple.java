package implementacion;
import app.*;
import escenariosyventas.*;
import enums.*;
import run.*;
import transacciones.*;
import usuarios.*;
import java.time.*;
import java.util.*;
import dtos.*;

public class CompraServiceSimple implements CompraService {
	private final TiqueteraContext ctx;
	public CompraServiceSimple(TiqueteraContext ctx) {this.ctx = ctx;}
	
	@Override public Cotizacion cotizar(CmdCompra cmd) {
		Localidad l = ctx.findLocalidad(cmd.getIdEvento(), cmd.getIdLocalidad()).orElseThrow(()
				-> new IllegalArgumentException("Localidad no existe"));
		Evento e = ctx.findEvento(cmd.getIdEvento()).orElseThrow(() -> new IllegalArgumentException("Evento no existe"));
		
		if (!ctx.cargoPorTipo.containsKey(e.getTipo())) 
			throw new IllegalStateException("Cargo de servicio para " + e.getTipo() + " no configurado");
		if (ctx.cuotaEmisionFija == null) throw new IllegalStateException("Evento no existe");
		
		double unit = l.precioVigente(LocalDateTime.now());
		double base = unit * Math.max(1, cmd.getCantidad());
		double cargo =  base * cargoServicio(e.getTipo());
		double cuota = ctx.cuotaEmisionFija;
		return new Cotizacion(base, cargo, cuota);	
	}
	
	@Override public Recibo comprar(CmdCompra cmd) {
		Cliente c = (Cliente) ctx.findUsuario(cmd.getLoginCliente()).orElseThrow(
				() -> new IllegalArgumentException("Cliente no existe"));
		
		Cotizacion cot = cotizar(cmd);
		int n = Math.max(1,  cmd.getCantidad());
		if (n > 10) throw new IllegalStateException("Tope por transaccion excedido");
		if (!c.getSaldoVirtual().puedePagar(cot.getTotal())) throw new IllegalStateException("Saldo insuficiente");
		
		for (int i=0; i<n; i++) {
			Localidad l = ctx.findLocalidad(cmd.getIdEvento(), cmd.getIdLocalidad()).get();
			var t = l.emitirTiquete(c, l.isNumerada()?cmd.getAsiento():null);
			t.aplicarCuotaEmisionFija(ctx.cuotaEmisionFija);
			t.registrarPago(cot.getTotal()/n);
			String nuevoId = UUID.randomUUID().toString();
			t.asignarId(nuevoId);
			ctx.tiquetes.put(nuevoId, t);
		}
		
		TransaccionCompra tx = new TransaccionCompra(UUID.randomUUID().toString(), LocalDate.now(), c);
		Evento e = ctx.findEvento(cmd.getIdEvento()).get();
		LineaTransaccion linea = new LineaTransaccion(TipoItem.TIQUETE, cmd.getIdLocalidad(), n, cot.getPrecioBase()/n,
				0.0, cargoServicio(e.getTipo()), ctx.cuotaEmisionFija);
		linea.calcularSubtotal();
		tx.agregarLinea(linea);
		tx.totalizar();
		ctx.transacciones.add(tx);
		c.debitarSaldo(cot.getTotal());
		return tx.aRecibo();
	}
	@Override public double cargoServicio(TipoEvento tipo) {
		Double v = ctx.cargoPorTipo.get(tipo);
		if (v == null) throw new IllegalStateException("Cargo de servicio para " + tipo + " no configurado");
		return v;
	}
}
