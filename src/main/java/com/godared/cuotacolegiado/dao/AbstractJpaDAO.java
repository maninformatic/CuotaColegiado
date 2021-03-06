package com.godared.cuotacolegiado.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.StoredProcedureQuery;

public class AbstractJpaDAO<T extends Serializable> {
	 private Class<T> clazz;

	    @PersistenceContext
	    protected EntityManager entityManager;

	    public final void setClazz(final Class<T> clazzToSet) {
	        this.clazz = clazzToSet;
	    }
	    public T findOne(final int id) {
	        return entityManager.find(clazz, id);
	    }
	    @SuppressWarnings("unchecked")
	    public List<T> findAll() {
	    	 //from "+ clazz.getName()
	        return entityManager.createQuery("from "+ clazz.getName()).getResultList();
	    }
	    public void create(final T entity) {
	        entityManager.persist(entity);
	    }

	    public T update(final T entity) {
	        return entityManager.merge(entity);
	    }
	    public void delete(final T entity) {
	        entityManager.remove(entity);
	    }

	    public void deleteById(final int entityId) {
	        final T entity = findOne(entityId);
	        delete(entity);
	    }
	  
}

