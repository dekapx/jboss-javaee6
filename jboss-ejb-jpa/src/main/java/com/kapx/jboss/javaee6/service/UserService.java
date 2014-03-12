package com.kapx.jboss.javaee6.service;

import java.util.Collection;

import com.kapx.jboss.javaee6.entity.Role;
import com.kapx.jboss.javaee6.entity.User;

public interface UserService {
	User save(User entity);

	User findUser(Long pk);

	Role save(Role entity);

	Role findByRoleName(String roleName);

	Collection<Role> findAllRoles();
}
