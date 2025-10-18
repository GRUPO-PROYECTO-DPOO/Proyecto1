package escenariosyventas;
import java.time.LocalDateTime;

public class Venue {
	private String id;
	private String nombre;
	private String ubicacion;
	private int capacidad;
	private boolean aprobado;
	private String restricciones;
	
	public Venue() { }
	public Venue(String id, String nombre, String ubicacion, int capacidad) {
		if (id == null || id.isBlank()) throw new IllegalArgumentException("id vacio");
		if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("nombre vacio");
		if (ubicacion == null || ubicacion.isBlank()) throw new IllegalArgumentException("ubicacion vacio");
		if (capacidad <= 0) throw new IllegalArgumentException("capacidad invalida");
		this.id = id;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
		
	}
	public void marcarAprobado() {this.aprobado = true;}
	public void marcarRechazado() {this.aprobado = false;}
	public boolean esDisponible(LocalDateTime fechaHora) {return aprobado;}
	
	public String getId() {return id;}
	public String getNombre() {return nombre;}
	public String getUbicacion() {return ubicacion;} 
	public int getCapacidad() {return capacidad;}
	public String getRestricciones() {return restricciones;}
	public boolean isAprobado() {return aprobado;}
	public void setAprobado(boolean aprobado) {this.aprobado = aprobado;}
	public void setRestricciones(String restricciones) {this.restricciones = restricciones;}
	
}
