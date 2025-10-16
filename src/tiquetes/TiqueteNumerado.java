package tiquetes;

import clientes.Cliente;
import evento.Evento;
import evento.Localidad;

public class TiqueteNumerado extends Tiquete {

	private int numeroAsiento;

	public TiqueteNumerado(String id, Evento evento, Localidad localidad, Cliente dueno, double precioBase,
			double precioPagado, double cuotaEmisionFija, String estado, int idPaquete, int numeroAsiento) {

		super(id, evento, localidad, dueno, precioBase, precioPagado, cuotaEmisionFija, estado, idPaquete);

		this.numeroAsiento = numeroAsiento;
	}

	public int getNumeroAsiento() {
		return numeroAsiento;
	}

	public void setNumeroAsiento(int numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}

}
