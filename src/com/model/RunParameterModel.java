package com.model;

public class RunParameterModel {

	int id;
	String parameterName;
	String parameterValue;
	int serviceRunID;
	
	public RunParameterModel(){}
	
	public RunParameterModel(int id, String parameterName, String parameterValue, int serviceRunID) {
		super();
		this.id = id;
		this.parameterName = parameterName;
		this.parameterValue = parameterValue;
		this.serviceRunID = serviceRunID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	public int getServiceRunID() {
		return serviceRunID;
	}
	public void setServiceRunID(int serviceRunID) {
		this.serviceRunID = serviceRunID;
	}
	
}
