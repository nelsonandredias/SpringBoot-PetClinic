package com.polarising.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.polarising.services.GreetingsService;

@Controller
public class SetterInjectedController {
	
	private GreetingsService greetingService;

	@Qualifier("setterGreetingsServiceImpl")
	@Autowired
	public void setGreetingService(GreetingsService greetingService) {
		this.greetingService = greetingService;
	}
	
	
	public String getGreeting() {
		return greetingService.sayGreeting();
	}

}
