package tiquetes;

import java.util.List;

import usuarios.Cliente;

public class PaqueteDeluxe {

	public String id;
	public List<Tiquete> tiquetes;
	public List<String> beneficios;
	public double precioPaquete;
	public Cliente dueno;

	public PaqueteDeluxe(String id, List<String> beneficios, double precioPaquete, Cliente dueno) {

		this.id = id;
		this.beneficios = beneficios;
		this.precioPaquete = precioPaquete;
		this.dueno = dueno;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Tiquete> getTiquetes() {
		return tiquetes;
	}

	public void setTiquetes(List<Tiquete> tiquetes) {
		this.tiquetes = tiquetes;
	}

	public void setBeneficios(List<String> beneficios) {
		this.beneficios = beneficios;
	}

	public double getPrecioPaquete() {
		return precioPaquete;
	}

	public void setPrecioPaquete(double precioPaquete) {
		this.precioPaquete = precioPaquete;
	}

	public Cliente getDueno() {
		return dueno;
	}

	public void setDueno(Cliente dueno) {
		this.dueno = dueno;
	}

	public List<String> listarBeneficios() {
		return beneficios;
		
	}

}
