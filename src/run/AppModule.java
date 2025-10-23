package run;
import app.*;
import implementacion.*;
 
public class AppModule {
	private final TiqueteraContext ctx = new TiqueteraContext();
	private final AuthService authService = new AuthServiceSimple(ctx);
	private final VenueService venueService = new VenueServiceSimple(ctx);
	private final EventoService eventoService = new EventoServiceSimple(ctx);
	private final CompraService compraService = new CompraServiceSimple(ctx);
	private final TransferenciaService transferenciaService = new TransferenciaServiceSimple(ctx);
	private final ReporteService reporteService = new ReporteServiceSimple(ctx);		
		public TiqueteraContext getContext() {return ctx;}
		public AuthService auth() {return authService;}
		public VenueService venues() {return venueService;}
		public EventoService evento() {return eventoService;}
		public CompraService compras() {return compraService;}
		public TransferenciaService transferencias() {return transferenciaService;}
		public ReporteService reportes() {return reporteService;}
		
		public AppModule setCuotaEmisionFija(double cuota) {
			if (cuota < 0) throw new IllegalArgumentException("cuota no puede ser < 0");
			ctx.cuotaEmisionFija = cuota;
			return this;
		}
		public AppModule setCargoServicio(enums.TipoEvento tipo, double porcentaje) {
			if (tipo == null) throw new IllegalArgumentException("tipo nulo");
			if (porcentaje < 0 || porcentaje > 1) throw new IllegalArgumentException("porcentaje fuera de rango");
			ctx.cargoPorTipo.put(tipo, porcentaje);
			return this;
		}			
	}
	
	
	
	

