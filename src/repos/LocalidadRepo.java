package repos;

import java.util.List;
import java.util.Optional;

import escenariosyventas.*;

public interface LocalidadRepo {
	void save(Localidad l);
	Optional<Localidad> finById(String idEvento, String idLocalidad);
	List<Localidad> findByEvento(String idEvento);
	

}
