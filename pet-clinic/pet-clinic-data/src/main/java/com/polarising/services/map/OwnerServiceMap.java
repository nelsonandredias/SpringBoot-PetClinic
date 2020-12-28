package com.polarising.services.map;

import java.util.Set;

import com.polarising.models.Owner;
import com.polarising.services.CrudService;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {

	@Override
	public Set<Owner> findAll() {
		
		return super.findAll();
	}

	@Override
	public Owner findById(Long id) {
		
		return super.findById(id);
	}

	@Override
	public Owner save(Long id, Owner object) {
		
		return super.save(id, object);
	}

	@Override
	public void deleteById(Long id) {
		
		super.deleteById(id);
	}

	@Override
	public void delete(Owner object) {
		
		super.delete(object);
	}


}
