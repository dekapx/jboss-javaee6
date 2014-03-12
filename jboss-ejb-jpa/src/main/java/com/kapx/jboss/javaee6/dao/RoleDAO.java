package com.kapx.jboss.javaee6.dao;

import java.util.Collection;

import com.kapx.jboss.javaee6.entity.Role;

public interface RoleDAO {
	Role save(Role entity);

	Role findByRole(String role);

	Collection<Role> findAllRoles();
}
