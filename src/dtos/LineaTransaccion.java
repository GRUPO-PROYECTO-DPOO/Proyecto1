package dtos;

import enums.TipoItem;

public class LineaTransaccion {
    public TipoItem tipoItem;
    public String refItem;
    public int cantidad;
    public double precioBase;
    public double descuento;
    public double cargoServicio;
    public double cuotaEmisionFija;
    double subtotal;

    public LineaTransaccion() {}

    public LineaTransaccion(TipoItem tipoItem, String refItem, int cantidad,
                            double precioBase, double descuento, double cargoServicio, double cuotaEmisionFija) {
        if (tipoItem == null) throw new IllegalArgumentException("tipoItem");
        if (refItem == null || refItem.isBlank()) throw new IllegalArgumentException("refItem");
        if (cantidad <= 0) throw new IllegalArgumentException("cantidad");
        if (precioBase < 0 || descuento < 0 || cuotaEmisionFija < 0) throw new IllegalArgumentException("precios");
        if (cargoServicio < 0 || cargoServicio > 1) throw new IllegalArgumentException("cargoServicio [0..1]");
        this.tipoItem = tipoItem; 
        this.refItem = refItem; 
        this.cantidad = cantidad;
        this.precioBase = precioBase; 
        this.descuento = descuento; 
        this.cargoServicio = cargoServicio; 
        this.cuotaEmisionFija = cuotaEmisionFija;
    }

    public double calcularSubtotal() {
        double base = Math.max(0.0, precioBase - descuento) * cantidad;
        double cargo = Math.max(0.0, cargoServicio) * base;
        this.subtotal = base + cargo + Math.max(0.0, cuotaEmisionFija);
        return this.subtotal;
    }

    public double getSubtotal() { return subtotal; }
}
