package com.polarising.models;

import java.io.Serializable;

import javax.persistence.Id;

public class BaseEntity implements Serializable {
	
	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
