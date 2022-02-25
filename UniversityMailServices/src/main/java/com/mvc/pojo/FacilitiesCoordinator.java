package com.mvc.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(appliesTo="FacilitiesCoordinator")

public class FacilitiesCoordinator {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "employeeId")
	private int employeeId;

	@ManyToOne()
	@JoinColumn(name="userid", foreignKey = @ForeignKey(name = "USER_ID_FK"))
	private user user;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}

}
