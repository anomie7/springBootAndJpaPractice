package com.anomie.webservice.category;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest @ActiveProfiles("test")
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
		albumParent = Category.builder().name("힙합").build();
		
		bookChild2 = Category.builder().name("소설").parent(bookParent).build();
		bookChild1 = Category.builder().name("페미니즘").parent(bookParent).build();
		albumChild1 = Category.builder().name("한국힙합").parent(albumParent).build();
		albumChild2 = Category.builder().name("쇼미더머니").parent(albumParent).build();
		
		categoryRepository.save(bookChild1);
		categoryRepository.save(bookChild2);
		categoryRepository.save(albumChild1);
		categoryRepository.save(albumChild2);
	}
	
	@Test
	public void findCategories() {
		List<Long> ids = new ArrayList<>();
		List<Category> expectedLs = new ArrayList<>();
		expectedLs.add(bookParent);
		expectedLs.add(bookChild1);
		expectedLs.add(bookChild2);
		ids.add(1L);
		ids.add(2L);
		ids.add(3L);
		List<Category> tmp = categoryService.findCategories(ids);
		for(int i = 0; i < tmp.size(); i++) {
			assertEquals("List<Long>로 불러온 카테고리 오브젝트들이 일치하지 않습니다.",expectedLs.get(i), tmp.get(i));
		}
	}

}
