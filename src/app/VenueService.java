package app;

import java.util.List;

import dtos.CmdVenue;
import escenariosyventas.*;

public interface VenueService {
	SolicitudVenue proponerVenue(CmdVenue cmd);
	void aprobarSolicitud(String idSolicitud);
	void rechazaSolicitud(String idSolicitud, String motivo);
	List<Venue> listarVenues();
	
	

}
