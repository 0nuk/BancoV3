package bio.kuno.banco.negocio;

import java.io.Serializable;
import java.util.Set;

import bio.kuno.banco.modelo.Cliente;
import bio.kuno.banco.modelo.Cuenta;
import bio.kuno.banco.persistencia.CuentaDao;
import bio.kuno.banco.persistencia.CuentaDaoImpl;

public class CuentaNegocioImpl implements CuentaNegocio, Serializable {
	private static final long serialVersionUID = 1L;
	private CuentaDao cuentaDao;
	
	public CuentaNegocioImpl() {
		cuentaDao = new CuentaDaoImpl();
	}
	
	@Override
	public Cuenta getCuenta(int id) {
		return cuentaDao.findByIdEager(id);
	}
	
	@Override
	public Set<Cuenta> getCuentas(Cliente cliente){
		return cuentaDao.findByIdCliente(cliente.getId());
	}
}
