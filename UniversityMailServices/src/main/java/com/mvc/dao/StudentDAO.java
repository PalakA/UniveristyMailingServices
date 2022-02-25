package com.mvc.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mvc.pojo.Student;
import com.mvc.pojo.user;

@Repository
public class StudentDAO extends DAO {

	// Get Student by ID
	@SuppressWarnings({ "deprecation" })
	public Student getStudentById(int userid) {

		try {

			return (Student) getSession().createCriteria(Student.class, "Student").createAlias("student.user", "user")
					.add(Restrictions.eq("user.userid", userid)).uniqueResult();

		} catch (HibernateException e) {

			e.printStackTrace();
			rollback();
			return null;

		} finally {
			close();
		}

	}

	// Update Student Login Details
	public boolean updateStudentDetails(String password, String firstName, String lastName, String mobileNumber,
			user user) {

		try {

			user.setPassword(password);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setMobileNumber(mobileNumber);

			Session session = getSession();
			Transaction t = session.beginTransaction();
			session.saveOrUpdate(user);
			t.commit();
			return true;

		} catch (HibernateException e) {

			e.printStackTrace();
			rollback();
			return false;

		} finally {

			close();
		}
	}

	// Update Student Login Details
	public boolean updateStudentDetails(String firstName, String lastName, String mobileNumber, user user) {

		try {

			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setMobileNumber(mobileNumber);

			Session session = getSession();
			Transaction t = session.beginTransaction();
			session.saveOrUpdate(user);
			t.commit();
			return true;

		} catch (HibernateException e) {

			e.printStackTrace();
			rollback();
			return false;

		} finally {

			close();
		}
	}
}
