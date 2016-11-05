package com.splitwise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.pojo.Customers;
import com.splitwise.service.ServiceLayerInterface;

@RestController
@RequestMapping("/Checklogin")
public class LoginRestController {
	
	@Autowired
	ServiceLayerInterface service;
	
	@RequestMapping(value = "/",method = RequestMethod.POST)
	public Customers Login(@RequestBody Customers customer){
		System.out.println("Login");
		Customers customer1 = service.getCustomer(customer);
		return customer1;
	}
}
