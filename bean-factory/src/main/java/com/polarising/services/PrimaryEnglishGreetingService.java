package com.polarising.services;

public class PrimaryEnglishGreetingService implements GreetingService{

	private GreetingRepository greetingRepository;

	public PrimaryEnglishGreetingService(GreetingRepository greetingRepository) {
		this.greetingRepository = greetingRepository;
	}

	
	@Override
	public String sayGreeting() {
		return greetingRepository.getEnglishGreeting();
	}

	
}
