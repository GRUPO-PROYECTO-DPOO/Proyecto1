package app;

import reportesyfiltros.*;

public interface ReporteService {
	EstadoFinanciero finanzasOrganizador(String loginOrg, FiltroFinanzas f);
	GananciasGenerales gananciasAdministrador(FiltroGanancias f);
}
