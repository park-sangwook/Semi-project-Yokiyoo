package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.MenuService;

@SpringBootTest
class Ex11YokiyooApplicationTests {
	@Autowired
	private MenuService menuService;
	@Test
	void contextLoads() {
		assertNotNull(menuService.selectByName("한식나라"));
	}

}
