package transacciones;

import java.time.LocalDate;
import java.util.List;

import clientes.Cliente;
import tiquetes.Tiquete;

public class Transferencia {

	private String id;
	private Cliente emisor;
	private Cliente receptor;
	private List<Tiquete> tiquetes;
	private LocalDate fecha;

	public Transferencia(String id, Cliente emisor, Cliente receptor, List<Tiquete> tiquetes, LocalDate fecha) {
		this.id = id;
		this.emisor = emisor;
		this.receptor = receptor;
		this.tiquetes = tiquetes;
		this.fecha = fecha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Cliente getEmisor() {
		return emisor;
	}

	public void setEmisor(Cliente emisor) {
		this.emisor = emisor;
	}

	public Cliente getReceptor() {
		return receptor;
	}

	public void setReceptor(Cliente receptor) {
		this.receptor = receptor;
	}

	public List<Tiquete> getTiquetes() {
		return tiquetes;
	}

	public void setTiquetes(List<Tiquete> tiquetes) {
		this.tiquetes = tiquetes;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public void validarPoliticas() {
		
	}

	public void ejecutar(String loginEmisor, String passEmisor) {

	}
}
