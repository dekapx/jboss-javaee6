package com.kapx.jboss.javaee6.dao;

import java.util.Collection;

import com.kapx.jboss.javaee6.entity.Student;

public interface StudentDAO {
	Student save(final Student entity);

	void delete(final Student entity);

	Collection<Student> findAllStudents();
}
