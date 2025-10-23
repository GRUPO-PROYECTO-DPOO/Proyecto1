package app;

import escenariosyventas.*;
import dtos.*;

public interface EventoService {
	Evento crearEvento(CmdCrearEvento cmd);
	Localidad agregarLocalidad(String idEvento, CmdLocalidad cmd);
	int generarTiquetes(String idEvento, String idLocalidad);

}
