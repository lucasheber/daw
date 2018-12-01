package distribuidora.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import distribuidora.modelo.Usuario;

public class UsuarioDAO {
	
	private EntityManager entityManager = new JPAUtil().getEntityManager();
	
	public boolean existe (Usuario usuario) {
		
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createQuery("from Usuario u where u.login = :pLogin and u.senha = :pSenha");
		
		System.out.println(usuario.getLogin());
		
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		
		boolean existe = !query.getResultList().isEmpty();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return existe;
	}
}
