package dtos;

import java.time.LocalDate;

public class CmdVenue {
	private String idSolicitud;
	private String nombrePropuesto;
	private LocalDate fecha;
	
	public String getIdSolicitud() {return idSolicitud;}
	public String getNombrePropuesto() {return nombrePropuesto;}
	public LocalDate getFecha() {return fecha;}
	
	public void setIdSolicitud(String idSolicitud) {this.idSolicitud = idSolicitud;}
	public void setNombrePropuesto(String nombrePropuesto) {this.nombrePropuesto = nombrePropuesto;}
	public void setFecha(LocalDate fecha) {this.fecha = fecha;}
	
 
 
}
