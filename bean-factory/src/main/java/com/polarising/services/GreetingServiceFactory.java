package com.polarising.services;

public class GreetingServiceFactory {
	
	private GreetingRepository greetingRepository;

	public GreetingServiceFactory(GreetingRepository greetingRepository) {
		this.greetingRepository = greetingRepository;
	}
	
	public GreetingService createGreetingService(String lang) {

        switch (lang){
            case "en":
                return new PrimaryEnglishGreetingService(greetingRepository);
            case "de":
                return new PrimaryGermanGreetingService(greetingRepository);
            case "es":
                return new PrimarySpanishGreetingService(greetingRepository);
            default:
                return new PrimaryEnglishGreetingService(greetingRepository);
        }

    }

}
