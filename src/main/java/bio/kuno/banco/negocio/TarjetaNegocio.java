package bio.kuno.banco.negocio;

import java.io.Serializable;
import java.util.Set;

import bio.kuno.banco.modelo.Tarjeta;

public interface TarjetaNegocio extends Serializable{
	
	public Set<Tarjeta> getTarjetas(int idCliente);
	
}
