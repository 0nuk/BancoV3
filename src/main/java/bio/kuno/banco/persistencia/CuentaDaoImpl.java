package bio.kuno.banco.persistencia;

import java.util.Set;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import bio.kuno.banco.modelo.Cuenta;
import bio.kuno.banco.config.EMF;

public class CuentaDaoImpl implements CuentaDao{
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public CuentaDaoImpl() {
		emf = EMF.getEMF();
	}
	
	@Override
	public void save(Cuenta entidad) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(entidad);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Cuenta findById(Integer id) {
		em = emf.createEntityManager();
		Cuenta c = em.find(Cuenta.class, id);
		em.close();
		return c;
		
	}

	@Override
	public Cuenta findByIdEager(Integer id) {
		em = emf.createEntityManager();
		String jpql = "SELECT c FROM Cuenta c LEFT JOIN FETCH c.extractos LEFT JOIN FETCH c.tarjetas WHERE c.id = :id";
		TypedQuery<Cuenta> q = em.createQuery(jpql, Cuenta.class);
		q.setParameter("id", id);
		Cuenta c = q.getSingleResult();
		em.close();
		return c;
	}

	@Override
	public Set<Cuenta> findAll() {
		em = emf.createEntityManager();
		String jpql = "SELECT c FROM Cuenta";
		TypedQuery<Cuenta> q = em.createQuery(jpql, Cuenta.class);
		Set<Cuenta> resu = new TreeSet<>(q.getResultList());
		em.close();
		return resu;
	}

	@Override
	public void delete(Cuenta entidad) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(entidad);
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public Set<Cuenta> findByIdCliente(int idCliente){
		em = emf.createEntityManager();
		String jpql = "SELECT c FROM Cuenta c WHERE c.cliente.id = :id";
		TypedQuery<Cuenta> q = em.createQuery(jpql, Cuenta.class);
		q.setParameter("id", idCliente);
		Set<Cuenta> c = new TreeSet<>(q.getResultList());
		em.close();
		return c;
	}

}
