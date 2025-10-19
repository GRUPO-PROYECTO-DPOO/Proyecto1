package app;

import dtos.*;
import enums.*;

public interface CompraService {
	Cotizacion cotizar(CmdCompra cmd);
	Recibo comprar(CmdCompra cmd);
	double cargoServicio(TipoEvento tipo);

}
