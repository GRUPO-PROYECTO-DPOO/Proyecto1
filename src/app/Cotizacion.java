package app;

public class Cotizacion {
	private final double precioBase;
	private final double cargoServicio;
	private final double cuotaEmisionFija;
	private final double total;
	
	public Cotizacion(double base, double cargo, double cuota) {
		this.precioBase = Math.max(0, base);
		this.cargoServicio = Math.max(0, cargo);
		this.cuotaEmisionFija = Math.max(0, cuota);
		this.total = this.precioBase + this.cargoServicio + this.cuotaEmisionFija;
	}
	  
	public double getPrecioBase(){return precioBase;}
	public double getCargoServicio(){return cargoServicio;}
	public double getCuotaEmisionFija(){return cuotaEmisionFija;}
	public double getTotal(){return total;}
}