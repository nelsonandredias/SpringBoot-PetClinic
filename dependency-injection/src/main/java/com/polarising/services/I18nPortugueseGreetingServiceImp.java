package com.polarising.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//@Profile({"PT", "default"})
@Profile("PT")
@Service("i18nService")
public class I18nPortugueseGreetingServiceImp implements GreetingsService{

	@Override
	public String sayGreeting() {
		return "Ola Mundo - PT";
		
	}

}
