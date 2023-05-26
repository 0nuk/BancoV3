package bio.kuno.banco.persistencia;

import java.util.Set;

public interface GenericoDao <K, E> {
	
	void save(E entidad);
	
	E findById(K id);
	
	E findByIdEager(K id);
	
	Set<E> findAll();
	
	void delete(E entidad);
}
