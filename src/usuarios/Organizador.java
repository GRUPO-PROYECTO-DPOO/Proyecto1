package usuarios;

import enums.Rol;
import escenariosyventas.*;
import reportesyfiltros.EstadoFinanciero;
import reportesyfiltros.FiltroFinanzas;
import dtos.*;
public class Organizador extends Usuario {
	public Organizador() { }
	public Organizador(String login, String password, String nombre, String email) {
		super(login, password, nombre, email, Rol.ORGANIZADOR);
	}
	public Evento crearEvento(CmdCrearEvento cmd) {
		if (cmd == null) throw new IllegalArgumentException("CmdCrearEvento nulo");
		return new Evento(cmd.getIdEvento(), cmd.getNombre(), cmd.getFechaHora(), cmd.getTipo(), cmd.getVenue(), this);
	}
	
	public Localidad configurarLocalidad(String idEvento, CmdLocalidad cmd) {
		if (cmd == null) throw new IllegalArgumentException("CmdLocalidad nulo");
		return new Localidad(cmd.getIdLocalidad(), cmd.getEvento(), cmd.getNombre(), cmd.getPrecioBase(), cmd.IsNumerada(),
				cmd.getCapacidad());
	}
	
	public Oferta crearOferta(String idLocalidad, CmdOferta cmd){
		if (cmd == null) throw new IllegalArgumentException("CmdOferta nulo");
		return new Oferta(cmd.getIdOferta(), cmd.getLocalidad(), cmd.getPorcentaje(), cmd.getInicio(), cmd.getFin());
	}
	
	public SolicitudVenue proponerVenue(CmdVenue cmd) {
		if (cmd == null) throw new IllegalArgumentException("CmdVenue nulo");
		return new SolicitudVenue(cmd.getIdSolicitud(), this, cmd.getNombrePropuesto(), cmd.getFecha());
	}
	
	public void solicitarCancelacionEvento(String idEvento) {
		if (idEvento == null || idEvento.isBlank()) throw new IllegalArgumentException("idEvento vacio");
	}
	
	public EstadoFinanciero revisarFinanzas(FiltroFinanzas filtro) {
		if (filtro == null) throw new IllegalArgumentException("FiltroFinanzas nulo");
		return new EstadoFinanciero();
	}

}
