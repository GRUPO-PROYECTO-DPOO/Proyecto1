package dtos;

public class ItemRecibo {
	private String tipoItem; 
	private String refItem;
    private int cantidad;
    private double precioBase;
    private double descuento;
    private double cargoServicio;
    private double  cuotaEmisionFija;
    private double subtotal;
    
    public String getTipoItem() {return tipoItem;}
    public String getRefItem() {return refItem;}
    public int getCantidad() {return cantidad;}
    public double getPrecioBase() { return precioBase;}
    public double getDescuento() { return descuento;}
    public double getCargoServicio() { return cargoServicio;}
    public double getCuotaEmisionFija() { return cuotaEmisionFija;}
    public double getSubtotal() { return subtotal; }
    
    public void setTipoItem(String tipoItem) {this.tipoItem = tipoItem;}
    public void setRefItem(String refItem) {this.refItem = refItem;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    public void setPrecioBase(double precioBase) {this.precioBase = precioBase;}
    public void setDescuento(double descuento) {this.descuento = descuento;}
    public void setCargoServicio(double cargoServicio) {this.cargoServicio = cargoServicio;}
    public void setCuotaEmisionFija(double cuotaEmisionFija) {this.cuotaEmisionFija = cuotaEmisionFija;}
    public void setSubtotal(double subtotal) {this.subtotal = subtotal;}
    
}
