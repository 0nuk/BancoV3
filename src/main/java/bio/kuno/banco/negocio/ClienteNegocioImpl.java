package bio.kuno.banco.negocio;

import java.io.Serializable;
import java.util.Set;

import bio.kuno.banco.modelo.Cliente;
import bio.kuno.banco.persistencia.ClienteDao;
import bio.kuno.banco.persistencia.ClienteDaoImpl;

public class ClienteNegocioImpl implements ClienteNegocio, Serializable{

	private static final long serialVersionUID = 1L;
	private ClienteDao clienteDao;
	
	public ClienteNegocioImpl() {
		clienteDao = new ClienteDaoImpl();
	}
	
	@Override
	public Set<Cliente> getClientes(String nombre) {
		return clienteDao.findByValue(nombre);
	}

	@Override
	public Cliente getClienteConCuentas(int idCliente) {
		return clienteDao.findByIdEager(idCliente);
	}

	@Override
	public Cliente getClienteConCuentas(Cliente cliente) {
		return getClienteConCuentas(cliente.getId());
	}

	@Override
	public Set<Cliente> getAllClientes() {
		return clienteDao.findAll();
	}

	@Override
	public boolean eliminarCliente(int idCliente) {
		return clienteDao.delete(idCliente);
	}

	@Override
	public Cliente actualizarCliente(Cliente cliente) {
		return clienteDao.merge(cliente);
	}
}
