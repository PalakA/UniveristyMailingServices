package com.mvc.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(appliesTo="packages")
public class packages {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "packageId")
	private int packageId;

	@Column(name="packageDesc")
	private String packageDesc;

	@ManyToOne()
	@JoinColumn(name="userid", foreignKey = @ForeignKey(name = "USER_ID_FK"))
	private user user;

	@Column(name="studentNuid")
	private String studentNUID;

	@Column(name="aisleNumber")
	private String aisleNumber;

	@Column(name="mailRoomNumber")
	private String mailRoomNumber;

	@Column(name="clerkName")
	private String clerkName;

	@Column(name="notifyStudent")
	private String notifyStudent;

	@Column(name="otp")
	private long otp;

	@Column(name="packagePickedup")
	private String packagePickedup;

	@Column(name= "packagePickedupDate")
	@Temporal(TemporalType.DATE)
	private Date packagePickedupDate;

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public String getPackageDesc() {
		return packageDesc;
	}

	public void setPackageDesc(String packageDesc) {
		this.packageDesc = packageDesc;
	}

	public String getStudentNUID() {
		return studentNUID;
	}

	public void setStudentNUID(String studentNUID) {
		this.studentNUID = studentNUID;
	}

	public String getAisleNumber() {
		return aisleNumber;
	}

	public String getNotifyStudent() {
		return notifyStudent;
	}

	public void setNotifyStudent(String notifyStudent) {
		this.notifyStudent = notifyStudent;
	}

	public void setAisleNumber(String aisleNumber) {
		this.aisleNumber = aisleNumber;
	}

	public String getMailRoomNumber() {
		return mailRoomNumber;
	}

	public void setMailRoomNumber(String mailRoomNumber) {
		this.mailRoomNumber = mailRoomNumber;
	}

	public String getClerkName() {
		return clerkName;
	}

	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}

	public long getOtp() {
		return otp;
	}

	public void setOtp(long otp) {
		this.otp = otp;
	}

	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}

	public String getPackagePickedup() {
		return packagePickedup;
	}

	public void setPackagePickedup(String packagePickedup) {
		this.packagePickedup = packagePickedup;
	}

	public Date getPackagePickedupDate() {
		return packagePickedupDate;
	}

	public void setPackagePickedupDate(Date packagePickedupDate) {
		this.packagePickedupDate = packagePickedupDate;
	}

}
