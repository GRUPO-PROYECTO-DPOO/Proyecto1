package transacciones;

import java.time.LocalDate;
import java.util.List;

import usuarios.Cliente;

public class TransaccionCompra {


	private String id;
	private LocalDate fecha;
	private Cliente cliente;
	private List<LineaTransaccion> linea;
	private double total;
	
	public TransaccionCompra(String id, LocalDate fecha, Cliente cliente, List<LineaTransaccion> linea, double total) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cliente = cliente;
		this.linea = linea;
		this.total = total;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<LineaTransaccion> getLinea() {
		return linea;
	}

	public void setLinea(List<LineaTransaccion> linea) {
		this.linea = linea;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void agregarLinea(LineaTransaccion linea) {
		
	}
	
	
}
