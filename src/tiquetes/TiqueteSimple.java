package tiquetes;

import usuarios.Cliente;
import enums.EstadoTiquete;
import escenariosyventas.Evento;
import escenariosyventas.Localidad;

public class TiqueteSimple extends Tiquete{
	
	public TiqueteSimple(String id, Evento evento, Localidad localidad, Cliente dueno, double precioBase,
			double precioPagado, double cuotaEmisionFija, EstadoTiquete estado, Paquete paquete) {
		super(id, evento, localidad, dueno, precioBase, precioPagado, cuotaEmisionFija, estado, paquete);
		
	}

}
