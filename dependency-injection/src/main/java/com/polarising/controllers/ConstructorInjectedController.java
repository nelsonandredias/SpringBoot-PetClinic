package com.polarising.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.polarising.services.GreetingsService;

@Controller
public class ConstructorInjectedController {
	
	private final GreetingsService greetingsService;

	public ConstructorInjectedController(@Qualifier("constructorGreetingsServiceImpl") GreetingsService greetingsService) {
		super();
		this.greetingsService = greetingsService;
	}
	
	public String getGreeting() {
		return greetingsService.sayGreeting();
	}


}
