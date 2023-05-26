package bio.kuno.banco.negocio;

import java.io.Serializable;
import java.util.Set;

import bio.kuno.banco.modelo.Tarjeta;
import bio.kuno.banco.persistencia.TarjetaDao;
import bio.kuno.banco.persistencia.TarjetaDaoImpl;

public class TarjetaNegocioImpl implements TarjetaNegocio, Serializable{
	
	private static final long serialVersionUID = 1L;
	private TarjetaDao tarjetaDao;
	
	public TarjetaNegocioImpl() {
		tarjetaDao = new TarjetaDaoImpl();
	}

	@Override
	public Set<Tarjeta> getTarjetas(int idCliente) {
		return tarjetaDao.findByIdCliente(idCliente);
	}

}
