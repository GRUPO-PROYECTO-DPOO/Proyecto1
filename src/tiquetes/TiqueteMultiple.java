package tiquetes;

import java.util.List;
import java.util.Objects;

import enums.EstadoTiquete;
import usuarios.Cliente;
import escenariosyventas.Evento;
import escenariosyventas.Localidad;

public class TiqueteMultiple implements Paquete {

	private String id;
	private Evento evento;
	private Localidad localidad;
	private List<Tiquete> tiquetes;
	private double precioPaquete;
	private Cliente dueno;

	public TiqueteMultiple(String id, Evento evento, Localidad localidad, List<Tiquete> tiquetes, double precioPaquete, Cliente dueno) {
		
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id nulo");
        if (evento == null) throw new IllegalArgumentException("evento nulo");
        if (localidad == null) throw new IllegalArgumentException("localidad nula");
        if (tiquetes == null || tiquetes.isEmpty()) throw new IllegalArgumentException("tiquetes vacio");
        if (precioPaquete < 0) throw new IllegalArgumentException("precioPaquete no puede ser < 0");
        if (dueno == null) throw new IllegalArgumentException("dueño nulo");
		
		this.id = id;
		this.evento = evento;
		this.localidad = localidad;
		this.tiquetes = tiquetes;
		this.precioPaquete = precioPaquete;
		this.dueno = dueno;
		
		for (Tiquete t: tiquetes) if (t != null) t.paquete = this;
	}

    public void transferirTiquete(String idTiquete, String loginEmisor, String passEmisor, String loginReceptor) {
        if (dueno == null || !Objects.equals(dueno.getLogin(), loginEmisor) || !dueno.autenticar(passEmisor))
            throw new IllegalStateException("Credenciales emisor inválidas");
        if (tieneTiqueteVencidoOTransferido())
            throw new IllegalStateException("Paquete con piezas vencidas/transferidas");
        Tiquete t = tiquetes.stream().filter(x -> Objects.equals(x.id, idTiquete))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No pertenece al paquete"));
        t.estado = EstadoTiquete.TRANSFERIDO;
    }

    public void transferirPaquete(String loginEmisor, String passEmisor, String loginReceptor) {
        if (dueno == null || !Objects.equals(dueno.getLogin(), loginEmisor) || !dueno.autenticar(passEmisor))
            throw new IllegalStateException("Credenciales emisor inválidas");
        if (tieneTiqueteVencidoOTransferido())
            throw new IllegalStateException("Paquete con piezas vencidas/transferidas");
        for (Tiquete t : tiquetes) t.estado = EstadoTiquete.TRANSFERIDO;
    }

    public boolean tieneTiqueteVencidoOTransferido() {
        return tiquetes != null && tiquetes.stream()
                .anyMatch(t -> t.getEstado() == EstadoTiquete.VENCIDO || t.getEstado() == EstadoTiquete.TRANSFERIDO);
    }

    public Evento getEvento() {return evento;}
    public Localidad getLocalidad() {return localidad;}
    public double getPrecioPaquete() {return precioPaquete;}
    @Override public String getId() { return id; }
    @Override public java.util.List<Tiquete> getTiquetes() { return tiquetes; }
}