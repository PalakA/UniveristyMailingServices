package com.mvc.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mvc.pojo.MailAssociate;
import com.mvc.pojo.user;

@Repository
public class MailAssociateDAO extends DAO {

	// Get specific User by userid
	@SuppressWarnings({ "deprecation" })
	public MailAssociate getUserById(int userid) {

		return (MailAssociate) getSession().createCriteria(MailAssociate.class, "mailAssociate")
				.createAlias("mailAssociate.user", "user").add(Restrictions.eq("user.userid", userid)).uniqueResult();
	}

	// Update Login Details
	public boolean updateDetails(String password, String firstName, String lastName, String mobileNumber,
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

	// Update Login Details
	public boolean updateDetails(String firstName, String lastName, String mobileNumber,
			user user) {

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
