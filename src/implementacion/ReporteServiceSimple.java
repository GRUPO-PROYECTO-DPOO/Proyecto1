package implementacion;
import app.*;
import enums.*;
import reportesyfiltros.*;
import run.*;
import escenariosyventas.*;
import tiquetes.*;
import java.time.*;
import java.util.*;

public class ReporteServiceSimple implements ReporteService {
	private final TiqueteraContext ctx;
	public ReporteServiceSimple(TiqueteraContext ctx) {this.ctx = ctx;}
	
	@Override public EstadoFinanciero finanzasOrganizador(String loginOrg, FiltroFinanzas f) {
		EstadoFinanciero out = new EstadoFinanciero();
		out.setLoginOrganizador(loginOrg);
		
		for (Tiquete t: ctx.tiquetes.values()) {
			Evento e = t.getEvento();
			if (e == null) continue;
			if (!pasaRangoFecha(e, f.getDesde(), f.getHasta())) continue;
			if (f.getIdEvento() != null && !f.getIdEvento().equals(e.getId())) continue;
			if (loginOrg != null) {
				try {
					var org = e.getOrganizador();
					if (org == null || !Objects.equals(org.getLogin(), loginOrg)) continue;
				} catch (NoSuchMethodError | Exception ex) {
					continue;
				}
			}
			double ingresoBase = t.montoReembolsoPorOrganizador();
			out.setIngresosBase(out.getIngresosBase() + ingresoBase);
			out.setTiquetesVendidos(out.getTiquetesVendidos() + 1);
			out.addIngresoEvento(e.getId(), ingresoBase);
		}
		return out;
	}
	@Override public GananciasGenerales gananciasAdministrador(FiltroGanancias f) {
		GananciasGenerales out = new GananciasGenerales();
		for (Tiquete t: ctx.tiquetes.values()) {
			Evento e = t.getEvento();
			if (e == null) continue;
			if (t.getEstado() == EstadoTiquete.REEMBOLSADO) continue;
			if (!pasaRangoFecha(e, f.getDesde(), f.getHasta())) continue;
			if (f.getTipo() != null && e.getTipo() != f.getTipo()) continue;
			
			double cargo = Math.max(0.0, t.montoReembolsoporAdmin() - t.montoReembolsoPorOrganizador());
			
			double cuota;
			try {
				var method = t.getClass().getSuperclass().getDeclaredMethod("getCuotaEmisionFija");
				method.setAccessible(true);
				cuota = (double) method.invoke(t);
			} catch (Throwable ignore) {
				cuota = (ctx.cuotaEmisionFija != null) ? ctx.cuotaEmisionFija : 0.0;
			}
			
			out.setTicketsContados(out.getTicketsContados() + 1);
			out.setTotalCargoServicio(out.getTotalCargoServicio() + cargo);
			out.setTotalCuotaEmision(out.getTotalCuotaEmision() + cuota);
			out.addCargoPorTipo(e.getTipo(), cargo);
		}
		out.setTotalPlataforma(out.getTotalCargoServicio() + out.getTotalCuotaEmision());
		return out;
	}
	private boolean pasaRangoFecha(Evento e, LocalDate desde, LocalDate hasta) {
		if (e.getFechaHora() == null) return true;
		LocalDate fecha = e.getFechaHora().toLocalDate();
		if (desde != null && fecha.isBefore(desde)) return false;
		if (hasta != null && fecha.isAfter(hasta)) return false;
		return true;
		
	}

}
