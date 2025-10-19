package run;
import enums.*;
import escenariosyventas.*;
import tiquetes.*;
import transacciones.*;
import usuarios.*;
import java.util.*;

public class TiqueteraContext {
	public final Map<String, Usuario> usuarios = new HashMap<>();
	public final Map<String, Venue> venues = new HashMap<>();
	  public final Map<String, SolicitudVenue> solicitudes = new HashMap<>();
	  public final Map<String, Evento> eventos = new HashMap<>();
	  public final Map<String, Localidad> localidades = new HashMap<>(); // key: idEvento#idLocalidad
	  public final Map<String, Tiquete> tiquetes = new HashMap<>();
	  public final List<TransaccionCompra> transacciones = new ArrayList<>();

	  // parametros precio
	  public final EnumMap<TipoEvento, Double> cargoPorTipo  = new EnumMap<>(TipoEvento.class);
	  public Double cuotaEmisionFija = null;
	  
	  public static String keyLoc(String idEvento, String idLocalidad) {return idEvento + "#" + idLocalidad;}
	  
	  //Helpers
	  public Optional<Usuario> findUsuario(String login) {return Optional.ofNullable(usuarios.get(login));}
	  public Optional<Venue> findVenue(String id) {return Optional.ofNullable(venues.get(id));}
	  public Optional<SolicitudVenue> findSolicitud(String id) {return Optional.ofNullable(solicitudes.get(id));}
	  public Optional<Evento> findEvento(String id) {return Optional.ofNullable(eventos.get(id));}
	  public Optional<Localidad> findLocalidad(String idEvento, String idLoc) {return Optional.ofNullable(localidades.get(keyLoc(idEvento, idLoc)));}
	  
	  public List<Tiquete> tiquetesPorEvento(String idEvento){
		  List<Tiquete> res = new ArrayList<>();
		  for (Tiquete t: tiquetes.values()) if (t.getEvento().getId().equals(idEvento)) res.add(t);
		  return res;
	  }
}
