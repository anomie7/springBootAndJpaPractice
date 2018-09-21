package com.anomie.webservice.commons;

import com.anomie.webservice.order.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearchVO {
	private String memberName;
	private OrderStatus orderStatus;
}
