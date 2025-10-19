package repos;

import java.util.Optional;

import dinero.SaldoVirtual;

public interface SaldoRepo {
	Optional<SaldoVirtual> findByUsuario(String login);
	void save(SaldoVirtual saldo);

}
