package dtos;

import escenariosyventas.Evento;

public class CmdLocalidad {
	private String idLocalidad;
	private String nombre;
	private Evento evento;
	private double precioBase;
	private boolean numerada;
	private int capacidad;
	
    public String getIdLocalidad() {return idLocalidad;}
    public Evento getEvento() {return evento;}
    public String getNombre() {return nombre;}
    public double getPrecioBase() {return precioBase;}
    public boolean IsNumerada() {return numerada;}
    public int getCapacidad() {return capacidad;}
    public void setIdLocalidad(String idLocalidad) {this.idLocalidad = idLocalidad;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setEvento(Evento evento) {this.evento = evento;}
    public void setPrecioBase(double precioBase) {this.precioBase = precioBase;}
    public void setNumerada(boolean numerada) {this.numerada = numerada;}
    public void setCapacidad(int capacidad) {this.capacidad = capacidad;}
   
}
