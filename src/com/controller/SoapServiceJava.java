package com.controller;

import java.io.IOException;

import eu.impact_project.iif.ws.generic.SoapInput;
import eu.impact_project.iif.ws.generic.SoapOperation;
import eu.impact_project.iif.ws.generic.SoapService;

public class SoapServiceJava {

	
	public static void soapTest()
	{
		SoapService service;
		try {
			//service = new SoapService(url)
			service = new SoapService("http://wsf.cdyne.com/WeatherWS/Weather.asmx?WSDL");
			for(SoapOperation op : service.getOperations()) {
				System.out.println("Operations:");
				System.out.println(op.getName());
				for(SoapInput in : op.getInputs()) {
					System.out.println(in.getName());
					System.out.println(in.getDefaultValue());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main (String args [])
	{
		soapTest();
	}
}
