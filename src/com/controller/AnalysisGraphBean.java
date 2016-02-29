package com.controller;

public class AnalysisGraphBean {

	
	private String wsdlName;
	private int averageTime;
	public String getWsdlName() {
		return wsdlName;
	}
	public void setWsdlName(String wsdlName) {
		this.wsdlName = wsdlName;
	}
	public int getAverageTime() {
		return averageTime;
	}
	public void setAverageTime(int averageTime) {
		this.averageTime = averageTime;
	}
	public AnalysisGraphBean(String wsdlName, int averageTime) {
		super();
		this.wsdlName = wsdlName;
		this.averageTime = averageTime;
	}
	
	
	
}
