package com.kapx.jboss.javaee6.service.impl;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import com.kapx.jboss.javaee6.dao.StudentDAO;
import com.kapx.jboss.javaee6.entity.Student;
import com.kapx.jboss.javaee6.service.StudentService;

@Stateless
@Local(StudentService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StudentServiceBean implements StudentService {

	@Inject
	private StudentDAO studentDAO;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Student save(final Student entity) {
		return studentDAO.save(entity);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(final Student entity) {
		studentDAO.delete(entity);
	}

	@Override
	public Collection<Student> findAllStudents() {
		return studentDAO.findAllStudents();
	}

}
