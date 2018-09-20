package com.anomie.webservice.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.anomie.webservice.member.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity 
@Table(name="ORDERS")
@NoArgsConstructor @Getter 
public class Order {
	@Id @GeneratedValue
	@Column(name="ORDER_ID")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="MEMBER_ID")
	private Member member;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="DELIVERY_ID")
	private Delivery delivery;
	
	@OneToMany(mappedBy="order", cascade=CascadeType.PERSIST)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@CreatedDate
	private Date orderDate;
	
	@Builder
	public Order(Long id, Member member, Delivery delivery, List<OrderItem> orderItems, OrderStatus status) {
		super();
		this.id = id;
		this.member = member;
		this.delivery = delivery;
		this.orderItems = orderItems;
		this.status = status;
	}
	
	public void addOrderItem(OrderItem orderItem) {
		if(this.orderItems == null){
			this.orderItems = new ArrayList<>();
		}
		
		if(!this.orderItems.isEmpty() && this.orderItems.contains(orderItem)) {
			return;
		}
		this.orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((delivery == null) ? 0 : delivery.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((member == null) ? 0 : member.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (delivery == null) {
			if (other.delivery != null)
				return false;
		} else if (!delivery.equals(other.delivery))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (member == null) {
			if (other.member != null)
				return false;
		} else if (!member.equals(other.member))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	public void setStatusComp() {
		this.delivery.deliveryComp();
	}

	public void orderCancle() {
		this.status = OrderStatus.CANCEL;
		List<OrderItem> orderItems = this.orderItems;
		for (OrderItem orderItem : orderItems) {
			orderItem.cancle();
		}
	}
}
