package dtos;

import java.time.LocalDateTime;

import escenariosyventas.Localidad;

public class CmdOferta {
	private String idOferta;
	private Localidad localidad;
	private int porcentaje;
	private LocalDateTime inicio;
	private LocalDateTime fin;
	
    public String getIdOferta() {return idOferta;}
    public Localidad getLocalidad() {return localidad;}
    public double getPorcentaje() {return porcentaje;}
    public LocalDateTime getInicio() {return inicio;}
    public LocalDateTime getFin() {return fin;}
	

}
