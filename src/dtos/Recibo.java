package dtos;

import java.time.LocalDate;
import java.util.List;

public class Recibo {
	private String idTransaccion;
	private LocalDate fecha;
	private String loginCliente;
	private List<ItemRecibo> items;
	private double total;
	private double cargo;
	private double cuotaFija;
	 
	public String getIdTransaccion() {return idTransaccion;}
	public LocalDate getFecha() {return fecha;}
	public String getLoginCliente() {return loginCliente;}
	public List<ItemRecibo> getItems() {return items;}
	public double getTotal() {return total;}
	public double getCargo() {return cargo;}
	public double getCuotaFija() {return cuotaFija;}
	
	public void setIdTransaccion(String idTransaccion) {this.idTransaccion = idTransaccion;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}
    public void setLoginCliente(String loginCliente) {this.loginCliente = loginCliente;}
    public void setItems(List<ItemRecibo> items) {this.items = items;}
    public void setTotal(double total) {this.total = total;}
    public void setCargo(double cargo) {this.cargo = cargo;}
    public void setCuotaFija(double cuotaFija) {this.cuotaFija = cuotaFija;}
	}


