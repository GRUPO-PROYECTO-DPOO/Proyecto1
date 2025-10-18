package escenariosyventas;

import java.time.LocalDateTime;

public class Oferta {
	private String id;
	private Localidad localidad;
	private double porcentaje;
	private LocalDateTime inicio;
	private LocalDateTime fin;
	
	public Oferta() { }
	public Oferta(String id, Localidad localidad, double porcentaje, LocalDateTime inicio, LocalDateTime fin) {
		if (id == null || id.isBlank()) throw new IllegalArgumentException("Id vacio");
		if (localidad == null) throw new IllegalArgumentException("Localidad nula");
		if (porcentaje < 0 || porcentaje > 1) throw new IllegalArgumentException("Porcentaje debe ser > 0 y < 1");
		if (inicio != null && fin != null && fin.isBefore(inicio)) throw new IllegalArgumentException("Ventana invalida");
		
		this.id = id;
		this.localidad = localidad;
		this.porcentaje = porcentaje;
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public boolean estaVigente(LocalDateTime ahora) {
		if (ahora == null) return false;
		if (inicio != null && ahora.isBefore(inicio)) return false;
		if (fin != null && ahora.isAfter(fin)) return false;
		return porcentaje > 0.0;
	}
	
	public double aplicarOferta(double precioBase) {
		if (porcentaje < 0 || porcentaje > 1) throw new IllegalStateException("Porcentaje fuera de rango");
		return Math.max(0.0, precioBase * (1.0 - porcentaje));
	}
	
	public String getId() {return id;}
	public Localidad getLocalidad() {return localidad;}
	
	public void setId(String id) {this.id = id;}
	public void setLocalidad(Localidad localidad) {this.localidad = localidad;}
	public void setPorcentaje(double porcentaje) {this.porcentaje = porcentaje;}
	public void setInicio(LocalDateTime inicio) {this.inicio = inicio;}
	public void setFin(LocalDateTime fin) {this.fin = fin;}
}
