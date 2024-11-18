package com.asurion.spc.datacollection.rf;

public class ParameterSetup {
	int status;
	String id;
	String parameter_name;
	String serial;
	String imei;
	private boolean active;
	
	public String getSerial() {
		return this.serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	public int getStatus() {
		return this.status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParameter_name() {
		return this.parameter_name;
	}
	public void setParameter_name(String parameter_name) {
		this.parameter_name = parameter_name;
	}
	public String getImei() {
		return this.imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public boolean getActive() {
		return this.active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	

}
