package com.mvc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.exception.MailServiceException;
import com.mvc.pojo.FacilitiesCoordinator;
import com.mvc.pojo.MailAssociate;
import com.mvc.pojo.MailroomClerk;
import com.mvc.pojo.Student;
import com.mvc.pojo.user;

@Repository
public class UserDAO extends DAO {

	@Autowired
	private SessionFactory sessionFactory;

	public user get(user user) throws MailServiceException {
		try {
			begin();
			Criteria crit = getSession().createCriteria(user.class);
			crit.add(Restrictions.eq("emailId", user.getEmailId()));
			crit.add(Restrictions.eq("password", user.getPassword()));
			user loggedInUser = (user) crit.uniqueResult();
			commit();
			return loggedInUser;
		} catch (HibernateException e) {
			rollback();
			throw new MailServiceException("Could not get user " + user.getEmailId(), e);
		}
	}

	public boolean addAdminUser(String password) {

		try {
			user user = new user();
			FacilitiesCoordinator facilitiesCoordinator = new FacilitiesCoordinator();
			Date regdate = new Date();

			user.setEmailId("facilitiescoordinator@gmail.com");
			user.setPassword(password);
			user.setUserRole("FacilitiesCoordinator");
			user.setRegDate(regdate);
			user.setFirstName("Facilities");
			user.setLastName("Coordinator");
			user.setMailroomNumber("1");
			user.setMobileNumber("0000000000");
			user.setUserStatus("Active");

			facilitiesCoordinator.setUser(user);

			Session session = getSession();
			Transaction t = session.beginTransaction();
			session.save(user);
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

	// Add new User to the Database based on UserRole
	public boolean addUser(String useremail, String password, String userrole, String firstName, String lastName,
			String mobileNumber, String mailroomnumber, String nuid) {

		try {

			if (userrole.equals("FacilitiesCoordinator")) {

				user user = new user();
				FacilitiesCoordinator facilitiesCoordinator = new FacilitiesCoordinator();
				Date regdate = new Date();

				user.setEmailId(useremail);
				user.setPassword(password);
				user.setUserRole(userrole);
				user.setRegDate(regdate);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setMobileNumber(mobileNumber);
				user.setMailroomNumber(mailroomnumber);
				user.setUserStatus("Active");

				facilitiesCoordinator.setUser(user);

				Session session = getSession();
				Transaction t = session.beginTransaction();
				session.save(user);
				session.save(facilitiesCoordinator);
				t.commit();
				return true;

			} else if (userrole.equals("MailAssociate")) {

				user user = new user();
				MailAssociate mailAssociate = new MailAssociate();
				Date regdate = new Date();

				user.setEmailId(useremail);
				user.setPassword(password);
				user.setUserRole(userrole);
				user.setRegDate(regdate);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setMobileNumber(mobileNumber);
				user.setMailroomNumber(mailroomnumber);
				user.setUserStatus("Active");

				mailAssociate.setUser(user);

				Session session = getSession();
				Transaction t = session.beginTransaction();
				session.save(user);
				t.commit();
				return true;

			} else if (userrole.equals("MailroomClerk")) {

				user user = new user();
				MailroomClerk mailroomClerk = new MailroomClerk();
				Date regdate = new Date();

				user.setEmailId(useremail);
				user.setPassword(password);
				user.setUserRole(userrole);
				user.setRegDate(regdate);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setMobileNumber(mobileNumber);
				user.setMailroomNumber(mailroomnumber);
				user.setUserStatus("Active");

				mailroomClerk.setUser(user);

				Session session = getSession();
				Transaction t = session.beginTransaction();
				session.save(user);
				session.save(mailroomClerk);
				t.commit();
				return true;

			} else {

				user user = new user();
				Student student = new Student();
				Date regdate = new Date();

				user.setEmailId(useremail);
				user.setPassword(password);
				user.setUserRole(userrole);
				user.setRegDate(regdate);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setMobileNumber(mobileNumber);
				user.setNuid(nuid);
				user.setUserStatus("Active");

				student.setUser(user);
				Session session = getSession();
				Transaction t = session.beginTransaction();
				session.save("User", user);
				t.commit();
				return true;
			}

		} catch (HibernateException e) {

			e.printStackTrace();
			rollback();
			return false;

		} finally {

			close();
		}
	}

	// Get specific User by EmailID
	@SuppressWarnings({ "deprecation" })
	public user getUserByEmail(String useremail) {

		Criteria crit = getSession().createCriteria(user.class);
		crit.add(Restrictions.eq("emailId", useremail));
		user user = (user) crit.uniqueResult();
		return user;
	}

	// Get specific User by userid
	@SuppressWarnings({ "deprecation" })
	public user getUserById(int userid) {

		Criteria crit = getSession().createCriteria(user.class);
		crit.add(Restrictions.eq("userid", userid));
		user user = (user) crit.uniqueResult();
		return user;
	}

	// Update User Last Login Details
	public void updateLogin(user user) {

		int userid = user.getUserid();
		Date login = new Date();

		Session session = getSession();
		Transaction t = session.beginTransaction();
		String hql = "UPDATE user set lastLoginDate = :login " + "WHERE userid = :userid";
		Query query = session.createQuery(hql);
		query.setParameter("login", login);
		query.setParameter("userid", userid);
		query.executeUpdate();
		t.commit();
		session.close();
	}

	// Get List of all Users
	@SuppressWarnings({ "deprecation" })
	public List<user> getAllUsers() {

		List<user> users = new ArrayList<user>();
		try {

			Session session = getSession();
			Transaction t = session.beginTransaction();
			Criteria crit = session.createCriteria(user.class);
			users = crit.list();
			t.commit();
			return users;

		} catch (HibernateException e) {

			e.printStackTrace();
			rollback();
			return null;

		} finally {

			close();
		}
	}

	// Find if User Exists or not!
	@SuppressWarnings({ "deprecation" })
	public boolean doUserExists(String email) {

		Criteria crit = getSession().createCriteria(user.class);
		crit.add(Restrictions.eq("emailId", email));
		user user = (user) crit.uniqueResult();

		if (user != null) {
			return false;
		} else {
			return true;
		}
	}

	// Update User Details
	public void updateUser(int userid, String password, String firstName, String lastName, String mobileNumber) {

		Session session = getSession();
		Transaction t = session.beginTransaction();
		String hql = "UPDATE user set password = :password,firstName = :firstName,lastName =:lastName, mobileNumber =:mobileNumber WHERE userid = :userid";
		Query query = session.createQuery(hql);
		query.setParameter("password", password);
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		query.setParameter("mobileNumber", mobileNumber);
		query.setParameter("userid", userid);
		query.executeUpdate();
		t.commit();
		session.close();
	}

	// Update User Details
	public void updateUser(int userid, String firstName, String lastName, String mobileNumber) {

		Session session = getSession();
		Transaction t = session.beginTransaction();
		String hql = "UPDATE user set firstName = :firstName,lastName =:lastName, mobileNumber =:mobileNumber WHERE userid = :userid";
		Query query = session.createQuery(hql);
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		query.setParameter("mobileNumber", mobileNumber);
		query.setParameter("userid", userid);
		query.executeUpdate();
		t.commit();
		session.close();
	}

	// Get specific User by neuid
	public Integer getStudentById(String nuid) {
		Session session = getSession();
		Query query = session.createQuery("Select userid from user where nuid =:nuid");
		query.setParameter("nuid", nuid);
		return (Integer) query.uniqueResult();
	}

	// Get specific User by neuid
	public String getEmailIdById(int userid) {
		Session session = getSession();
		Query query = session.createQuery("Select emailId from user where userid =:userid");
		query.setParameter("userid", userid);
		return ((String) query.uniqueResult());
	}
}
