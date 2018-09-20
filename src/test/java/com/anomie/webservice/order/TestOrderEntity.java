package com.anomie.webservice.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.anomie.webservice.category.Category;
import com.anomie.webservice.commons.OrderDTO;
import com.anomie.webservice.commons.OrderItemDTO;
import com.anomie.webservice.item.Album;
import com.anomie.webservice.item.Book;
import com.anomie.webservice.item.Item;
import com.anomie.webservice.item.ItemRepository;
import com.anomie.webservice.member.Address;
import com.anomie.webservice.member.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class TestOrderEntity {

	@Autowired
	private OrderService orderService;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	OrderRepository orderRepository;

	private Member member;
	private Address address;
	private Delivery delivery;
	private Order order;
	private Category bookParent;
	private Category bookChild1;
	private Book bookItem1;
	private OrderItem orderItem;

	private Category albumParent;
	private Category albumChild1;
	private Album albumItem1;

	@Before
	public void generatedObj() {
		address = Address.builder().city("대구").stress("교학로 38").zipcode("854-9").build();
		member = Member.builder().name("민우").address(address).build();
		delivery = Delivery.builder().status(DeliveryStatus.READY).address(member.getAddress()).build();
		order = Order.builder().status(OrderStatus.ORDER).delivery(delivery).member(member).build();
		bookParent = Category.builder().name("책").build();
		bookChild1 = Category.builder().name("페미니즘").parent(bookParent).build();
		bookItem1 = Book.builder().author("김소은").isbn("120-0").name("92년생 김소은").price(10000).stockQuantity(2000)
				.build();
		bookItem1.addCategory(bookChild1);

		orderItem = OrderItem.builder().count(3).orderPrice(1000).build();
		orderItem.addItem(bookItem1);
		order.addOrderItem(orderItem);

		albumItem1 = Album.builder().artist("기리보이").name("flex").price(10000).stockQuantity(10000).build();
		albumParent = Category.builder().name("힙합").build();
		albumChild1 = Category.builder().name("한국힙합").parent(albumParent).build();
		albumItem1.addCategory(albumChild1);

		OrderItem orderItem2 = OrderItem.builder().count(10).orderPrice(1000).build();
		orderItem2.addItem(albumItem1);
		order.addOrderItem(orderItem2);

		orderService.save(order);
	}

	@Test
	public void testFindOrderOne() {
		order = orderService.findOrderOne(order.getId());
		Delivery testdelivery = order.getDelivery();
		testdelivery.deliveryComp();
		Member testMember = order.getMember();
		OrderItem testOrderItem = order.getOrderItems().get(0);
		OrderItem testOrderItem2 = order.getOrderItems().get(1);
		Item testItem = testOrderItem.getItem();
		Category testCategory = testItem.getCategories().get(0);

		assertThat(order).isEqualTo(order);
	}

	@Test
	public void testDeliveryComp() {
		order = orderService.findOrderOne(order.getId());
		order.setStatusComp();
		assertThat(delivery.getStatus()).isEqualTo(DeliveryStatus.COMP);
	}

	@Test
	public void testOrderCancle() {
		final String msg = "주문 취소가 실패해서 재고가 일치하지 않습니다.";
		order = orderService.findOrderOne(order.getId());
		order.orderCancle();
		assertEquals(msg, 2000, order.getOrderItems().get(0).getItem().getStockQuantity());
		assertEquals(msg, 10000, order.getOrderItems().get(1).getItem().getStockQuantity());
	}

	@Test
	public void testFindOrders() {
		List<OrderDTO> orderDtoLs = orderService.findOrders();
		for (OrderDTO orderDTO : orderDtoLs) {
			assertEquals(order.getId(), orderDTO.getOrderId());
		}
	}

	@After
	public void after() {
		orderRepository.deleteAll();
	}
}
