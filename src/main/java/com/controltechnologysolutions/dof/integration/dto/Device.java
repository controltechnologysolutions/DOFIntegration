package com.controltechnologysolutions.dof.integration.dto;

public class Device {
	private String deviceID;
	private String name;
	private String parentDeviceID;
	private Integer quantum;
	private Integer position;
	
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentDeviceID() {
		return parentDeviceID;
	}
	public void setParentDeviceID(String parentDeviceID) {
		this.parentDeviceID = parentDeviceID;
	}
	public Integer getQuantum() {
		return quantum;
	}
	public void setQuantum(Integer quantum) {
		this.quantum = quantum;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
}
