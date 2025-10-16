package escenariosyventas;

import java.time.LocalDate;

import enums.EstadoSolicitud;
import usuarios.*;
public class SolicitudVenue {
	private String id;
	private Organizador propuestoPor;
	private String venuePropuesto;
	private LocalDate fecha;
	private EstadoSolicitud estado = EstadoSolicitud.PENDIENTE;
	private String motivoRechazo;
	
	public SolicitudVenue() { }
	public SolicitudVenue(String id, Organizador propuestoPor, String venuePropuesto, LocalDate fecha) {
		if (id == null || id.isBlank()) throw new IllegalArgumentException("id vacio");
		if (propuestoPor == null) throw new IllegalArgumentException("propuestoPor vacio");
		if (venuePropuesto == null) throw new IllegalArgumentException("VenuePropuesto vacio");
		if (fecha == null) throw new IllegalArgumentException("fecha");
		this.id = id;
		this.propuestoPor = propuestoPor;
		this.venuePropuesto = venuePropuesto;
		this.fecha = fecha;
	}
	
	public void aprobar() {this.estado = EstadoSolicitud.APROBADA; this.motivoRechazo = null;}
	public void rechazar(String motivo) {
		if (motivo == null || motivo.isBlank()) throw new IllegalArgumentException("Motivo requerido");
		this.estado = EstadoSolicitud.RECHAZADA;
		this.motivoRechazo = motivo;
		
	}
	public String getId() {return id;}
	public Organizador getPropuestoPor() {return propuestoPor;}
	public String getVenuePropuesto() {return venuePropuesto;}
	public LocalDate getFecha() {return fecha;}
	public EstadoSolicitud getEstado() {return estado;}
	public String getMotivoRechazo() {return motivoRechazo;}
	
	public void setId(String id) {this.id = id;}
	public void setPropuesto(Organizador propuestoPor) {this.propuestoPor = propuestoPor;}
	public void setVenuePropuesto(String venuePropuesto) {this.venuePropuesto = venuePropuesto;}
	public void setFecha(LocalDate fecha) {this.fecha = fecha;}
	public void setEstado(EstadoSolicitud estado) {this.estado = estado;}

}

