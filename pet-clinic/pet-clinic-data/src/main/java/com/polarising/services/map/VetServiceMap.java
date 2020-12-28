package com.polarising.services.map;

import java.util.Set;

import com.polarising.models.Vet;
import com.polarising.services.CrudService;

public class VetServiceMap extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long> {

	@Override
	public Set<Vet> findAll() {
		
		return super.findAll();
	}

	@Override
	public Vet findById(Long id) {
		
		return super.findById(id);
	}

	@Override
	public Vet save(Long id, Vet object) {
		
		return super.save(id, object);
	}

	@Override
	public void deleteById(Long id) {
		
		super.deleteById(id);
	}

	@Override
	public void delete(Vet object) {
		
		super.delete(object);
	}

}
