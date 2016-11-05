package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.splitwise.service.ServiceLayerInterface;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SplitwiseApplicationTests {
	
	@Autowired
	private ServiceLayerInterface service;
	
	public void setup(){
		
	}
	
	@Test
	public void test1(){
		int id =1;
		assertEquals(id, service.getCustomerById(id).getUserId());
	}
	

}
