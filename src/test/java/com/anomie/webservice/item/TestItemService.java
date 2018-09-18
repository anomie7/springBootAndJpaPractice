package com.anomie.webservice.item;

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

import com.anomie.webservice.category.Category;
import com.anomie.webservice.category.CategoryService;
import com.anomie.webservice.commons.ItemDTO;

@RunWith(SpringRunner.class)
@SpringBootTest @ActiveProfiles("test")
public class TestItemService {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	CategoryService categoryService;
	
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
		
		itemRepository.save(bookItem1);
		itemRepository.save(albumItem1);
	}
	
	@Test @Transactional
	public void testFindByItemIdAndDtype() {
		final String notAddedCategoryMsg = "추가한 카테고리가 일치하지 않습니다.";
		final String notAddedParentMsg = "카테고리의 상속관계가 매핑되지 않았습니다.";
		
		//테스트 코드에서는 dtype 매핑하지 못함..그러나 프로덕션 코드에서는 매핑 잘됨...
		ItemDTO testBook = itemService.findOne(bookItem1.getId(), "B");
		ItemDTO testAlbum = itemService.findOne(albumItem1.getId(), "A");
		
		assertEquals(notAddedCategoryMsg ,testBook.getCategory_id().get(0), bookChild1.getId() );
		assertEquals(notAddedCategoryMsg ,testBook.getCategory_id().get(1), bookChild2.getId() );
		assertEquals(notAddedCategoryMsg ,testAlbum.getCategory_id().get(0), albumChild1.getId());
		assertEquals(notAddedCategoryMsg ,testAlbum.getCategory_id().get(1), albumChild2.getId());
	}
	
	@Test @Transactional
	public void testItemDtoSave() {
		List<Long> ids = new ArrayList<>();
		ids.add(bookChild1.getId());
		ids.add(bookChild2.getId());
		ItemDTO itemDTO = ItemDTO.builder().kindOfItem("movie").itemName("나홀로 집에").actor("맥컬리 컬킨").director("추석 특선").category_id(ids).price(2000).stockQuantity(100).build();
		itemService.save(itemDTO);
	}
	
	@Test
	public void testFindItems() {
		List<ItemDTO> items = itemService.findItems();
		assertEquals("정렬 조건이 틀렸습니다.", albumItem1.getId(), items.get(0).getItemId() );
	}
	
	@Test
	public void testUpdate() {
		Book testBook =	 Book.builder().id(bookItem1.getId()).author("헤밍웨이").isbn("1212-2").name("노인과 바다").price(12).stockQuantity(22).build();
		testBook.addCategory(bookItem1.getCategories().get(0));
		testBook.addCategory(bookItem1.getCategories().get(1));
		itemRepository.save(testBook);
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
