package com.controltechnologysolutions.dof.integration.dto;

public class DeviceValue {
	private String deviceID;
	private Long timestamp;
	private String interfaceID;
	private Integer itemID;
	private Long valueLong;
	private Float valueFloat1;
	private Float valueFloat2;
	private Float valueFloat3;

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getInterfaceID() {
		return interfaceID;
	}

	public void setInterfaceID(String interfaceID) {
		this.interfaceID = interfaceID;
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public Long getValueLong() {
		return valueLong;
	}

	public void setValueLong(Long valueLong) {
		this.valueLong = valueLong;
	}

	public Float getValueFloat1() {
		return valueFloat1;
	}

	public void setValueFloat1(Float valueFloat1) {
		this.valueFloat1 = valueFloat1;
	}

	public Float getValueFloat2() {
		return valueFloat2;
	}

	public void setValueFloat2(Float valueFloat2) {
		this.valueFloat2 = valueFloat2;
	}

	public Float getValueFloat3() {
		return valueFloat3;
	}

	public void setValueFloat3(Float valueFloat3) {
		this.valueFloat3 = valueFloat3;
	}
}
