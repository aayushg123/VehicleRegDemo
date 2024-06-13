package com.model;

import java.time.LocalDate;

public class Vehicleresponse {

	private String model;
	private String engineNo;
	private LocalDate regDate;
	private String state;
	private String district;
	private String vehicleRegNo;
	private int challanCount;
	private LocalDate lastChallanPaid;
	
	public Vehicleresponse() {
		// TODO Auto-generated constructor stub
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

	public Vehicleresponse(String model, String engineNo, LocalDate regDate, String state, String district,
			String vehicleRegNo, int challanCount, LocalDate lastChallanPaid) {
		super();
		this.model = model;
		this.engineNo = engineNo;
		this.regDate = regDate;
		this.state = state;
		this.district = district;
		this.vehicleRegNo = vehicleRegNo;
		this.challanCount = challanCount;
		this.lastChallanPaid = lastChallanPaid;
	}

	
}
