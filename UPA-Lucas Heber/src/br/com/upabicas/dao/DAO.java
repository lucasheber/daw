package br.com.upabicas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> {

	private Class<T> classe;
	private EntityManager entityManager;
	
	public DAO(Class<T> classe) {
		this.classe = classe;
	}
	
	public boolean insert (T objectT) {
		this.entityManager = new JPAUtil().getEntityManager(); 
		
		this.entityManager.getTransaction().begin();
		
		this.entityManager.persist(objectT);
		
		this.entityManager.getTransaction().commit();
		
		this.entityManager.close();
		
		return true;
	}
	
	public boolean update (T objectT) {
		
		this.entityManager = new JPAUtil().getEntityManager();  
		
		this.entityManager.getTransaction().begin();
		
		this.entityManager.merge(objectT);
		
		this.entityManager.getTransaction().commit();
		
		this.entityManager.close();
		
		return true;
	}
	
	public boolean remove (T objectT) {
		
		this.entityManager = new JPAUtil().getEntityManager();  
		
		this.entityManager.getTransaction().begin();
		
		this.entityManager.remove(this.entityManager.merge(objectT));
		
		this.entityManager.getTransaction().commit();
		
		this.entityManager.close();
		
		return true;
	}
	
	public List<T> list () {
		this.entityManager = new JPAUtil().getEntityManager(); 
		
		CriteriaQuery<T> query = this.entityManager.getCriteriaBuilder().createQuery(classe);
		
		query.select(query.from(classe));
		
		return this.entityManager.createQuery(query).getResultList();
	}
	
	public T searchById (Long id) {
		this.entityManager = new JPAUtil().getEntityManager(); 
		
		return this.entityManager.find(classe, id);
	}
	
}
