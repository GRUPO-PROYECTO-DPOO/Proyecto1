package reportesyfiltros;
import java.util.*;
public class EstadoFinanciero {
	private String loginOrganizador;
	private int tiquetesVendidos;
	private double ingresosBase;
	private Map<String, Double> ingresosPorEvento = new LinkedHashMap<>();
	
	public String getLoginOrganizador() {return loginOrganizador;}
	public void setLoginOrganizador(String loginOrganizador) {this.loginOrganizador = loginOrganizador;}
	public int getTiquetesVendidos() {return tiquetesVendidos;}
	public void setTiquetesVendidos(int tiquetesVendidos) {this.tiquetesVendidos = tiquetesVendidos;}
	public double getIngresosBase() {return ingresosBase;}
	public void setIngresosBase(double ingresosBase) {this.ingresosBase = ingresosBase;}
	public Map<String, Double> getIngresosPorEvento(){return ingresosPorEvento;}
	public void setIngresosPorEvento(Map<String, Double> ingresosPorEvento) {this.ingresosPorEvento = ingresosPorEvento;}
	
	public void addIngresoEvento(String idEvento, double monto) {
		ingresosPorEvento.merge(idEvento, monto, Double::sum);
	}		
}
