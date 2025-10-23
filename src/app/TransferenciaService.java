package app;

public interface TransferenciaService {
	void reembolsarPorAdmin(String idEvento);
	void reembolsarPorOrganizadorAutorizado(String idEvento);
}
