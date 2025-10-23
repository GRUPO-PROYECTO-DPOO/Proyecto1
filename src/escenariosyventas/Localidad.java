package escenariosyventas;
import java.util.Set;

import enums.EstadoTiquete;
import tiquetes.Tiquete;
import tiquetes.TiqueteNumerado;
import tiquetes.TiqueteSimple;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import usuarios.*;
public class Localidad {
	private String id;
	private Evento evento;
	private String nombre;
	private double precioBase;
	private boolean numerada;
	private int capacidad;
	private Set<Integer> asientos;
	private List<Oferta> ofertas = new ArrayList<>();
	
	public Localidad() { }
	public Localidad(String id, Evento evento, String nombre, double precioBase, boolean numerada, int capacidad) {
		if (id == null || id.isBlank()) throw new IllegalArgumentException("Id vacio");
		if (evento == null) throw new IllegalArgumentException("Evento nulo");
		if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre vacio");
		if (precioBase < 0) throw new IllegalArgumentException("PrecioBase debe ser >= 0");
		if (capacidad <= 0) throw new IllegalArgumentException("Capacidad debe ser > 0");
		
		this.id = id;
		this.evento = evento;
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.numerada = numerada;
		this.capacidad = capacidad;
	}
	
	public int generarInventario() {
		if (!numerada) return capacidad;
		if (asientos ==  null) asientos = new LinkedHashSet<>();
		int before = asientos.size();
		for (int i = 1; i <= capacidad; i++) asientos.add(i);
		return asientos.size() - before;
	}
	
	public double precioVigente(LocalDateTime ahora) {
		double precio = precioBase;
		if (ofertas != null) {
			for (Oferta o: ofertas) {
				if (o != null && o.estaVigente(ahora)) precio = o.aplicarOferta(precio);
			}
		}
		return Math.max(0.0, precio);
	}

	public Tiquete emitirTiquete(Cliente duenio, String asientoOpcional) {
		if (duenio == null) throw new IllegalArgumentException("Dueño requerido");
		if (numerada) {
			if (asientos == null || asientos.isEmpty()) throw new IllegalStateException("No hay asientos disponibles");
			int asiento;
			try { asiento = Integer.parseInt(asientoOpcional);}
			catch (Exception e) {throw new IllegalArgumentException("Asiento invalido");}
			if (!asientos.remove(asiento)) throw new IllegalStateException("Asiento no disponible");
			return new TiqueteNumerado(null, evento, this, duenio, precioBase, precioBase, 0.0, EstadoTiquete.EMITIDO,
					null, String.valueOf(asiento));}
		else {
			return new TiqueteSimple(null, evento, this, duenio, precioBase, precioBase, 0.0, EstadoTiquete.EMITIDO, null);
		}
	}
	
	public boolean asientoDisponible(String asiento) {
		if (!numerada || asientos == null) return false;
		try {return asientos.contains(Integer.parseInt(asiento)); } 
		catch (Exception e) {return false;}
	}
	
	public void agregarOferta(Oferta oferta) {
		if (oferta == null) throw new IllegalArgumentException("Oferta nula");
		if (ofertas == null) ofertas = new ArrayList<>();
		ofertas.add(oferta);
	}
	
    public String getId() {return id;}
    public String getNombre() {return nombre;}
    public Evento getEvento() {return evento;}
    public boolean isNumerada() {return numerada;}
    public int getCapacidad() {return capacidad;}
    public double getPrecioBase() {return precioBase;}
    public void setId(String id) {this.id = id;}
    public void setEvento(Evento evento) {this.evento = evento;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setPrecioBase(double precioBase) {this.precioBase = precioBase;}
    public void setNumerada(boolean numerada) {this.numerada = numerada;}
    public void setCapacidad(int capacidad) {this.capacidad = capacidad;}
}
