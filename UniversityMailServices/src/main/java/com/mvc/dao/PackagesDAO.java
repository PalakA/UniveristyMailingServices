package com.mvc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.mvc.pojo.packages;
import com.mvc.pojo.user;

public class PackagesDAO extends DAO {

	// Get All Packages List
	@SuppressWarnings({ "deprecation" })
	public static List<packages> getAllPackages() {

		List<packages> packages = new ArrayList<packages>();

		try {

			Session session = getSession();
			Transaction t = session.beginTransaction();
			Criteria crit = session.createCriteria(packages.class);
			packages = crit.list();
			t.commit();
			return packages;

		} catch (HibernateException e) {

			e.printStackTrace();
			rollback();
			return null;

		} finally {

			close();
		}
	}

	// Get specific package by id
	public static packages getPackagesbyUserid(int userid) {

		try {

			Session session = getSession();
			Transaction t = session.beginTransaction();
			Criteria crit = getSession().createCriteria(packages.class);
			crit.add(Restrictions.eq("userid", userid));
			packages packages = (packages) crit.uniqueResult();
			t.commit();
			return packages;

		} catch (HibernateException e) {

			e.printStackTrace();
			rollback();
			return null;

		} finally {

			close();
		}
	}

	// Get All Packages List by userid
	@SuppressWarnings({ "deprecation" })
	public static List<packages> getAllPackagesbyUserid(int userid) {

		List<packages> packages = new ArrayList<packages>();

		try {
			String nuid = "001090330";
			Session session = getSession();
			Query query = session.createQuery("from packages where studentNuid =:studentNuid");
			query.setString("studentNuid", nuid);
			packages = query.list();
			return packages;

		} catch (HibernateException e) {

			e.printStackTrace();
			rollback();
			return null;

		} finally {

			close();
		}
	}

	// Get All Packages List by userid
	@SuppressWarnings({ "deprecation" })
	public static List<packages> getAllPackagesbyOtp(String otp) {

		List<packages> packages = new ArrayList<packages>();

		try {

			Session session = getSession();
			Query query = session.createQuery("from packages where otp =:otp");
			query.setString("otp", otp);
			packages = query.list();
			return packages;

		} catch (HibernateException e) {

			e.printStackTrace();
			rollback();
			return null;

		} finally {

			close();
		}
	}

	// Update User Details
	public boolean addPackage(String packageDesc, String aisleNumber, String mailRoomNumber, String clerkName,
			String nuid, String notifyStudent, long otp, user user) {

		packages packages = new packages();
		packages.setPackageDesc(packageDesc);
		packages.setAisleNumber(aisleNumber);
		packages.setMailRoomNumber(mailRoomNumber);
		packages.setClerkName(clerkName);
		packages.setNotifyStudent(notifyStudent);
		packages.setStudentNUID(nuid);
		packages.setUser(user);
		packages.setOtp(otp);

		Session session = getSession();
		Transaction t = session.beginTransaction();
		session.save(packages);
		t.commit();
		return true;
	}

	// Update package pickup details
	@SuppressWarnings("deprecation")
	public void updatePackageDelivery(int packageId) {

		Date packagePickedupDate = new Date();
		String packagePickedup = "Yes";
		String notifyStudent = "Yes";

		Session session = getSession();
		Transaction t = session.beginTransaction();
		String hql = "UPDATE packages set packagePickedupDate = :packagePickedupDate, packagePickedup =:packagePickedup, notifyStudent =:notifyStudent "
				+ "WHERE packageId = :packageId";
		Query query = session.createQuery(hql);
		query.setParameter("packagePickedupDate", packagePickedupDate);
		query.setString("packagePickedup", packagePickedup);
		query.setString("notifyStudent", notifyStudent);
		query.setParameter("packageId", packageId);
		query.executeUpdate();
		t.commit();
		session.close();
	}
}
