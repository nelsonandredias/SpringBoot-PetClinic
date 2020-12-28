package com.polarising.services;

import java.util.Set;

import com.polarising.models.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
	
	Owner findByLastName(String lastName);
	
}
