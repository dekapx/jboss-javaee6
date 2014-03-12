package com.kapx.jboss.javaee6.service.impl;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import com.kapx.jboss.javaee6.dao.RoleDAO;
import com.kapx.jboss.javaee6.dao.UserDAO;
import com.kapx.jboss.javaee6.entity.Role;
import com.kapx.jboss.javaee6.entity.User;
import com.kapx.jboss.javaee6.service.UserService;

@Stateless
@Local(UserService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserServiceBean implements UserService {

	@Inject
	private UserDAO userDAO;

	@Inject
	private RoleDAO roleDAO;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User save(final User entity) {
		return userDAO.save(entity);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Role findByRoleName(final String roleName) {
		return roleDAO.findByRole(roleName);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Role save(Role entity) {
		return roleDAO.save(entity);
	}

	@Override
	public Collection<Role> findAllRoles() {
		return roleDAO.findAllRoles();
	}

	@Override
	public User findUser(final Long pk) {
		return userDAO.findUser(pk);
	}

}
