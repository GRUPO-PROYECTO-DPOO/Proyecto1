package implementacion;
import app.*;
import run.TiqueteraContext;
import usuarios.Usuario;

public class AuthServiceSimple implements AuthService {
	private final TiqueteraContext ctx;
	public AuthServiceSimple(TiqueteraContext ctx) {this.ctx = ctx;}
	
	@Override public Usuario login(String login, String pass) {
		return ctx.findUsuario(login).filter(u -> u.autenticar(pass)).orElseThrow(() -> 
		new IllegalArgumentException("Credenciales invalidas"));
	}
}
