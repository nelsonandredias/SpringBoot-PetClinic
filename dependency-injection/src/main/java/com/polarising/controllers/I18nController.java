package com.polarising.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.polarising.services.GreetingsService;

@Component
public class I18nController {

	private final GreetingsService greetingsService;

	public I18nController(@Qualifier("i18nService") GreetingsService greetingsService) {
		super();
		this.greetingsService = greetingsService;
	}
	
	public String sayHello() {
		return greetingsService.sayGreeting();
	}
	
}
