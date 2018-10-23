package jcms.daw.jpa.biblioteca.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import um_pra_um.Estado;
import um_pra_um.Governador;

public class TesteGovEstado {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager manager = factory.createEntityManager();
		
		Governador gov = new Governador();
		gov.setNome("João de Carandaí");
		
		Estado estado = new Estado();
		estado.setGovernador(gov);
		estado.setNome("Minas Gerais");
		
		manager.getTransaction().begin();
		manager.persist(gov);
		manager.persist(estado);
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
		
	}
}
