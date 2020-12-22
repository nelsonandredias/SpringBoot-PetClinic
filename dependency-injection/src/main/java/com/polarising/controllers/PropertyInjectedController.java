package com.polarising.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.polarising.services.GreetingsService;

@Component
public class PropertyInjectedController {
	
	@Qualifier("propertyGreetingsServiceImpl")
	@Autowired
	public GreetingsService greetingService;
	
	public String getGreeting() {
		return greetingService.sayGreeting();
	}

}
