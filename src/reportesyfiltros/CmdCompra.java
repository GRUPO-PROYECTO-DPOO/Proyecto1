package reportesyfiltros;

import enums.TipoItem;

public class CmdCompra {

	  private String loginCliente, idEvento, idLocalidad, asiento;
	  private int cantidad;
	  private TipoItem tipoItem = TipoItem.TIQUETE;
	  public String getLoginCliente(){return loginCliente;} public void setLoginCliente(String v){loginCliente=v;}
	  public String getIdEvento(){return idEvento;} public void setIdEvento(String v){idEvento=v;}
	  public String getIdLocalidad(){return idLocalidad;} public void setIdLocalidad(String v){idLocalidad=v;}
	  public int getCantidad(){return cantidad;} public void setCantidad(int v){cantidad=v;}
	  public String getAsiento(){return asiento;} public void setAsiento(String v){asiento=v;}
	  public TipoItem getTipoItem(){return tipoItem;} public void setTipoItem(TipoItem v){tipoItem=v;}
}
