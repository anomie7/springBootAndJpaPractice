package com.anomie.webservice.commons;

import java.util.Date;

import com.anomie.webservice.order.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Setter
@Getter @AllArgsConstructor
public class OrderDTO {
	private Long orderId;
	private String memberName;
	private String itemName;
	private int orderPrice;
	private int orderCount;
	private OrderStatus orderStatus;
	private Date orderDate;
}
