package transacciones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dtos.ItemRecibo;
import dtos.LineaTransaccion;
import dtos.Recibo;
import usuarios.Cliente;

public class TransaccionCompra {
	private String id;
	private LocalDate fecha;
	private Cliente cliente;
	private List<LineaTransaccion> lineas = new ArrayList<>();
	private double total;
	
	public TransaccionCompra(String id, LocalDate fecha, Cliente cliente, List<LineaTransaccion> lineas, double total) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id");
        if (fecha == null) throw new IllegalArgumentException("fecha");
        if (cliente == null) throw new IllegalArgumentException("cliente");
		this.id = id;
		this.fecha = fecha;
		this.cliente = cliente;
	}
	
    public void agregarLinea(LineaTransaccion l) {
        if (l == null) throw new IllegalArgumentException("Línea nula");
        lineas.add(l);
    }

    public void validarTope() {
        if (lineas.isEmpty()) throw new IllegalStateException("Transacción sin líneas");
        for (LineaTransaccion l : lineas) if (l.calcularSubtotal() < 0) throw new IllegalStateException("Subtotal negativo");
    }

    public double totalizar() {
        validarTope();
        total = 0.0;
        for (LineaTransaccion l : lineas) total += l.getSubtotal();
        return total;
    }

    public Recibo aRecibo() {
        Recibo r = new Recibo();
        r.setIdTransaccion(id);
        r.setFecha(fecha);
        r.setLoginCliente(cliente != null ? cliente.getLogin() : null);
        List<ItemRecibo> items = new ArrayList<>();
        for (LineaTransaccion l : lineas) {
            ItemRecibo it = new ItemRecibo();
            it.setTipoItem(l.tipoItem != null ? l.tipoItem.name() : null);
            it.setRefItem(l.refItem);
            it.setCantidad(l.cantidad);
            it.setPrecioBase(l.precioBase);
            it.setDescuento(l.descuento);
            it.setCargoServicio(l.cargoServicio);
            it.setCuotaEmisionFija(l.cuotaEmisionFija);
            it.setSubtotal(l.getSubtotal());
            items.add(it);
        }
        r.setItems(items);
        r.setTotal(total);
        r.setCargo(0.0);
        r.setCuotaFija(0.0);
        return r;
    }
}