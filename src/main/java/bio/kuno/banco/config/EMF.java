package bio.kuno.banco.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	private static EntityManagerFactory emf;
	public static EntityManagerFactory getEMF() {
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("banco");
		}
		return emf;
	}
}
