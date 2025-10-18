package usuarios;

import enums.*;
import reportesyfiltros.FiltroGanancias;
import reportesyfiltros.GananciasGenerales;

public class Administrador extends Usuario {
	public Administrador() { }
	public Administrador(String login, String password, String nombre, String email) {
		super(login, password, nombre, email, Rol.AMINISTRADOR);
}
	
	public void aprobarVenue(String idSolicitud) {
		if (idSolicitud == null || idSolicitud.isBlank()) throw new IllegalArgumentException("IdSolicitud vacio");
	}
	
	public void rechazarVenue(String idSolicitud, String motivo) {
		if (idSolicitud == null || idSolicitud.isBlank()) throw new IllegalArgumentException("IdSolicitud vacio");
		if (motivo == null || motivo.isBlank()) throw new IllegalArgumentException("Motivo vacio");
	}
	
	public void fijarCargoServicio(TipoEvento tipo, double porcentaje) {
		if (tipo == null) throw new IllegalArgumentException("TipoEvento nulo");
		if (porcentaje < 0 || porcentaje > 1) throw new IllegalArgumentException("Porcentaje invalido");
	}
	
	public void fijarCuotaEmision(double monto) {
		if (monto < 0) throw new IllegalArgumentException("Cuota negativa");
	}
	
	public void cancelarEvento(String idEvento) {
		if (idEvento ==  null || idEvento.isBlank()) throw new IllegalArgumentException("idEvento vacio");
	}
	
	public GananciasGenerales revisarGanancias(FiltroGanancias filtro) {
		if (filtro == null) throw new IllegalArgumentException("FiltroGanancias nulo");
		return new GananciasGenerales();
	}
}
