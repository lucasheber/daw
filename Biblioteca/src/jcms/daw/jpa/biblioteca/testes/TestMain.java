package jcms.daw.jpa.biblioteca.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jcms.daw.jpa.biblioteca.modelo.Editora;

public class TestMain {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager manager = factory.createEntityManager();
		
		Editora editora = new Editora();
		editora.setEmail("abril@gmail.com");
		editora.setNome("Editora Abril");
		
		// Abre a transação
		manager.getTransaction().begin();
		// Persiste o objeto
		//manager.persist(editora);
		// Commita a transação
		//manager.getTransaction().commit();
		
		Editora editora1 = manager.find(Editora.class, 1L);
		System.out.println(editora1);
		
		editora1.setEmail("elsevier@gmail.com");
		editora1.setNome("Elsevier");
		
		manager.merge(editora1);
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		// Persiste o objeto
		manager.persist(editora);
		// Commita a transação
		manager.getTransaction().commit();
		
		
		@SuppressWarnings("unchecked")
		List<Editora> editoras = manager.createQuery("SELECT e FROM Editora e").getResultList();
		
		for (Editora ed : editoras) {
			System.out.println(ed);
		}
		
		
		/*manager.getTransaction().begin();
		// Remove o objeto
		manager.remove(editora1);
		// Commita a transação
		manager.getTransaction().commit();*/
		
		
		manager.close();
		factory.close();
	}

}
