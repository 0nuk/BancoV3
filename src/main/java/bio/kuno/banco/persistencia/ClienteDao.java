package bio.kuno.banco.persistencia;

import java.util.Set;

import bio.kuno.banco.modelo.Cliente;

public interface ClienteDao extends GenericoDao<Integer, Cliente>{
	
	Set<Cliente> findByValue(String valor);
	
	public boolean delete(int id);
	
	public Cliente merge(Cliente entidad);
}
