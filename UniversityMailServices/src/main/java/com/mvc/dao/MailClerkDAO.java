package com.mvc.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mvc.pojo.MailroomClerk;
import com.mvc.pojo.user;

@Repository
public class MailClerkDAO extends DAO {

	// Get specific User by userid
	@SuppressWarnings({ "deprecation" })
	public MailroomClerk getUserById(int userid) {

		return (MailroomClerk) getSession().createCriteria(MailroomClerk.class, "mailroomClerk")
				.createAlias("mailroomClerk.user", "user").add(Restrictions.eq("user.userid", userid)).uniqueResult();
	}

	// Update Login Details
	public boolean updateDetails(String password, String firstName, String lastName, String mobileNumber, user user) {

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

	// Update User Details
	public void updatepackageUserId(String nuid) {

		int userid = 4;
		Session session = getSession();
		Transaction t = session.beginTransaction();
		String hql = "UPDATE user set userid = :userid WHERE nuid = :nuid";
		Query query = session.createQuery(hql);
		query.setParameter("userid", userid);
		query.setParameter("nuid", nuid);
		query.executeUpdate();
		t.commit();
		session.close();
	}

	public boolean updateDetails(String firstName, String lastName, String mobileNumber, user user) {

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
