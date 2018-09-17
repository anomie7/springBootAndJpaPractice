package com.anomie.webservice.item;

import java.util.List;
import java.util.stream.Collectors;

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
	public List<ItemDTO> findItems() {
		List<Item> items = itemRepository.findAllByOrderByIdDesc();
		List<ItemDTO> result = items.stream().map(m -> ItemDTO.builder().itemId(m.getId()).itemName(m.getName()).price(m.getPrice())
				.stockQuantity(m.getStockQuantity()).build()).collect(Collectors.toList());
		return result;
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
