package implementacion;
import app.*;
import run.TiqueteraContext;

public class TransferenciaServiceSimple implements TransferenciaService{
	private TiqueteraContext ctx;
	public TransferenciaServiceSimple(TiqueteraContext ctx) {this.ctx = ctx;}
	
	@Override public void reembolsarPorAdmin(String idEvento) {
		var e = ctx.findEvento(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento no existe"));
		ctx.tiquetesPorEvento(idEvento).forEach(t -> {double monto = t.montoReembolsoporAdmin();
		if (t.getDueño()!=null) {t.getDueño().getSaldoVirtual().acreditar(monto); t.marcarReembolsado();}
		});
		e.cancelarPorAdmin();
		
	}
	
	@Override public void reembolsarPorOrganizadorAutorizado(String idEvento) {
		var e = ctx.findEvento(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento no existe"));
		ctx.tiquetesPorEvento(idEvento).forEach(t -> {double monto = t.montoReembolsoPorOrganizador();
		if (t.getDueño()!=null) {t.getDueño().getSaldoVirtual().acreditar(monto); t.marcarReembolsado();}
		});
		e.cancelarPorOrganizadorAutorizado();
	}
}