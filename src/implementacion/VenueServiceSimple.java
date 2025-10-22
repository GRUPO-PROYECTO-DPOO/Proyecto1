package implementacion;
import app.*;
import dtos.*;
import escenariosyventas.*;
import run.TiqueteraContext;
import usuarios.*;
import java.util.ArrayList;
import java.util.List;

public class VenueServiceSimple implements VenueService {
	private final TiqueteraContext ctx;
	public VenueServiceSimple(TiqueteraContext ctx) {this.ctx = ctx;}
	
	@Override public SolicitudVenue proponerVenue(CmdVenue cmd) {
		Organizador org  = (Organizador) ctx.usuarios.values().stream().filter(u -> u instanceof Organizador).findFirst()
				.orElse(new Organizador());
		SolicitudVenue s = new SolicitudVenue(cmd.getIdSolicitud(), org, cmd.getNombrePropuesto(), cmd.getFecha());
		ctx.solicitudes.put(s.getId(), s);
		return s;
	}
		
		@Override public void aprobarSolicitud(String idSolicitud) {
			SolicitudVenue s = ctx.findSolicitud(idSolicitud).orElseThrow(() -> new IllegalArgumentException("Solicitud no existe"));
			s.aprobar();
			Venue v = new Venue(s.getId(), s.getVenuePropuesto(), "Por-definir", 1000);
			v.marcarAprobado();
			ctx.venues.put(v.getId(), v);
	}
		@Override public void rechazarSolicitud(String idSolicitud, String motivo) {
			SolicitudVenue s = ctx.findSolicitud(idSolicitud).orElseThrow(() -> new IllegalArgumentException("Solicitud no existe"));
			s.rechazar(motivo);		
		}
		@Override public List<Venue> listarVenues() {return new ArrayList<>(ctx.venues.values());}
		}
