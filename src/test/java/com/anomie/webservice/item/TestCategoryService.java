package com.anomie.webservice.item;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.anomie.webservice.category.Category;
import com.anomie.webservice.category.CategoryRepository;
import com.anomie.webservice.category.CategoryService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest @Transactional
public class TestCategoryService {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private Category bookParent;
	private Category bookChild1;
	private Category bookChild2;
	
	private Category albumParent;
	private Category albumChild1;
	private Category albumChild2;
	
	@Before
	public void generateObj() {
		bookParent = Category.builder().name("책").build();
		bookChild1 = Category.builder().name("페미니즘").parent(bookParent).build();
		bookChild2 = Category.builder().name("소설").parent(bookParent).build();
		albumParent = Category.builder().name("힙합").build();
		albumChild1 = Category.builder().name("한국힙합").parent(albumParent).build();
		albumChild2 = Category.builder().name("쇼미더머니").parent(albumParent).build();
		
		bookParent.addChild(bookChild1);
		bookParent.addChild(bookChild2);
		
		albumParent.addChild(albumChild1);
		albumParent.addChild(albumChild2);
		
		categoryRepository.save(bookParent);
		categoryRepository.save(albumParent);
	}
	
	@Test 
	public void findParentCateories() {
		List<Category> testLs = categoryService.findParentCateories();
		assertEquals("조회한 객체의 개수가 틀립니다.", 2,testLs.size());
		assertEquals(bookChild1, testLs.get(0).getChild().get(0));
		assertEquals(bookChild2, testLs.get(0).getChild().get(1));
		assertEquals(albumChild1, testLs.get(1).getChild().get(0));
		assertEquals(albumChild2, testLs.get(1).getChild().get(1));
	}

}
