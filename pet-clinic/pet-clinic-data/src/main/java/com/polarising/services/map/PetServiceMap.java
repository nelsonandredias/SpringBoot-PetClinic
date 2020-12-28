package com.polarising.services.map;

import java.util.Set;

import com.polarising.models.Pet;
import com.polarising.services.CrudService;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long>{

	@Override
	public Set<Pet> findAll() {
		
		return super.findAll();
	}

	@Override
	public Pet findById(Long id) {
		
		return super.findById(id);
	}

	@Override
	public Pet save(Long id, Pet object) {
		
		return super.save(id, object);
	}

	@Override
	public void deleteById(Long id) {
		
		super.deleteById(id);
	}

	@Override
	public void delete(Pet object) {
		
		super.delete(object);
	}

}
