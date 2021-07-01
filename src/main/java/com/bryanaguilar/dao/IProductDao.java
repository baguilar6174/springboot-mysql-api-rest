package com.bryanaguilar.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bryanaguilar.entity.Product;

public interface IProductDao extends JpaRepository<Product, Long> {
	
	@Query(value = "select p from Product p left join fetch p.presentation")
	public List<Product> findAll(Sort sort);
	
	@Query(value = "select p from Product p left join fetch p.presentation", countQuery = "select count(p) from Product p left join p.presentation")
	public Page<Product> findAll(Pageable pageable);
	
	@Query(value = "select p from Product p left join fetch p.presentation where p.id = :id")
	public Product findById(long id);

}
