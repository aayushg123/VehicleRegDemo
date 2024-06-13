package com.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.model.Vehicle;

@Entity
@Table(name = "owner")
public class Owner implements Serializable {

	@Column(name = "owner_id", unique = true)
	@Transient
	private Integer ownerId;

	@Column(name = "owner_name", length = 255, nullable = true, unique = false)
	private String owner_name;

	@Id
	@Column(name = "aadhar_card", length = 12, nullable = true, unique = true)
	private String aadharNo;

	@Column(name = "mobile_no", nullable = false, unique = true)
	private long mobileNo;

	@Column(name = "dob", length = 12, nullable = true, unique = false)
	private LocalDate dob;

	public Owner() {

	}

	public Owner(Integer ownerId, String owner_name, String aadharNo, long mobileNo, LocalDate dob) {
		super();
		this.ownerId = ownerId;
		this.owner_name = owner_name;
		this.aadharNo = aadharNo;
		this.mobileNo = mobileNo;
		this.dob = dob;
	}

	public Owner(String owner_name, String aadharNo, long mobileNo, LocalDate dob) {
		super();
		this.owner_name = owner_name;
		this.aadharNo = aadharNo;
		this.mobileNo = mobileNo;
		this.dob = dob;
	}

	public Owner(String ownerName, LocalDate dob2, long mobileNo2) {
		// TODO Auto-generated constructor stub
		this.owner_name = ownerName;
		this.dob = dob2;
		this.mobileNo = mobileNo2;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Owner [owner_name=" + owner_name + ", aadharNo=" + aadharNo + ", mobileNo=" + mobileNo + ", dob=" + dob
				+ "]";
	}

};
