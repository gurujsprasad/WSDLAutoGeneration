package com.model;

import java.util.List;

public class WebServiceModel {
	
	private int wsdlID;
	private String wsdl;
	private String wsdlName;
	private String method;
	private double timePerRequest;
	private int timesRun;
	private List<RunParameterModel> parametersValues;
	
	
	
	public WebServiceModel(int wsdlID, String wsdl, String wsdlName) {
		super();
		this.wsdlID = wsdlID;
		this.wsdl = wsdl;
		this.wsdlName = wsdlName;
	}
	public WebServiceModel(int wsdlID, String wsdlName, String method, double timePerRequest, int timesRun) {
		super();
		this.wsdlID = wsdlID;
		this.wsdlName = wsdlName;
		this.method = method;
		this.timePerRequest = timePerRequest;
		this.timesRun = timesRun;
	}
	public WebServiceModel(int wsdlID, String wsdl, String wsdlName, String method, double timePerRequest,
			int timesRun, List<RunParameterModel> parametersValues) {
		super();
		this.wsdlID = wsdlID;
		this.wsdl = wsdl;
		this.wsdlName = wsdlName;
		this.method = method;
		this.timePerRequest = timePerRequest;
		this.timesRun = timesRun;
		this.parametersValues = parametersValues;
	}
	public WebServiceModel() {
		// TODO Auto-generated constructor stub
	}
	public int getWsdlID() {
		return wsdlID;
	}
	public void setWsdlID(int wsdlID) {
		this.wsdlID = wsdlID;
	}
	public String getWsdl() {
		return wsdl;
	}
	public void setWsdl(String wsdl) {
		this.wsdl = wsdl;
	}
	public String getWsdlName() {
		return wsdlName;
	}
	public void setWsdlName(String wsdlName) {
		this.wsdlName = wsdlName;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public double getTimePerRequest() {
		return timePerRequest;
	}
	public void setTimePerRequest(double timePerRequest) {
		this.timePerRequest = timePerRequest;
	}
	public double getTimesRun() {
		return timesRun;
	}
	public void setTimesRun(int timesRun) {
		this.timesRun = timesRun;
	}
	public List<RunParameterModel> getParametersValues() {
		return parametersValues;
	}
	public void setParametersValues(List<RunParameterModel> parametersValues) {
		this.parametersValues = parametersValues;
	} 
	
	
}
