package repos;

import java.util.List;

import transacciones.TransaccionCompra;

public interface TransaccionRepo {
	void save(TransaccionCompra tx);
	List<TransaccionCompra> findByCliente(String login);

}
