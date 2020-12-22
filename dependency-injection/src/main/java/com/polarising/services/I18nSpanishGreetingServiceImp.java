package com.polarising.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"ES", "default"})
//@Profile("ES")
@Service("i18nService")
public class I18nSpanishGreetingServiceImp implements GreetingsService{

	@Override
	public String sayGreeting() {
		return "Hola Mundo - ES";
		
	}

}
