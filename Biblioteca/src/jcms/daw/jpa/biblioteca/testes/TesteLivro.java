package jcms.daw.jpa.biblioteca.testes;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jcms.daw.jpa.biblioteca.modelo.Livro;

public class TesteLivro {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager manager = factory.createEntityManager();
		
		Livro livro = new Livro();
		livro.setIsbn("346346346");
		livro.setNome("Java para Web");
		livro.setDatalancamento(Calendar.getInstance());
		livro.setPreco(99.20);
		livro.setCount(5);
		Livro.setTeste(5);
		
		manager.getTransaction().begin();
		
		manager.persist(livro);
		
		manager.getTransaction().commit();
		
		
		
	}
}
