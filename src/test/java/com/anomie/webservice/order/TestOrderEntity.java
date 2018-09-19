package com.anomie.webservice.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.anomie.webservice.category.Category;
import com.anomie.webservice.item.Book;
import com.anomie.webservice.item.Item;
import com.anomie.webservice.member.Address;
import com.anomie.webservice.member.Member;

@RunWith(SpringRunner.class)
@SpringBootTest @ActiveProfiles("test")
@Transactional
public class TestOrderEntity {
	
	@Autowired
	private OrderService orderService;
	
	private Member member;
	private Address address;
	private Delivery delivery;
	private Order order;
	private Category bookParent;
	private Category bookChild1;
	private Book bookItem1;
	private OrderItem orderItem;
	
	@Before
	public void generatedObj() {
		address = Address.builder().city("대구").stress("교학로 38").zipcode("854-9").build();
		member = Member.builder().name("민우").address(address).build();
		delivery = Delivery.builder().status(DeliveryStatus.READY).address(member.getAddress()).build();
		order = Order.builder().status(OrderStatus.ORDER).delivery(delivery).member(member).build();
		bookParent = Category.builder().name("책").build();
		bookChild1 = Category.builder().name("페미니즘").parent(bookParent).build();
		bookItem1 = Book.builder().author("김소은").isbn("120-0").name("92년생 김소은").price(10000).stockQuantity(2000).build();
		bookItem1.addCategory(bookChild1);
		orderItem = OrderItem.builder().count(3).orderPrice(1000).item(bookItem1).build();
		order.addOrderItem(orderItem);
		orderService.save(order);
	}
	
	@Test
	public void insertOrder() {
		Order ordertest = orderService.findItemOne(1L);
		Delivery testdelivery = ordertest.getDelivery();
		Member testMember = ordertest.getMember();
		OrderItem testOrderItem =  ordertest.getOrderItems().get(0);
		Item testItem = testOrderItem.getItem();
		Category testCategory = testItem.getCategories().get(0);
		assertThat(ordertest).isEqualTo(ordertest);
	}

}
