package repos;

import java.util.List;
import java.util.Optional;

import escenariosyventas.SolicitudVenue;

public interface SolicitudVenueRepo {
	void save(SolicitudVenue s);
	Optional<SolicitudVenue> findById(String id);
	List<SolicitudVenue> findAllPendientes();
	
}
