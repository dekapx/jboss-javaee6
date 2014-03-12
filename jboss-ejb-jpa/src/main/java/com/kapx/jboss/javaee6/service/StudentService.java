package com.kapx.jboss.javaee6.service;

import java.util.Collection;

import com.kapx.jboss.javaee6.entity.Student;

public interface StudentService {
	Student save(Student entity);

	void delete(final Student entity);

	Collection<Student> findAllStudents();
}
