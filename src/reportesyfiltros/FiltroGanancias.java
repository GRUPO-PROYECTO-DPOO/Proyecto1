package reportesyfiltros;
import java.time.*;
import enums.*;
public class FiltroGanancias {
	private LocalDate desde;
	private LocalDate hasta;
	private TipoEvento tipo;
	
	public LocalDate getDesde() {return desde;}
	public void setDesde(LocalDate desde) {this.desde = desde;}
	public LocalDate getHasta() {return hasta;}
	public void setHasta(LocalDate hasta) {this.hasta = hasta;}
	public TipoEvento getTipo() {return tipo;}
	public void setTipo(TipoEvento tipo) {this.tipo = tipo;}

}
