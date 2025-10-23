package implementacion;
import app.*;
import dtos.LineaTransaccion;
import enums.*;
import reportesyfiltros.*;
import run.*;
import escenariosyventas.*;
import transacciones.TransaccionCompra;

import java.time.*;
import java.util.*;

public class ReporteServiceSimple implements ReporteService {
	private final TiqueteraContext ctx;
	public ReporteServiceSimple(TiqueteraContext ctx) {this.ctx = ctx;}
	
	@Override public EstadoFinanciero finanzasOrganizador(String loginOrg, FiltroFinanzas f) {
	    if (loginOrg == null || !ctx.usuarios.containsKey(loginOrg) || ctx.usuarios.get(loginOrg).getRol() != Rol.ORGANIZADOR) {
	          throw new IllegalArgumentException("Acceso denegado: no es Organizador");}
		
	    final LocalDate desde = (f != null) ? f.getDesde() : null;
	    final LocalDate hasta = (f != null) ? f.getHasta() : null;
	    final String idEventoFiltro = (f != null) ? f.getIdEvento() : null;
	    
	    EstadoFinanciero out = new EstadoFinanciero();
		out.setLoginOrganizador(loginOrg);
		
	    for (TransaccionCompra tx : ctx.transacciones) {
	        if (!pasaRangoFecha(tx.getFecha(), desde, hasta)) continue;

	        for (LineaTransaccion li : tx.getLineas()) {
	          if (li.getTipoItem() != TipoItem.TIQUETE) continue;

	          Localidad loc = ctx.localidades.values().stream()
	              .filter(L -> Objects.equals(L.getId(), li.getRefItem()))
	              .findFirst().orElse(null);
	          if (loc == null) continue;
	          Evento ev = loc.getEvento(); if (ev == null) continue;
	          if (idEventoFiltro != null && !idEventoFiltro.equals(ev.getId())) continue;
	          if (ev.getOrganizador() == null || !loginOrg.equals(ev.getOrganizador().getLogin())) continue;

	          double ingresoLinea = li.getPrecioBase() - li.getDescuento();
	          if (ingresoLinea < 0) ingresoLinea = 0.0;

	          out.setIngresosBase(out.getIngresosBase() + ingresoLinea);
	          out.setTiquetesVendidos(out.getTiquetesVendidos() + li.getCantidad());


	          try { out.addIngresoEvento(ev.getId(), ingresoLinea); } catch (Throwable ignore) {}
	        }
	      }

	      return out;
	    }
		
	@Override public GananciasGenerales gananciasAdministrador(FiltroGanancias f) {
	    final LocalDate desde = (f != null) ? f.getDesde() : null;
	    final LocalDate hasta = (f != null) ? f.getHasta() : null;
				
		GananciasGenerales out = new GananciasGenerales();
	    for (TransaccionCompra tx : ctx.transacciones) {
	        if (!pasaRangoFecha(tx.getFecha(), desde, hasta)) continue;

	        for (LineaTransaccion li : tx.getLineas()) {
	          if (li.getTipoItem() != TipoItem.TIQUETE) continue;
	   
	          double baseNeta = li.getPrecioBase() - li.getDescuento();
	          if (baseNeta < 0) baseNeta = 0.0;

	          double cargoLinea = baseNeta * li.getCargoServicio();   
	          double cuotasLinea = li.getCuotaEmisionFija() * li.getCantidad();
	          out.setTotalCargoServicio(out.getTotalCargoServicio() + cargoLinea);
	          out.setTotalCuotaEmision(out.getTotalCuotaEmision() + cuotasLinea);
	          out.setTicketsContados(out.getTicketsContados() + li.getCantidad());

	          try {
	            Localidad loc = ctx.localidades.values().stream().filter(L -> Objects.equals(L.getId(), li.getRefItem())).findFirst().orElse(null);
	            if (loc != null && loc.getEvento() != null) {out.addCargoPorTipo(loc.getEvento().getTipo(), cargoLinea);}
	          } catch (Throwable ignore) {}
	        }
	      }

	      out.setTotalPlataforma(out.getTotalCargoServicio() + out.getTotalCuotaEmision());
	      return out;
	    }

	  private static boolean pasaRangoFecha(LocalDate fechaTx, LocalDate desde, LocalDate hasta) {
		    if (fechaTx == null) return true;
		    if (desde != null && fechaTx.isBefore(desde)) return false;
		    if (hasta != null && fechaTx.isAfter(hasta))  return false;
		    return true;
		  }
		}