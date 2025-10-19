package repos;

import java.util.Optional;

import usuarios.Usuario;

public interface UsuarioRepo { 
	Optional<Usuario> findbyLogin(String login);
	void save(Usuario u);

}
