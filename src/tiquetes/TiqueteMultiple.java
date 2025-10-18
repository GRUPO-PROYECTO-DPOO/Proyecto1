package tiquetes;

import java.util.List;

import usuarios.Cliente;
import escenariosyventas.Evento;
import escenariosyventas.Localidad;

public class TiqueteMultiple {

	private String id;
	private Evento evento;
	private Localidad localidad;
	private List<Tiquete> tiquetes;
	private double precioPaquete;
	private Cliente dueno;

	public TiqueteMultiple(String id, Evento evento, Localidad localidad, double precioPaquete, Cliente dueno, List<Tiquete> tiquetes) {
		
		this.id = id;
		this.precioPaquete = precioPaquete;
		this.dueno = dueno;
		this.tiquetes = tiquetes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public List<Tiquete> getTiquetes() {
		return tiquetes;
	}

	public void setTiquetes(List<Tiquete> tiquetes) {
		this.tiquetes = tiquetes;
	}

	public double getPrecioPaquete() {
		return precioPaquete;
	}

	public void setPrecioPaquete(double precioPaquete) {
		this.precioPaquete = precioPaquete;
	}

	public Cliente getDueno() {
		return dueno;
	}

	public void setDueno(Cliente dueno) {
		this.dueno = dueno;
	}

	public void transferirTiquete(String idTiquete, String loginEmisor, String passEmisor, String loginReceptor) {
		//necesitamos lista o mapa de tiquetes/ paquetes para el cliente -> el mapa funcione con el id como llave y el valor seria toda la informacion del tiquete
	}

	public void transferirPaquete(String loginEmisor, String passEmisor, String loginReceptor) {
		//necesitamos lista o mapa de tiquetes/ paquetes para el cliente -> el mapa funcione con el id como llave y el valor seria toda la informacion del paquete
	}

	public boolean tieneTiqueteVencidoOTransferido() {

		for (Tiquete t : tiquetes) {
			String estado = t.getEstado();
			if (estado.equals("VENCIDO") || estado.equals("TRANSFERIDO")) {
				return true;
			}
		}
		return false;

	}

}
