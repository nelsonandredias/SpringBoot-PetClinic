package com.polarising.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class PrimaryGreetingService implements GreetingsService{

	@Override
	public String sayGreeting() {
		return "Hello World - From the PRIMARY bean";
	}
	
	

}
