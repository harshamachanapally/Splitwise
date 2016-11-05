package com.example.service;

import static org.junit.Assert.assertEquals;

import javax.xml.ws.Service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.SplitwiseApplicationTests;
import com.splitwise.service.ServiceLayerInterface;

@Transactional
public class ServiceTest {

	@Autowired
	private ServiceLayerInterface service;
	
	@Test
	void test1(){
		int id =1;
		assertEquals(id, service.getCustomerById(id).getUserId());
	}
	
}
