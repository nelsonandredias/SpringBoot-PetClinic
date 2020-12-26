package com.polarising.services;

import java.util.Set;

import com.polarising.models.Vet;

public interface VetService {
	
	Vet findById(Long id);
	
	Vet save(Vet vet);
		
	Set<Vet> findAll();

}
