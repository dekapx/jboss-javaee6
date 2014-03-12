package com.kapx.jboss.javaee6.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Abstract JPA based DAO class consisting of common data access operations.
 * 
 * @author KAPIL
 * 
 * @param <T>
 *            Type of persistence object.
 * @param <PK>
 *            type of Serializable primary key
 * 
 */
public abstract class AbstractJpaDAO<T, PK extends Serializable> {

	@PersistenceContext(unitName = "mysql-persistence-unit")
	private EntityManager entityManager;

	private Class<T> persistentClass;

	protected AbstractJpaDAO(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public void persist(final T entity) {
		entityManager.persist(entity);
	}

	public void update(final T entity) {
		entityManager.merge(entity);
	}

	public T find(final PK pk, final Class<T> t) {
		return entityManager.find(t, pk);
	}

	public void delete(final T entity) {
		entityManager.remove(entity);
	}

	@SuppressWarnings("unchecked")
	public Collection<T> findAll() {
		return this.entityManager.createQuery("select obj from " + this.persistentClass.getName() + " obj").getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<T> findAll(final int page, final int pageSize) {
		/**
		 * The TypedQuery interface is the preferred when the result type is
		 * known. The Query interface when the result type is Object or in
		 * dynamic queries when the result type may not be known ahead of time.
		 */
		final TypedQuery<T> query = (TypedQuery<T>) entityManager.createQuery("select obj from " + this.persistentClass.getName() + " obj");

		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();
	}
}
