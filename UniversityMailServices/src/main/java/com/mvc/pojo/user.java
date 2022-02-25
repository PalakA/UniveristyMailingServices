package com.mvc.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("deprecation")
@Entity
@Table(name="user")
@Inheritance(strategy = InheritanceType.JOINED)
public class user implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userid", unique = true, nullable = false)
	private int userid;

	@Column(name="password", nullable = false)
	private String password;

	@Column(name= "regDate", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date regDate;

	@Column(name= "lastLoginDate")
	@Temporal(TemporalType.DATE)
	private Date lastLoginDate;

	@Column(name = "userRole", nullable = false)
	private String userRole;

	@Column(name = "userStatus")
	private String userStatus;

	@Column(name="firstName")
	private String firstName;

	@Column(name="lastName")
	private String lastName;

	@Column(name="nuid")
	private String nuid;

	@Column(name="mobileNumber")
	private String mobileNumber;

	@Column(name="emailId", unique = true, nullable = false)
	private String emailId;

	@Column(name="mailRoomNumber")
	private String mailroomNumber;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNuid() {
		return nuid;
	}

	public void setNuid(String nuid) {
		this.nuid = nuid;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMailroomNumber() {
		return mailroomNumber;
	}

	public void setMailroomNumber(String mailroomNumber) {
		this.mailroomNumber = mailroomNumber;
	}

}