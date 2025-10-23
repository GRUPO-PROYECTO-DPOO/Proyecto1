package usuarios;
public class SaldoVirtual {
	private Usuario usuario;
	private double saldo;
	
	public SaldoVirtual() { }
	public SaldoVirtual(Usuario usuario, double saldoInicial) {
		if (usuario == null) throw new IllegalArgumentException("usuario");
		if (saldoInicial < 0) throw new IllegalArgumentException("saldo inicial menor a cero");
		this.usuario = usuario;
		this.saldo = saldoInicial;
	}
	public void acreditar(double monto) {
		if (monto < 0) throw new IllegalArgumentException("Monto negativo");
		saldo += monto;
	}
	
	public void debitar(double monto) {
		if (monto < 0) throw new IllegalArgumentException("Monto negtivo");
		if (saldo < monto) throw new IllegalStateException("Saldo insuficiente");
		saldo -=monto;
	}
	
	public boolean puedePagar(double monto) {
		if (monto < 0) return false;
		return saldo >= monto;
	}
	
	public double getSaldo() {return saldo;}
	public Usuario getUsuario( ) {return usuario;}
	
	public void setUsuario(Usuario usuario) {this.usuario = usuario;}
	public double consultarSaldo() {return this.saldo;}

}
