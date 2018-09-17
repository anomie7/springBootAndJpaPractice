package com.anomie.webservice.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anomie.webservice.category.Category;
import com.anomie.webservice.category.CategoryService;
import com.anomie.webservice.commons.ItemDTO;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(path="/item/create")
	public String goCreateItemPage(Model model) {
		List<Category> categoryLs = categoryService.findParentCateories();
		model.addAttribute("categoryLs",categoryLs);
		return "item/create";
	}
	
	@PostMapping(path="/api/item/create")
	@ResponseBody
	public void createItem(@RequestBody ItemDTO item) {
		itemService.save(item);
	}
	
	@GetMapping(path="/item/list")
	public String goItemListPage(Model model) {
		model.addAttribute("items", itemService.findItems());
		return "/item/list";
	}
}
