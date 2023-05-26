package bio.kuno.banco.persistencia;

import java.util.Set;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import bio.kuno.banco.modelo.TipoMovimiento;
import bio.kuno.banco.config.EMF;

public class TipoMovimientoDaoImpl implements TipoMovimientoDao{
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public TipoMovimientoDaoImpl() {
		emf = EMF.getEMF();
	}
	
	@Override
	public void save(TipoMovimiento entidad) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(entidad);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public TipoMovimiento findById(Integer id) {
		em = emf.createEntityManager();
		TipoMovimiento tm = em.find(TipoMovimiento.class, id);
		em.close();
		return tm;
	}

	@Override
	public TipoMovimiento findByIdEager(Integer id) {
		em = emf.createEntityManager();
		String jpql = "SELECT tp FROM TipoMovimiento tp LEFT JOIN FETCH tp.movimientos WHERE tp.id = :id";
		TypedQuery<TipoMovimiento> q = em.createQuery(jpql, TipoMovimiento.class);
		q.setParameter("id", id);
		TipoMovimiento tp = q.getSingleResult();
		em.close();
		return tp;
	}

	@Override
	public Set<TipoMovimiento> findAll() {
		em = emf.createEntityManager();
		String jpql = "SELECT tp FROM TipoMovimiento tp";
		TypedQuery<TipoMovimiento> q = em.createQuery(jpql, TipoMovimiento.class);
		Set<TipoMovimiento> resu = new TreeSet<>(q.getResultList());
		em.close();
		return resu;
	}

	@Override
	public void delete(TipoMovimiento entidad) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(entidad);
		em.getTransaction().commit();
		em.close();
	}
}
