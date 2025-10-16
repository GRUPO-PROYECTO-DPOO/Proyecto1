package escenariosyventas;
import java.time.LocalDateTime;
import escenariosyventas.*;
import usuarios.*;
import enums.*;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
public class Evento {
	private String id;
	private String nombre;
	private LocalDateTime fechaHora;
	private TipoEvento tipo;
	private Venue venue;
	private Organizador organizador;
	private EstadoEvento estado;
	private List<Localidad> localidades = new ArrayList<>();
	
	public Evento() { }
	public Evento(String id, String nombre, LocalDateTime fechaHora, TipoEvento tipo, Venue venue, Organizador organizador) {
		if (id == null || id.isBlank()) throw new IllegalArgumentException("Id vacio");
		if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre vacio");
		if (fechaHora ==  null) throw new IllegalArgumentException("FechaHora nula");
		if (tipo == null) throw new IllegalArgumentException("Tipo vacio");
		if (venue == null) throw new IllegalArgumentException("Venue vacio");
		if (organizador == null) throw new IllegalArgumentException("Organizador vacio");
		
		this.id = id;
		this.nombre = nombre;
		this.fechaHora = fechaHora;
		this.tipo = tipo;
		this.venue = venue;
		this.organizador = organizador;
	}
	
	public Localidad agregarLocalidad(CmdLocalidad cmd) {
		if (cmd == null) throw new IllegalArgumentException("CmdLocalidad nulo");
		Localidad l = new Localidad(cmd.getLocalidad(), this, cmd.getNombre(), cmd.getPrecioBase(), cmd.isNumerada(),
				cmd.getCapacidad());
		localidades.add(l);
		return l;
	}
	
	public int generarTiquetes(String idLocalidad) {
		Localidad loc = localidades.stream().filter(l -> Objects.equals(l.getId(), idLocalidad)).findFirst().orElseThrow
				(() -> new IllegalArgumentException("Localidad no encontrada"));
		return loc.generarInventario();
	}
	
	public double porcentajeOcupacion() {return 0.0;}
	public void cancelarPorAdmin() {this.estado = EstadoEvento.CANCELADO;}
	public void cancelarPorOrganizadorAutorizado() {this.estado = EstadoEvento.CANCELADO;}
	
	public String getId() {return id;}
	public LocalDateTime getFechaHora() {return fechaHora;}
	public List<Localidad> getLocalidades() {return localidades;}
	
	public void setId(String id) {this.id = id;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public void setFechaHora(LocalDateTime fechaHora) {this.fechaHora = fechaHora;}
	public void setTipo(TipoEvento tipo) {this.tipo = tipo;}
	public void setVenue(Venue venue) {this.venue = venue;}
	public void setOrganizador(Organizador organizador) {this.organizador = organizador;}
	public void setEstado(EstadoEvento estado) {this.estado = estado;}
	

}
