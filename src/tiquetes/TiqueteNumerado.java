package tiquetes;

import usuarios.Cliente;
import escenariosyventas.Evento;
import escenariosyventas.Localidad;
import enums.*;

public class TiqueteNumerado extends Tiquete {

	private String asiento;

	;

	public TiqueteNumerado(String id, Evento evento, Localidad localidad, Cliente dueno, double precioBase,
			double precioPagado, double cuotaEmisionFija, EstadoTiquete estado, Paquete paquete, String asiento) 
	{		super(id, evento, localidad, dueno, precioBase, precioPagado, cuotaEmisionFija, estado, paquete);

        if (asiento == null || asiento.isBlank()) throw new IllegalArgumentException("asiento");
        this.asiento = asiento;
	}

    public String getAsiento() { return asiento; }
}