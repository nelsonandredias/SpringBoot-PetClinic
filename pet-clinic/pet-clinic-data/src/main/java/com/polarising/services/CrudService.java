package com.polarising.services;

import java.util.Set;

//Mimicking Spring Data repository
public interface CrudService<T, ID> {

	Set<T> findAll();
	
	T findById(ID id);
	
	T save(ID id, T object);
	
	void delete(T object);
	
	void deleteById(ID id);
}
