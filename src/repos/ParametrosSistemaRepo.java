package repos;

import enums.TipoEvento;

public interface ParametrosSistemaRepo {
	double cargoServicioDe(TipoEvento tipo);
	double cuotaEmisonFija();
	void setCargoServicio(TipoEvento tipo, double porcentaje);
	void setCuotaEmisionFija(double monto);

}
