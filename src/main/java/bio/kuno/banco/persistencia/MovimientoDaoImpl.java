package bio.kuno.banco.persistencia;

import java.util.Set;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import bio.kuno.banco.modelo.Movimiento;
import bio.kuno.banco.config.EMF;


public class MovimientoDaoImpl implements MovimientoDao{
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public MovimientoDaoImpl() {
		emf = EMF.getEMF();
	}
	
	@Override
	public void save(Movimiento entidad) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(entidad);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Movimiento findById(Integer id) {
		em = emf.createEntityManager();
		Movimiento m = em.find(Movimiento.class, id);
		em.close();
		return m;
	}

	@Override
	public Movimiento findByIdEager(Integer id) {
		return findById(id);
	}

	@Override
	public Set<Movimiento> findAll() {
		em = emf.createEntityManager();
		String jpql = "SELECT m FROM Movimiento m";
		TypedQuery<Movimiento> q = em.createQuery(jpql, Movimiento.class);
		Set<Movimiento> resu = new TreeSet<>(q.getResultList());
		em.close();
		return resu;
	}

	@Override
	public void delete(Movimiento entidad) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(entidad);
		em.getTransaction().commit();
		em.close();
	}

}
