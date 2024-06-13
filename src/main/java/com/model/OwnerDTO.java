package com.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

public class OwnerDTO {

	private String ownerName;

	private long mobileNo;

	private LocalDate dob;
	
	public OwnerDTO() {
		// TODO Auto-generated constructor stub
		super();
	}

	public String getOwnerName() {
		return ownerName;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setOwner_name(String ownerName) {
		this.ownerName = ownerName;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public OwnerDTO(String ownerName, long mobileNo, LocalDate dob) {
		this.ownerName = ownerName;
		this.mobileNo = mobileNo;
		this.dob = dob;
	}

	public OwnerDTO(Response response) {
		// TODO Auto-generated constructor stub 
	}

}
