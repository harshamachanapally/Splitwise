package com.splitwise.controller;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.splitwise.pojo.Customers;
import com.splitwise.service.ServiceLayerInterface;
//@Component
//@WebService
public class SOAPWebService {
	
	//AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-configuration-servlet.xml");
	
	
	ServiceLayerInterface service;
	
	public Customers getCustomerById(int Id){
		return service.getCustomerById(Id);
	}
}
