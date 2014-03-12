package com.kapx.jboss.javaee6.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kapx.jboss.javaee6.dao.AbstractJpaDAO;
import com.kapx.jboss.javaee6.dao.RoleDAO;
import com.kapx.jboss.javaee6.entity.Role;

@Named("roleDAO")
public class RoleDAOImpl extends AbstractJpaDAO<Role, Long> implements RoleDAO {
	private static final Logger LOG = LoggerFactory.getLogger(RoleDAOImpl.class.getName());

	@PersistenceContext(unitName = "mysql-persistence-unit")
	private EntityManager entityManager;

	public RoleDAOImpl() {
		super(Role.class);
	}

	public Role save(final Role entity) {
		super.persist(entity);

		LOG.info("persist Role object with ID {} ", entity.getId());
		return entity;
	}

	@Override
	public Role findByRole(final String role) {
		final TypedQuery<Role> query = (TypedQuery<Role>) entityManager.createQuery("select r from Role r where r.role = ?1", Role.class);
		query.setParameter(1, role);

		final List<Role> users = query.getResultList();
		return users.iterator().next();
	}

	public Collection<Role> findAllRoles() {
		return super.findAll();
	}

}
