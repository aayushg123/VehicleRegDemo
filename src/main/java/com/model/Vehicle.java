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

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="vehicle")
public class Vehicle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Transient
	private Integer       VehicleId;
	@Column(name="model", length=255, nullable=true, unique=false)
	private String    model;
	@Column(name="engine_no", length=255, nullable=false, unique=true)
	private String    engineNo;
	@Column(name="regdate",nullable=false, unique=false)
	private LocalDate regDate;
	@Column(name="state", length=100, nullable=false, unique=false)
	private String    state;
	@Column(name="district", length=255, nullable=false, unique=false)
	private String    district;
	@Column(name="aadhar_card", length=12, nullable=false, unique=false)
	private String    aadharNo;
	@Id
	@Column(name="vehicle_regno", length=15, nullable=false, unique=true)
	private String    vehicleRegNo;
	@Column(name="challanno", nullable=true, unique=false)
	private int       challanCount;
	@Column(name="lastchallanpaid", nullable=true, unique=false)
	private LocalDate lastChallanPaid;
	@Column(name="isarchived", nullable=true, unique=false)
	private Boolean   isArchived;
	@JsonProperty("isGreenTaxPaid")
	@Column(name="isgreentaxpaid",nullable=true, unique=false)
	private Boolean   isGreenTaxPaid;
	

	public Vehicle() {
		// TODO Auto-generated constructor stub
	}
	
	public Vehicle(String vehicleRegNo, int challanCount, LocalDate lastChallanPaid, boolean isGreenTaxPaid) {
		
		
		this.vehicleRegNo = vehicleRegNo;
		this.challanCount = challanCount;
		this.lastChallanPaid = lastChallanPaid;
		this.isGreenTaxPaid = isGreenTaxPaid;
		System.out.println("1"+ isGreenTaxPaid);
	}
	
	public Vehicle(String model, String engineNo, LocalDate regDate, String state, String district, String vehicleRegNo,
			int challanCount, boolean isArchived) {
		
		
		this.model = model;
		this.engineNo = engineNo;
		this.regDate = regDate;
		this.state = state;
		this.district = district;
		this.vehicleRegNo = vehicleRegNo;
		this.challanCount = challanCount;
		this.isArchived = isArchived;
	}
	
	public Vehicle(int vehicleId, String model, String engineNo, LocalDate regDate, String state, String district,
			String aadharNo, String vehicleRegNo, int challanCount, LocalDate lastChallanPaid, boolean isArchived) {
		
		
		VehicleId = vehicleId;
		this.model = model;
		this.engineNo = engineNo;
		this.regDate = regDate;
		this.state = state;
		this.district = district;
		this.aadharNo = aadharNo;
		this.vehicleRegNo = vehicleRegNo;
		this.challanCount = challanCount;
		this.lastChallanPaid = lastChallanPaid;
		this.isArchived = isArchived;
	}
	
	public Vehicle(String model, String engineNo, LocalDate regDate, String state, String district) {

		this.model = model;
		this.engineNo = engineNo;
		this.regDate = regDate;
		this.state = state;
		this.district = district;
	}
	
	
	public Vehicle(Object object) {
		// TODO Auto-generated constructor stub
	}

	public Integer getVehicleId() {
		return VehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		VehicleId = vehicleId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getVehicleRegNo() {
		return vehicleRegNo;
	}

	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}

	public int getChallanCount() {
		return challanCount;
	}

	public void setChallanCount(int challanCount) {
		this.challanCount = challanCount;
	}

	public LocalDate getLastChallanPaid() {
		return lastChallanPaid;
	}

	public void setLastChallanPaid(LocalDate lastChallanPaid) {
		this.lastChallanPaid = lastChallanPaid;
	}

	public Boolean isArchived() {
		return isArchived;
	}

	public void setArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}
	
	public Boolean getIsGreenTaxPaid() {
		return isGreenTaxPaid;
	}

	public void setIsGreenTaxPaid(Boolean isGreenTaxPaid) {
		this.isGreenTaxPaid = isGreenTaxPaid;
	}

	@Override
	public String toString() {
		return "Vehicle [model=" + model + ", engineNo=" + engineNo + ", regDate=" + regDate + ", state=" + state
				+ ", district=" + district + ", aadharNo=" + aadharNo + "]";
	}


	
};
