package com.mvc.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(appliesTo="Student")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "studentId")
	private int studentId;

	@OneToOne
    @JoinColumn(name="userid")
    private user user;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}

	/*
	 * public void addPackages(packages packages) { this.packages.add(packages);
	 * packages.setUserid(this); }
	 */

}
