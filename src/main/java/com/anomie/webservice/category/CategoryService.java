package com.anomie.webservice.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findParentCateories() {
		return categoryRepository.findAllByParentIsNull(); 
	}
	
	public List<Category> findCategories(List<Long> ids){
		return categoryRepository.findAll(ids);
	}

}
