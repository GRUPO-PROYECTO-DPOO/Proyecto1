package tiquetes;

import java.time.LocalDateTime;

import clientes.Cliente;
import evento.Evento;
import evento.Localidad;

public abstract class Tiquete {

	private String id;

	private Evento evento;

	private Localidad localidad;

	private Cliente dueno;

	private double precioBase;

	private double precioPagado;

	private double cuotaEmisionFija;

	private String estado;

	private int idPaquete;
	
	//contemplar añadir tipo de tiquete para diferenciar facilmente entre simple, numerado, multiple y multi evento
	
	public Tiquete(String id, Evento evento, Localidad localidad, Cliente dueno, double precioBase, double precioPagado,
			double cuotaEmisionFija, String estado, int idPaquete) {

		this.id = id;
		this.evento = evento;
		this.localidad = localidad;
		this.dueno = dueno;
		this.precioBase = precioBase;
		this.precioPagado = precioPagado;
		this.cuotaEmisionFija = cuotaEmisionFija;
		this.estado = estado;
		this.idPaquete = idPaquete;

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

	public Cliente getDueno() {
		return dueno;
	}

	public void setDueno(Cliente dueno) {
		this.dueno = dueno;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public double getPrecioPagado() {
		return precioPagado;
	}

	public void setPrecioPagado(double precioPagado) {
		this.precioPagado = precioPagado;
	}

	public double getCuotaEmisionFija() {
		return cuotaEmisionFija;
	}

	public void setCuotaEmisionFija(double cuotaEmisionFija) {
		this.cuotaEmisionFija = cuotaEmisionFija;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIdPaquete() {
		return idPaquete;
	}

	public void setIdPaquete(int idPaquete) {
		this.idPaquete = idPaquete;
	}

	public boolean esVencido(LocalDateTime ahora) {
		if (this.estado == "VENCIDO") {
			return true;
		} else {
			if (ahora.isAfter(evento.getFechaHora()))
				return false;
		}

	}

	public void marcarTransferido(Cliente nuevoDueno) {
		if (this.dueno != nuevoDueno) {
			this.estado = "TRANSFERIDO";
		}
	}

	public void marcarReembolsado() {
		this.estado = "REEMBOLSADO";
	}

	public boolean perteneceAPaquete(int newIdPaquete) {
		if (this.idPaquete == newIdPaquete) {
			return true;
		} else {
			return false;
		}

	}

	public double montoReembolsoPorAdmin() {
		return precioPagado - cuotaEmisionFija;
	}

	public double montoReembolsoPorOrganizador() {
		return precioBase;
	}
}
