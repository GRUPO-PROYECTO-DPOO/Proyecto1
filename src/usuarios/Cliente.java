package usuarios;
import dinero.SaldoVirtual;
import enums.Rol;

public class Cliente extends Usuario{
	private SaldoVirtual saldo;
	
	public Cliente() { }
	public Cliente(String login, String password, String nombre, String email) {
		super(login, password, nombre, email, Rol.CLIENTE);
		this.saldo = new SaldoVirtual(this, 0.0);
	}
	public double consultarSaldo() {
		return (saldo != null) ? saldo.getSaldo() : 0.0;
	}
	
	public void acreditarSaldo(double monto) {
		if (monto < 0) throw new IllegalArgumentException("Monto negativo");
		if (saldo == null) { 
			saldo = new SaldoVirtual();
			saldo.setUsuario(this);
		}
		saldo.acreditar(monto);
	}
	
	public void debitarSaldo(double monto) {
		if (saldo == null) throw new IllegalStateException("Saldo no inicializado");
		saldo.debitar(monto);
	}
	public SaldoVirtual getSaldoVirtual() {return saldo;}
}
