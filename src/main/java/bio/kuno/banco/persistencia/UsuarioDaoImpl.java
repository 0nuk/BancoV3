package bio.kuno.banco.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bio.kuno.banco.modelo.Usuario;
import bio.kuno.banco.config.EMF;

public class UsuarioDaoImpl implements UsuarioDao {
	EntityManagerFactory emf;
	EntityManager em;

	public UsuarioDaoImpl() {
		emf = EMF.getEMF();
	}

	@Override
	public boolean save(Usuario usuario) {
		char[] clave = BCrypt.withDefaults().hashToChar(12, usuario.getPassword().toCharArray());
		String pwdEnc = String.valueOf(clave);
		usuario.setPassword(pwdEnc);
		
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch(PersistenceException e) {
			return false;
		} finally {
			em.close();
		}	
	}
	
	@Override
	public Usuario valida(String usuario, String password) {
		Usuario u;
		em = emf.createEntityManager();
		String jpql = "SELECT u FROM Usuario u WHERE u.usuario = :usuario";
		TypedQuery<Usuario> q = em.createQuery(jpql, Usuario.class);
		q.setParameter("usuario", usuario);
		try {
			u = q.getSingleResult();
			if(!BCrypt.verifyer().verify(password.toCharArray(), u.getPassword().toCharArray()).verified) {
				u = null;
			}
		} catch (NoResultException e) {
			u = null;
		} 
		em.close();
		return u;
	}
}
