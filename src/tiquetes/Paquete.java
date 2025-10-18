package tiquetes;
import java.util.List;

public interface Paquete {
    String getId();
    List<Tiquete> getTiquetes();
}