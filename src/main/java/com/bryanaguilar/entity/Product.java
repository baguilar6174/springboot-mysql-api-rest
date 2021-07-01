package com.bryanaguilar.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Product {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "The name field cannot be empty")
	@Size(min = 4, max = 255, message = "The name field must be between 4 and 255 characters")
	private String name;
	
	@Size(max = 255, message = "The description field must be no more than 255 characters")
	private String description;
	
	@Min( value = 0, message = "The price field must be greater than or equal to zero" )
	private double price;
	
	@Min( value = 0, message = "The stock field must be greater than or equal to zero" )
	private long stock;
	
	// UNA PRESENTACIÓN TIENE MUCHOS PRODUCTOS ASOCIADOS
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@NotNull(message = "The stock presentation cannot be null")
	private Presentation presentation;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public Presentation getPresentation() {
		return presentation;
	}

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}
	
}
