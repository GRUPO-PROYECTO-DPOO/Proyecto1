package implementacion;
import app.*;
import dtos.*;
import escenariosyventas.*;
import run.TiqueteraContext;

public class EventoServiceSimple implements EventoService{
	private final TiqueteraContext ctx;
	public EventoServiceSimple(TiqueteraContext ctx) {this.ctx = ctx;}
	
	@Override public Evento crearEvento(CmdCrearEvento cmd) {
		Venue v = ctx.findVenue(cmd.getVenue().getId()).orElseThrow(() -> new IllegalStateException("Venue no aprobado"));
		Evento e = new Evento(cmd.getIdEvento(), cmd.getNombre(), cmd.getFechaHora(), cmd.getTipo(), v, new usuarios.Organizador());
		ctx.eventos.put(e.getId(), e);
		return e;
	}
	@Override public Localidad agregarLocalidad(String idEvento, CmdLocalidad cmd) {
		Evento e = ctx.findEvento(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento no existe"));
		Localidad l = new Localidad(cmd.getIdLocalidad(), e, cmd.getNombre(), cmd.getPrecioBase(), cmd.IsNumerada(), cmd.getCapacidad());
		ctx.localidades.put(TiqueteraContext.keyLoc(idEvento, l.getId()), l);
		return l;
	}
	@Override public int generarTiquetes(String idEvento, String idLocalidad) {
		Localidad l = ctx.findLocalidad(idEvento, idLocalidad).orElseThrow(() -> new IllegalArgumentException("Localidad no existe"));
		return l.generarInventario();
	}
}
