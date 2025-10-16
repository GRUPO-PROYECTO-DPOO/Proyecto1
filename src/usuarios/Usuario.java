package usuarios;

import java.util.Objects;

import enums.*;

public abstract class Usuario {
	protected String login;
	protected String password;
	protected String nombre;
	protected String email;
	protected Rol rol;

	public Usuario() { }
	protected Usuario(String login, String password, String nombre, String email, Rol rol) {
		if (login == null || login.isBlank()) throw new IllegalArgumentException("login es nulo");
		if (password == null || password.isBlank()) throw new IllegalArgumentException("password es nulo");
		if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("nombre es nulo");
		if (email == null || email.isBlank()) throw new IllegalArgumentException("email es nulo");
		if (rol == null) throw new IllegalArgumentException("rol es nulo");
		this.login = login;
		this.password = password;
		this.nombre = nombre;
		this.email = email;
		this.rol = rol;
	}
	public boolean autenticar(String passPlano) {
		return Objects.equals(this.password, passPlano);
	}
	
	public void cambiarPassword(String passActual, String passNuevo) {
		if (!autenticar(passActual)) {
			throw new IllegalArgumentException("Password actual incorrecto");
		}
		if (passNuevo == null || passNuevo.isBlank()) {
			throw new IllegalArgumentException("Password nuevo invalido");
		}
		this.password = passNuevo;
	}
	
	public Rol getRol() {return rol;}
	public String getLogin() {return login;}
	public String getNombre() {return nombre;}
	public String getEmail() {return email;}
}

