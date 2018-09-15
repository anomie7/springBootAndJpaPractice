package com.anomie.webservice.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Item> findItems() {
		return itemRepository.findAllByOrderByIdDesc();
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
