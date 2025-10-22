package tiquetes;

import java.time.LocalDateTime;

import enums.EstadoTiquete;
import usuarios.Cliente;
import escenariosyventas.Evento;
import escenariosyventas.Localidad;

public abstract class Tiquete {

	protected String id;

	protected Evento evento;

	protected Localidad localidad;

	protected Cliente dueno;

	protected double precioBase;

	protected double precioPagado;

	protected double cuotaEmisionFija;

	protected EstadoTiquete estado = EstadoTiquete.EMITIDO;

	protected Paquete paquete;
	
	
	protected Tiquete(String id, Evento evento, Localidad localidad, Cliente dueno, double precioBase, double precioPagado,
			double cuotaEmisionFija, EstadoTiquete estado, Paquete paquete) {
		
        if (evento == null) throw new IllegalArgumentException("evento nulo");
        if (localidad == null) throw new IllegalArgumentException("localidad nula");
        if (dueno == null) throw new IllegalArgumentException("dueño nulo");
        if (precioBase < 0 || precioPagado < 0 || cuotaEmisionFija < 0) throw new IllegalArgumentException("precios");
        this.id = id;
        this.evento = evento; 
        this.localidad = localidad; 
        this.dueno = dueno;
        this.precioBase = precioBase; 
        this.precioPagado = precioPagado; 
        this.cuotaEmisionFija = cuotaEmisionFija;
        if (estado != null) this.estado = estado;
        this.paquete = paquete;
    }
	
    public boolean esVencido(LocalDateTime ahora) {
        if (evento == null || evento.getFechaHora() == null || ahora == null) return false;
        return ahora.isAfter(evento.getFechaHora());
    }
    public void marcarTransferido(Cliente nuevoDueño) {this.dueno = nuevoDueño; this.estado = EstadoTiquete.TRANSFERIDO;}
   
    public void marcarReembolsado() {this.estado = EstadoTiquete.REEMBOLSADO;}
    public Paquete getPaquete() {return paquete;}

    public void asignarId(String nuevoId) {
    	  if (nuevoId == null || nuevoId.isBlank()) throw new IllegalArgumentException("id inválido");
    	  if (this.id != null && !this.id.isBlank()) throw new IllegalStateException("El tiquete ya tiene id");
    	  this.id = nuevoId;
    }
    
    public void registrarPago(double monto) {
    	  if (monto < 0) throw new IllegalArgumentException("monto<0");
    	  this.precioPagado = monto;
    }
    
    public void aplicarCuotaEmisionFija(double cuota) {
    	  if (cuota < 0) throw new IllegalArgumentException("cuota<0");
    	  this.cuotaEmisionFija = cuota;
    	}
    
    public double montoReembolsoporAdmin() {return Math.max(0.0, precioPagado - cuotaEmisionFija);}
    public double montoReembolsoPorOrganizador() {return Math.max(0.0, precioBase);}

    public EstadoTiquete getEstado() {return estado;}
    public Evento getEvento() {return evento;}
    public Cliente getDueño() {return dueno;}
    public String getId() {return id;}
    public double getPrecioBase() {return precioBase;}
    public double getPrecioPagado() {return precioPagado;}
    public double getCuotaEmisionFija() {return cuotaEmisionFija;}
}