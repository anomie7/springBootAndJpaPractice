package com.anomie.webservice.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.anomie.webservice.member.Address;
import com.anomie.webservice.member.Member;

@RunWith(SpringRunner.class)
@DataJpaTest @ActiveProfiles("test")
public class TestOrderEntity {
	
	@Autowired
	private OrderRepository orderRepository;
	
	private Member member;
	private Address address;
	private Delivery delivery;
	private Order order;
	
	@Before
	public void generatedObj() {
		address = Address.builder().city("대구").stress("교학로 38").zipcode("854-9").build();
		member = Member.builder().name("민우").address(address).build();
		delivery = Delivery.builder().status(DeliveryStatus.READY).address(member.getAddress()).build();
		order = Order.builder().status(OrderStatus.ORDER).delivery(delivery).member(member).build();
		orderRepository.save(order);
	}
	
	@Test
	public void insertOrder() {
		assertThat(order).isEqualTo(orderRepository.findOne(1L));
	}

}
