package repos;

import java.util.List;
import java.util.Optional;

import escenariosyventas.Evento;

public interface EventoRepo {
	void save(Evento e);
	Optional<Evento> findById(String id);
	List<Evento> findAll();

}
