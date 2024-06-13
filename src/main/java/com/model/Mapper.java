package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Mapper")
public class Mapper implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx",nullable=true)
	private Integer id;
	@Column(name = "vehicle_regno", length = 15, nullable = false, unique = false)
	private String vehicleRegNo;
	@Column(name = "mobile_no", nullable = false, unique = false)
	private long mobileNo;
	@Column(name = "old_regno", nullable = true, unique = false)
	private String oldRegNo;
	
	public Mapper() {
		// TODO Auto-generated constructor stub

	}
	public Mapper(String vehicleRegNo, long mobileNo, String oldRegNo) {
		super();
		this.vehicleRegNo = vehicleRegNo;
		this.mobileNo = mobileNo;
		this.oldRegNo = oldRegNo;
	}

	public String getVehicleRegNo() {
		return vehicleRegNo;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public String getOldRegNo() {
		return oldRegNo;
	}

	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setOldRegNo(String oldRegNo) {
		this.oldRegNo = oldRegNo;
	}

}
