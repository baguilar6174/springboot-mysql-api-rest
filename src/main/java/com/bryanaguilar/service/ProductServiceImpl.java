package com.bryanaguilar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bryanaguilar.dao.IPresentationDao;
import com.bryanaguilar.dao.IProductDao;
import com.bryanaguilar.entity.Presentation;
import com.bryanaguilar.entity.Product;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao productDao;
	
	@Autowired
	private IPresentationDao presentationDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll(Sort sort) {
		return productDao.findAll(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Product> findAll(Pageable pageable) {
		return productDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(long id) {
		return productDao.findById(id);
	}

	@Override
	@Transactional
	public void delete(long id) {
		productDao.deleteById(id);
	}

	@Override
	@Transactional
	public Product save(Product product) {
		Presentation presentation = presentationDao.findById(product.getPresentation().getId()).orElse(null);
		product.setPresentation(presentation);
		return productDao.save(product);
	}

}
