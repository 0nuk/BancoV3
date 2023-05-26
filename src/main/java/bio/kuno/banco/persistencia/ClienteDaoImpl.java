package bio.kuno.banco.persistencia;

import java.util.Set;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import bio.kuno.banco.config.EMF;


import bio.kuno.banco.modelo.Cliente;

public class ClienteDaoImpl implements ClienteDao{
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ClienteDaoImpl() {
		emf = EMF.getEMF();
	}
	
	@Override
	public void save(Cliente entidad) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(entidad);
		em.getTransaction().commit();
		em.close();		
	}

	@Override
	public Cliente findById(Integer id) {
	
		em = emf.createEntityManager();
		Cliente c = em.find(Cliente.class, id);
		em.close();
		return c;
	}

	@Override
	public Cliente findByIdEager(Integer id) {
		Cliente c;
		em = emf.createEntityManager();
		String jpql = "SELECT c FROM Cliente c LEFT JOIN FETCH c.cuentas WHERE c.id = :id";
		TypedQuery<Cliente> q = em.createQuery(jpql, Cliente.class);
		q.setParameter("id", id);
		try {
 			c = q.getSingleResult();
 		} catch(NoResultException e) {
 			c = null;
 		}
		em.close();
		return c;
	}

	@Override
	public Set<Cliente> findAll() {
		em = emf.createEntityManager();
		String jpql = "SELECT c FROM Cliente c";
		TypedQuery<Cliente> q = em.createQuery(jpql, Cliente.class);
		Set<Cliente> resu = new TreeSet<>(q.getResultList());
		em.close();
		return resu;
	}

	@Override
	public void delete(Cliente entidad) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(entidad);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Set<Cliente> findByValue(String valor) {
		em = emf.createEntityManager();
		String jpql = "SELECT c FROM Cliente c WHERE c.nombre LIKE :value OR c.apellido1 LIKE :value OR c.apellido2 LIKE :value";
		TypedQuery<Cliente> q = em.createQuery(jpql, Cliente.class);
		q.setParameter("value", "%"+valor+"%");
		Set<Cliente> resu = new TreeSet<>(q.getResultList());
		em.close();
		return resu;
	}

	@Override
	public boolean delete(int id) {
		em = emf.createEntityManager();
		Cliente c = em.find(Cliente.class, id);
		try {
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (RollbackException e) {
			return false;
		} finally {
			em.close();
		}
	}
	@Override
	public Cliente merge(Cliente entidad) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Cliente c = em.merge(entidad);
		em.getTransaction().commit();
		em.close();	
		return c;
	}

}
