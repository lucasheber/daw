package distribuidora.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import distribuidora.modelo.Usuario;

public class UsuarioDAO {

	public boolean existe (Usuario usuario) {
		
		EntityManager entityManager = new JPAUtil().getEntityManager();
		
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createQuery("from Usuario u where u.login = :pLogin and u.senha = :pSenha");
		
		
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		
		boolean existe = !query.getResultList().isEmpty();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return existe;
	}
}
