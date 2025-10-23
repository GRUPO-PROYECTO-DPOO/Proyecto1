package dtos;

import java.time.LocalDateTime;

import enums.TipoEvento;
import escenariosyventas.Venue;

public class CmdCrearEvento {
	private String idEvento;
	private String nombre; 
	private LocalDateTime fechaHora;
	private TipoEvento tipo;
	private Venue venue;
	
	public String getIdEvento() {return idEvento;}
	public String getNombre() {return nombre;}
	public LocalDateTime getFechaHora() {return fechaHora;}
	public TipoEvento getTipo() {return tipo;}
	public Venue getVenue() {return venue;}
	public void setIdEvento(String idEvento) {this.idEvento = idEvento;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public void setFechaHora(LocalDateTime fechaHora) {this.fechaHora = fechaHora;}
	public void setTipo(TipoEvento tipo) {this.tipo = tipo;}
	public void setVenue(Venue venue) {this.venue  = venue;}
}
