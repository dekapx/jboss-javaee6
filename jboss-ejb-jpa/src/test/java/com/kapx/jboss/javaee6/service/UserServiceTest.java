package com.kapx.jboss.javaee6.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kapx.jboss.javaee6.dao.AbstractJpaDAO;
import com.kapx.jboss.javaee6.dao.RoleDAO;
import com.kapx.jboss.javaee6.dao.UserDAO;
import com.kapx.jboss.javaee6.dao.impl.RoleDAOImpl;
import com.kapx.jboss.javaee6.dao.impl.UserDAOImpl;
import com.kapx.jboss.javaee6.entity.Role;
import com.kapx.jboss.javaee6.entity.User;
import com.kapx.jboss.javaee6.service.impl.UserServiceBean;

@RunWith(Arquillian.class)
public class UserServiceTest {
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceTest.class.getName());

	@Deployment(name = "user-role-test")
	public static Archive<?> createTestArchive() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "user-role-test.war");
		war.addClass(UserService.class);
		war.addClass(UserServiceBean.class);

		war.addClass(Role.class);
		war.addClass(User.class);

		war.addClass(AbstractJpaDAO.class);
		war.addClass(RoleDAO.class);
		war.addClass(RoleDAOImpl.class);
		war.addClass(UserDAO.class);
		war.addClass(UserDAOImpl.class);

		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		// war.addAsResource("META-INF/test-persistence.xml",
		// "META-INF/persistence.xml");
		// war.addAsWebInfResource("test-mysql-ds.xml");

		war.addAsResource("META-INF/test-h2db-persistence.xml", "META-INF/persistence.xml");
		war.addAsWebInfResource("test-h2db-ds.xml");
		return war;
	}

	@EJB
	private UserService userServiceEJB;

	@Test
	@InSequence(1)
	public void test_insert_roles() throws Exception {
		Role entity = null;

		entity = userServiceEJB.save(new Role("ROLE_ADMIN"));
		assertNotNull(entity);
		assertNotNull(entity.getId());
		LOG.info("Create new Role with ID {}", entity.getId());

		entity = userServiceEJB.save(new Role("ROLE_USER"));
		assertNotNull(entity);
		assertNotNull(entity.getId());
		LOG.info("Create new Role with ID [{}]", entity.getId());
	}

	@Test
	@InSequence(2)
	public void test_findAllRoles() throws Exception {
		final Collection<Role> roles = userServiceEJB.findAllRoles();
		assertNotNull(roles);
		assertEquals(2, roles.size());
		LOG.info("Number of roles are [{}] returned by findAllRoles API", roles.size());
	}

	@Test
	@InSequence(3)
	public void test_findByRole() throws Exception {
		final String roleName = "ROLE_ADMIN";
		Role entity = userServiceEJB.findByRoleName(roleName);
		assertNotNull(entity);
		assertEquals(roleName, entity.getRole());

		LOG.info("Find Role with ID [{}] and role name [{}]", entity.getId(), entity.getRole());
	}

	@Test
	@InSequence(4)
	public void test_insert_user() throws Exception {
		User entity = null;
		Role role = null;

		role = userServiceEJB.findByRoleName("ROLE_ADMIN");

		entity = userServiceEJB.save(new User("admin", "password", "Admin", "User", "admin@myemail.com", role));
		assertNotNull(entity);
		assertNotNull(entity.getId());
		LOG.info("Create new User entity with ID [{}] and username [{}]", entity.getId(), entity.getUsername());

		role = userServiceEJB.findByRoleName("ROLE_USER");

		entity = userServiceEJB.save(new User("test-user", "password", "Test", "User", "test-user@myemail.com", role));
		assertNotNull(entity);
		assertNotNull(entity.getId());
		LOG.info("Create new User entity with ID [{}] and username [{}]", entity.getId(), entity.getUsername());
	}

	@Test
	@InSequence(5)
	public void test_find_user_byPK() throws Exception {
		final Long pk = 1L;
		User entity = userServiceEJB.findUser(pk);
		assertNotNull(entity);
		assertEquals("admin", entity.getUsername());
		LOG.info("Find User by primary key [{}] with username [{}]", pk, entity.getUsername());
	}
}
