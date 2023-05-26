package bio.kuno.banco.persistencia;

import java.util.Set;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import bio.kuno.banco.modelo.Tarjeta;
import bio.kuno.banco.config.EMF;

public class TarjetaDaoImpl implements TarjetaDao {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public TarjetaDaoImpl() {
		emf = EMF.getEMF();
	}
	
	@Override
	public void save(Tarjeta entidad) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(entidad);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Tarjeta findById(Integer id) {
		em = emf.createEntityManager();
		Tarjeta t = em.find(Tarjeta.class, id);
		em.close();
		return t;
	}

	@Override
	public Tarjeta findByIdEager(Integer id) {
		em = emf.createEntityManager();
		String jpql = "SELECT t FROM Tarjeta t LEFT JOIN FETCH t.movimientos WHERE t.id = :id";
		TypedQuery<Tarjeta> q = em.createQuery(jpql, Tarjeta.class);
		q.setParameter("id", id);
		Tarjeta t = q.getSingleResult();
		em.close();
		return t;
	}

	@Override
	public Set<Tarjeta> findAll() {
		em = emf.createEntityManager();
		String jpql = "SELECT t FROM Tarjeta t";
		TypedQuery<Tarjeta> q = em.createQuery(jpql, Tarjeta.class);
		Set<Tarjeta> resu = new TreeSet<>(q.getResultList());
		em.close();
		return resu;
	}

	@Override
	public void delete(Tarjeta entidad) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(entidad);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Set<Tarjeta> findByIdCliente(int idCliente) {
		em = emf.createEntityManager();
		String jpql = "SELECT t FROM Tarjeta t WHERE t.cuenta.cliente.id = :id";
		TypedQuery<Tarjeta> q = em.createQuery(jpql, Tarjeta.class);
		q.setParameter("id", idCliente);
		Set<Tarjeta> resu = new TreeSet<>(q.getResultList());
		em.close();
		return resu;
	}
}
