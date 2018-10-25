package br.com.upabicas.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("upabicas");
	
	public EntityManager getEntityManager () {
		
		return managerFactory.createEntityManager();
	}	
}
