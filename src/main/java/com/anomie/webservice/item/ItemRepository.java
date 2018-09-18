package com.anomie.webservice.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
	public List<Item> findAllByOrderByIdDesc();
	public Item findByIdAndDtype(Long id, String dtype);
}
