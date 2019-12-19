package com.maybank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.maybank.service.ProductService;

@SpringBootTest
class MaybankApplicationTests {

	@Autowired
	ProductService services;
	
	@Test
	void contextLoads() {
		
		services.searchProductByName("");
		
	}
	

}
