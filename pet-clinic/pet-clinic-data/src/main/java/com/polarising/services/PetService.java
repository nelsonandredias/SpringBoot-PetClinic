package com.polarising.services;

import java.util.Set;


import com.polarising.models.Pet;

public interface PetService {
	
	Pet findById(Long id);
		
	Pet save(Pet pet);
		
	Set<Pet> findAll();

}
