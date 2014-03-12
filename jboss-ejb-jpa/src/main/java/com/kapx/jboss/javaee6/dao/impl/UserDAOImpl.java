package com.kapx.jboss.javaee6.dao.impl;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kapx.jboss.javaee6.dao.AbstractJpaDAO;
import com.kapx.jboss.javaee6.dao.UserDAO;
import com.kapx.jboss.javaee6.entity.User;

@Named("userDAO")
public class UserDAOImpl extends AbstractJpaDAO<User, Long> implements UserDAO {

	private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class.getName());

	public UserDAOImpl() {
		super(User.class);
	}

	@Override
	public User save(final User entity) {
		super.persist(entity);

		LOG.info("persist User object with ID {} ", entity.getId());
		return entity;
	}

	public void delete(final User entity) {
		LOG.info("Removing User entity for ID {}", entity.getId());
		super.delete(entity);
	}

	@Override
	public User findUser(final Long pk) {
		return super.find(pk, User.class);
	}
}
