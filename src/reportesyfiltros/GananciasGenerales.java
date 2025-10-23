package reportesyfiltros;
import java.util.*;
import enums.*;
public class GananciasGenerales {
	private int ticketsContados;
	private double totalCargoServicio; //suma de carg
	private double totalCuotaEmision; // suma de cuotas fijas
	private double totalPlataforma; // cargo + cuota
	private EnumMap<TipoEvento, Double> cargoPorTipo = new EnumMap<>(TipoEvento.class);
	

	  public int getTicketsContados() {return ticketsContados;}
	  public void setTicketsContados(int ticketsContados) {this.ticketsContados = ticketsContados;}
	  public double getTotalCargoServicio() {return totalCargoServicio;}
	  public void setTotalCargoServicio(double totalCargoServicio) {this.totalCargoServicio = totalCargoServicio;}
	  public double getTotalCuotaEmision() {return totalCuotaEmision;}
	  public void setTotalCuotaEmision(double totalCuotaEmision) {this.totalCuotaEmision = totalCuotaEmision;}
	  public double getTotalPlataforma() {return totalPlataforma;}
	  public void setTotalPlataforma(double totalPlataforma) {this.totalPlataforma = totalPlataforma;}
	  public EnumMap<TipoEvento, Double> getCargoPorTipo() {return cargoPorTipo;}
	  public void setCargoPorTipo(EnumMap<TipoEvento, Double> cargoPorTipo) {this.cargoPorTipo = cargoPorTipo;}

	  public void addCargoPorTipo(TipoEvento tipo, double cargo){
	    cargoPorTipo.merge(tipo, cargo, Double::sum);
	  }
	}


