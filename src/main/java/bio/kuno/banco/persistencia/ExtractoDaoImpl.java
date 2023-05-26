package bio.kuno.banco.persistencia;

import java.util.Set;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


import bio.kuno.banco.modelo.Cuenta;
import bio.kuno.banco.modelo.Extracto;
import bio.kuno.banco.config.EMF;


public class ExtractoDaoImpl implements ExtractoDao{
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ExtractoDaoImpl() {
		emf = EMF.getEMF();
	}
	
	@Override
	public void save(Extracto entidad) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(entidad);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Extracto findById(Integer id) {
		em = emf.createEntityManager();
		Extracto e = em.find(Extracto.class, id);
		em.close();
		return e;	
	}

	@Override
	public Extracto findByIdEager(Integer id) {
		em = emf.createEntityManager();
		String jpql = "SELECT e FROM Extracto e LEFT JOIN FETCH e.movimientos WHERE e.id = :id";
		TypedQuery<Extracto> q = em.createQuery(jpql, Extracto.class);
		q.setParameter("id", id);
		Extracto e = q.getSingleResult();
		em.close();
		return e;
	}

	@Override
	public Set<Extracto> findAll() {
		em = emf.createEntityManager();
		String jpql = "SELECT e FROM Extracto e";
		TypedQuery<Extracto> q = em.createQuery(jpql, Extracto.class);
		Set<Extracto> resu = new TreeSet<>(q.getResultList());
		em.close();
		return resu;
	}

	@Override
	public void delete(Extracto entidad) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(entidad);
		em.getTransaction().commit();
		em.close();
	}
	
	public Extracto extractoPorFecha(Cuenta c, int anyo, int mes) {
		em = emf.createEntityManager();
		Extracto e;
		String jpql = "SELECT e FROM Extracto e JOIN FETCH e.movimientos JOIN FETCH e.cuenta cu JOIN FETCH cu.tarjetas m WHERE e.cuenta = :cuenta AND e.anyo = :anyo AND e.mes = :mes";
		TypedQuery<Extracto> q = em.createQuery(jpql, Extracto.class);
		q.setParameter("cuenta", c);
		q.setParameter("anyo", anyo);
		q.setParameter("mes", mes);
		try {
			e = q.getSingleResult();
		} catch(NoResultException exc) {
			e = null;
		}
		em.close();
		return e;
	}
}
