package com.bryanaguilar.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table
public class Presentation {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "The name field cannot be empty")
	@Size(min = 4, max = 255, message = "The name field must be between 4 and 255 characters")
	private String name;
	
	@Size(max = 255, message = "The description field must be no more than 255 characters")
	private String description;

	//UNA PRESENTACIÓN TIENE ASOCIADOS MUCHOS PRODUCTOS
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "presentation")
	private List<Product> products;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
