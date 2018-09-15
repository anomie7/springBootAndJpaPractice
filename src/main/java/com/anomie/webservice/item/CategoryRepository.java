package com.anomie.webservice.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	public List<Category> findAllByParentIsNull();
}
