package com.asurion.spc.datacollection.rf;

public class RFBean<T> {
	private String parametersetup_id;
	private double data_value;
	private boolean qual_run;
	private String user;
	private String imei;
	private T rawJson;
	public String getParametersetup_id() {
		return this.parametersetup_id;
	}
	public void setParametersetup_id(String parametersetup_id) {
		this.parametersetup_id = parametersetup_id;
	}
	public double getData_value() {
		return this.data_value;
	}
	public void setData_value(double data_value) {
		this.data_value = data_value;
	}
	public boolean isQual_run() {
		return this.qual_run;
	}
	public void setQual_run(boolean qual_run) {
		this.qual_run = qual_run;
	}
	public String getUser() {
		return this.user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	public String getImei() {
		return this.imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public T getRawJson() {
		return this.rawJson;
	}
	public void setRawJson(T rawJson) {
		this.rawJson = rawJson;
	}
	
	
	
	
}
