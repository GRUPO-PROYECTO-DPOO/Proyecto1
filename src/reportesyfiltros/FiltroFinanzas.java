package reportesyfiltros;
import java.time.*;
public class FiltroFinanzas {
	private LocalDate desde;
	private LocalDate hasta;
	private String idEvento;
	
	public LocalDate getDesde() {return desde;}
	public void setDesde(LocalDate desde) {this.desde = desde;}
	public LocalDate getHasta() {return hasta;}
	public void setHasta(LocalDate hasta) {this.hasta = hasta;}
	public String getIdEvento() {return idEvento;}
	public void setIdEvento(String idEvento) {this.idEvento = idEvento;}

}
