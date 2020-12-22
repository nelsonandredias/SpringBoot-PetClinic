package com.polarising.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polarising.services.GreetingsService;

@Component
public class MyController {
	
	private final GreetingsService greetingService;
	
	@Autowired
	public MyController(GreetingsService greetingService) {
		super();
		this.greetingService = greetingService;
	}

	public String getGreeting() {
		
		return greetingService.sayGreeting();
	}

}
