package tiquetes;

import usuarios.Cliente;
import escenariosyventas.Evento;
import escenariosyventas.Localidad;

public class TiqueteSimple extends Tiquete{

	//NO PARECE NECESARIO, PARA ESO PODEMOS GENERAR DIRECTAMENTE EL TIQUETE
	
	public TiqueteSimple(String id, Evento evento, Localidad localidad, Cliente dueno, double precioBase,
			double precioPagado, double cuotaEmisionFija, String estado, int idPaquete) {
		super(id, evento, localidad, dueno, precioBase, precioPagado, cuotaEmisionFija, estado, idPaquete);
		
	}

}
