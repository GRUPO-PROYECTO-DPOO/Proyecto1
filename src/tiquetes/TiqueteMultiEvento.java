package tiquetes;

import java.util.List;
import java.util.Objects;

import usuarios.Cliente;

public class TiqueteMultiEvento {

	private String id;
	private List<ItemEventoLocalidad> componentes;
	private double precioPaquete;
	private Cliente dueno;
	
	
	
	public TiqueteMultiEvento(String id, List<ItemEventoLocalidad> componentes, double precioPaquete, Cliente dueno) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id nulo");
        if (componentes == null || componentes.isEmpty()) throw new IllegalArgumentException("componentes nulos");
        if (precioPaquete < 0) throw new IllegalArgumentException("precioPaquete no puede ser < 0");
        if (dueno == null) throw new IllegalArgumentException("dueño nulo");
		
		this.id = id;
		this.componentes = componentes;
		this.precioPaquete = precioPaquete;
		this.dueno = dueno;
	}
	
    public void transferirTiquete(ItemEventoLocalidad tiquete, String loginEmisor, String passEmisor, String loginReceptor) {
        if (dueno == null || !Objects.equals(dueno.getLogin(), loginEmisor) || !dueno.autenticar(passEmisor))
            throw new IllegalStateException("Credenciales emisor inválidas");
        if (tiquete == null || componentes == null || !componentes.contains(tiquete))
            throw new IllegalArgumentException("Componente no pertenece al pase");
    }

    public void transferirPaquete(String loginEmisor, String passEmisor, String loginReceptor) {
        if (dueno == null || !Objects.equals(dueno.getLogin(), loginEmisor) || !dueno.autenticar(passEmisor))
            throw new IllegalStateException("Credenciales emisor inválidas");
    }

    public boolean tieneTiqueteVencidoOTransferido() { return false; }
}