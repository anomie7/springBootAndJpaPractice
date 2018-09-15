package com.anomie.webservice.item;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestItemService {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ItemService itemService;
	
	private Category bookParent;
	private Category bookChild1;
	private Category bookChild2;
	private Book bookItem1;
	
	private Category albumParent;
	private Category albumChild1;
	private Category albumChild2;
	private Album albumItem1;
	
	@Before
	public void generateObj() {
		bookItem1 = Book.builder().author("김소은").isbn("120-0").name("92년생 김소은").price(10000).stockQuantity(2000).build();
		bookParent = Category.builder().name("책").build();
		bookChild1 = Category.builder().name("페미니즘").parent(bookParent).build();
		bookChild2 = Category.builder().name("소설").parent(bookParent).build();
		bookItem1.addCategory(bookChild1);
		bookItem1.addCategory(bookChild2);
		
		albumItem1 = Album.builder().artist("기리보이").name("flex").price(10000).stockQuantity(10000).build();
		albumParent = Category.builder().name("힙합").build();
		albumChild1 = Category.builder().name("한국힙합").parent(albumParent).build();
		albumChild2 = Category.builder().name("쇼미더머니").parent(albumParent).build();
		albumItem1.addCategory(albumChild1);
		albumItem1.addCategory(albumChild2);
		
		itemService.save(bookItem1);
		itemService.save(albumItem1);
	}
	
	@Test @Transactional
	public void testSave() {
		final String notAddedCategoryMsg = "추가한 카테고리가 일치하지 않습니다.";
		final String notAddedParentMsg = "카테고리의 상속관계가 매핑되지 않았습니다.";
		
		Item testBook = itemService.findOne(bookItem1.getId());
		assertEquals(notAddedCategoryMsg ,testBook.getCategories().get(0), bookChild1);
		assertEquals(notAddedCategoryMsg ,testBook.getCategories().get(1), bookChild2);
		assertEquals(notAddedParentMsg, testBook.getCategories().get(0).getParent(), bookChild1.getParent());
		assertEquals(notAddedParentMsg , testBook.getCategories().get(1).getParent(), bookChild2.getParent());
		
		Item testAlbum = itemService.findOne(albumItem1.getId());
		assertEquals(notAddedCategoryMsg ,testAlbum.getCategories().get(0), albumChild1);
		assertEquals(notAddedCategoryMsg ,testAlbum.getCategories().get(1), albumChild2);
		assertEquals(notAddedParentMsg, testAlbum.getCategories().get(0).getParent(), albumChild1.getParent());
		assertEquals(notAddedParentMsg , testAlbum.getCategories().get(1).getParent(), albumChild2.getParent());
	}
	
	@Test
	public void testFindItems() {
		List<Item> items = itemService.findItems();
		assertEquals("정렬 조건이 틀렸습니다.", albumItem1.getId(), items.get(0).getId() );
	}
	
	@Test
	public void testUpdate() {
		Book testBook =	 Book.builder().id(bookItem1.getId()).author("헤밍웨이").isbn("1212-2").name("노인과 바다").price(12).stockQuantity(22).build();
		testBook.addCategory(bookItem1.getCategories().get(0));
		testBook.addCategory(bookItem1.getCategories().get(1));
		itemService.save(testBook);
		assertEquals(testBook.getId(), bookItem1.getId());
	}
	
	@Test
	public void testDelete() {
		itemService.delete(albumItem1);
	}
	
	@Test
	public void testDeleteAll() {
		itemService.deleteAll();
	}
}
