package com.anomie.webservice.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anomie.webservice.category.Category;
import com.anomie.webservice.category.CategoryService;
import com.anomie.webservice.commons.ItemDTO;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Transactional
	public void save(ItemDTO itemDto) {
		Item item = itemDto.toEntity();
		for (Category category : categoryService.findCategories(itemDto.getCategory_id())) {
			item.addCategory(category);
		}
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
