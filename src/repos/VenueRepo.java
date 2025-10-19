package repos;

import java.util.List;
import java.util.Optional;

import escenariosyventas.Venue;

public interface VenueRepo {
	void save(Venue v);
	Optional<Venue> findById(String id);
	List<Venue> findAll();

}
