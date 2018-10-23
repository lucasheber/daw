package jcms.daw.jpa.biblioteca.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import um_pra_um.Departamento;
import um_pra_um.Funcionario;

public class TestaOneToMany {

	public static void main(String[] args) {
		EntityManagerFactory entityManager = Persistence.createEntityManagerFactory("biblioteca-atualizada");
	
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Haddad");
		funcionario.setPapel("PT");
		
		Funcionario funcionario2 = new Funcionario();
		funcionario2.setNome("Bolsonaro");
		funcionario2.setPapel("PSL");
		
		Departamento departamento = new Departamento();
		departamento.setNome("Partidario");
		departamento.getFuncionarios().add(funcionario);
		departamento.getFuncionarios().add(funcionario2);
		
		EntityManager manager = entityManager.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(departamento);
		manager.getTransaction().commit();
		
		manager.close();
		entityManager.close();
		
	}
}
