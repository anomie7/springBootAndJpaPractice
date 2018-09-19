package com.anomie.webservice.order;

import javax.jdo.annotations.Column;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.anomie.webservice.item.Item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor @Getter
public class OrderItem {
	@Id @GeneratedValue
	@Column(name="ORDER_ITEM_ID")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ORDER_ID")
	private Order order;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="ITEM_ID")
	private Item item;
	
	private int orderPrice;
	
	private int count;
	
	@Builder
	public OrderItem(Long id, Order order, Item item, int orderPrice, int count) {
		super();
		this.id = id;
		this.order = order;
		this.item = item;
		this.orderPrice = orderPrice;
		this.count = count;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
