package com.anomie.webservice.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Transactional
	public void save(Order order) {
		orderRepository.save(order);
	}

	@Transactional
	public Order findItemOne(Long orderId) {
		return orderRepository.findOne(orderId);
	}
}
