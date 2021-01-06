package com.polarising.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.polarising.models.Owner;
import com.polarising.models.Vet;
import com.polarising.services.OwnerService;
import com.polarising.services.VetService;
import com.polarising.services.map.OwnerServiceMap;
import com.polarising.services.map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	
	public DataLoader(OwnerService ownerService, VetService vetService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
	}

	@Override
	public void run(String... args) throws Exception {
		
		Owner owner1 = new Owner();
		owner1.setFirstName("Nelson");
		owner1.setLastName("Dias");
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Andre");
		owner2.setLastName("Silva");
		
		ownerService.save(owner2);
		
		System.out.println("Loaded Owners....");
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Julian");
		vet2.setLastName("Travis");
		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets....");
		
	}

	
	
}
