package com.bryanaguilar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.bryanaguilar.entity.Product;

public interface IProductService {
	
	public List<Product> findAll(Sort sort);
	
	public Page<Product> findAll(Pageable pageable);
	
	public Product findById(long id);
	
	public void delete(long id);
	
	public Product save(Product product);
	
}
