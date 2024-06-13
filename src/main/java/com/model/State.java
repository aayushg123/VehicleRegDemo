package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "state")
public class State {
	
	@Column(name = "state", length =100,nullable = false, unique=false)
	private String state;
	@Id
	@Column(name = "district", length =255,nullable = false, unique=true)
	private String district;
	@Column(name = "prefix" ,nullable = false, unique=false)
	private String prefix;
	@Column(name = "alphaseries" ,nullable = false, unique=false)
	private String alphaseries;
	@Column(name = "next_number" ,nullable = false, unique=false)
	private String numTobeused;

	public State() {
		// TODO Auto-generated constructor stub
	}

	public State(String state, String district, String prefix, String alphaseries, String numTobeused) {
		super();
		this.state = state;
		this.district = district;
		this.prefix = prefix;
		this.alphaseries = alphaseries;
		this.numTobeused = numTobeused;
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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getAlphaseries() {
		return alphaseries;
	}

	public void setAlphaseries(String alphaseries) {
		this.alphaseries = alphaseries;
	}

	public String getNumTobeused() {
		return numTobeused;
	}

	public void setNumTobeused(String numTobeused) {
		this.numTobeused = numTobeused;
	}

};
