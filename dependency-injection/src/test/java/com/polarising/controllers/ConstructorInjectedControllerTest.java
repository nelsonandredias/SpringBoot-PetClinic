package com.polarising.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.polarising.services.ConstructorGreetingsServiceImpl;

class ConstructorInjectedControllerTest {

	ConstructorInjectedController controller;
	
	@BeforeEach
	void setUp() {
		controller = new ConstructorInjectedController(new ConstructorGreetingsServiceImpl());
	}
	
	@Test
	void test() {
		System.out.println(controller.getGreeting());
	}
}
