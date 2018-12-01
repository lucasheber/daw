package distribuidora.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("distribuidora");;
	
	public EntityManager getEntityManager () {
		
		return managerFactory.createEntityManager();
	}
}