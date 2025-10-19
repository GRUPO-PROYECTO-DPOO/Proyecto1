package repos;

import java.util.List;

import tiquetes.Tiquete;

public interface TiqueteRepo {
	void save(Tiquete t);
	List<Tiquete> findByEvento(String idEvento);
	List<Tiquete> findByDuenio(String login);

}
