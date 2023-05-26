package bio.kuno.banco.negocio;

import java.io.Serializable;
import java.util.Set;

import bio.kuno.banco.modelo.Cliente;

public interface ClienteNegocio extends Serializable{
	
	Set<Cliente> getClientes(String nombre);
	
	Cliente getClienteConCuentas(int idCliente);

	Cliente getClienteConCuentas(Cliente cliente);

	Set<Cliente> getAllClientes();
	
	boolean eliminarCliente(int idCliente);
	
	Cliente actualizarCliente(Cliente cliente);

}
