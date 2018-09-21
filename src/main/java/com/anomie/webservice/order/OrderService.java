package com.anomie.webservice.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anomie.webservice.commons.OrderDTO;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Transactional
	public void save(Order order) {
		orderRepository.save(order);
	}

	@Transactional
	public Order findOrderOne(Long orderId) {
		return orderRepository.findOne(orderId);
	}

	public List<OrderDTO> findOrders() {
		List<Order> orders =orderRepository.findAll();
		List<OrderDTO> orderDtoLs = new ArrayList<>();
		for (Order order : orders) {
			OrderItem orderItem = order.getOrderItems().get(0);
			OrderDTO test = OrderDTO.builder()
									.orderId(order.getId())
									.memberName(order.getMember()
									.getName())
									.orderStatus(order.getStatus())
									.orderDate(order.getOrderDate())
									.orderPrice(orderItem.getOrderPrice())
									.orderCount(orderItem.getCount())
									.itemName(orderItem.getItem().getName())
									.build();
			orderDtoLs.add(test);
		}
		return orderDtoLs;
	}
}
