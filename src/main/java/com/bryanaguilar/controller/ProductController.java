package com.bryanaguilar.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bryanaguilar.entity.Product;
import com.bryanaguilar.service.IProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private IProductService productService;
	
	
	/*
	INSERT INTO `presentation` VALUES (1,NULL,'unidad'),(2,NULL,'docena');
	INSERT INTO `product` VALUES (1,NULL,'rezma de papel',3.75,10,1),(2,NULL,'cartas',1,10,2),(3,NULL,'guitarra de juguete',4.5,5,1), (4,NULL,'teclado para computadora',15,5,1), (5,NULL,'teclado para laptop',40,5,1), (6,NULL,'bocinas bluetooth',15,5,1), (7,NULL,'lapices 2b',1.50,4,2), (8,NULL,'plumas color azul',2,10,2), (9,NULL,'monitor dell 15p',40,5,1), (10,NULL,'cargador samsung',10,10,1), (11,NULL,'mouse básico',5,5,1);
	*/
	
	
	
	// http://localhost:8080/products
	// http://localhost:8080/products?page=0&size=5
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(
			@RequestParam(required = false) Integer page, 
			@RequestParam(required = false) Integer size ){
		
		Sort sort= Sort.by("name");
		ResponseEntity<List<Product>> responseEntity = null;
		List<Product> products = null;
		
		if(page != null && size != null) {
			// With pagination
			Pageable pageable = PageRequest.of(page, size, sort);
			products = productService.findAll(pageable).getContent();
		} else {
			// Without pagination	
			products = productService.findAll(sort);
		}
		
		if(products.size() > 0) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}else {
			responseEntity = new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
		
		return responseEntity;
	}
	
	// http://localhost:8080/products/5
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable long id) {
		
		Product product = productService.findById(id);
		ResponseEntity<Product> responseEntity = null;
		
		if(product != null) {
			responseEntity = new ResponseEntity<Product>(product, HttpStatus.OK);
		}else {
			responseEntity = new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}
	
	
	// http://localhost:8080/products
	/*
	 * {
	    "name": "nuevo producto",
	    "description": "nueva descripcion",
	    "price": 69.0,
	    "stock": 1,
	    "presentation": {
	        "id": 1,
	        "name": "unidad",
	        "description": null
	    }
	  }
	 * 
	 * */
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> insert(@Valid @RequestBody Product product, BindingResult bindingResult ) {
		
		Map<String, Object> responseAsMap = new HashMap<String, Object>();
		ResponseEntity<Map<String, Object>> responseEntity = null;
		List<String> errors = null;
		
		if(bindingResult.hasErrors()) {
			errors = new ArrayList<String>();
			for (ObjectError error : bindingResult.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}
			responseAsMap.put("errors", errors);
			responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
			return responseEntity;
		}
		
		try {
			Product productFromDB = productService.save(product);
			if(productFromDB != null) {
				responseAsMap.put("product", product);
				responseAsMap.put("message", "Product with id "+product.getId()+" was created");
				responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.OK);
			}else {
				responseAsMap.put("message", "Product has not been created");
				responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (DataAccessException e) {
			responseAsMap.put("message", "Product has not been created "+ e.getMostSpecificCause().toString());
			responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	// http://localhost:8080/products/1
	/*
	 * {
	    "name": "nuevo producto",
	    "description": "nueva descripcion",
	    "price": 69.0,
	    "stock": 1,
	    "presentation": {
	        "id": 1,
	        "name": "unidad",
	        "description": null
	    }
	  }
	 * 
	 * */
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Map<String, Object>> update(@PathVariable long id, @Valid @RequestBody Product product, BindingResult bindingResult ) {
		
		Map<String, Object> responseAsMap = new HashMap<String, Object>();
		ResponseEntity<Map<String, Object>> responseEntity = null;
		List<String> errors = null;
		
		if(bindingResult.hasErrors()) {
			errors = new ArrayList<String>();
			for (ObjectError error : bindingResult.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}
			responseAsMap.put("errors", errors);
			responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
			return responseEntity;
		}
		
		try {
			product.setId(id);
			Product productFromDB = productService.save(product);
			if(productFromDB != null) {
				responseAsMap.put("product", product);
				responseAsMap.put("message", "Product with id "+product.getId()+" was updated");
				responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.OK);
			}else {
				responseAsMap.put("message", "Product has not been updated");
				responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (DataAccessException e) {
			responseAsMap.put("message", "Product has not been updated "+ e.getMostSpecificCause().toString());
			responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	
	
	
}
