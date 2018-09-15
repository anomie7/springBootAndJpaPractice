package com.anomie.webservice.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;
	
	@Transactional
	public void save(Item item) {
		itemRepository.save(item);
	}
	
	@Transactional
	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}
	
	@Transactional
	public Page<Item> findAll(Pageable pageable) {
		return itemRepository.findAll(pageable);
	}
	
	@Transactional
	public void delete(Item item) {
		itemRepository.delete(item);
	}
	
	@Transactional
	public void deleteAll() {
		itemRepository.deleteAll();
	}
}
