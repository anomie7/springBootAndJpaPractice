package com.anomie.webservice.order;

import javax.jdo.annotations.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.anomie.webservice.member.Address;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor @Getter
public class Delivery {
	@Id @GeneratedValue
	@Column(name="DELIVERY_ID")
	private Long id;
	
	@Embedded
	private Address address;
	
	@OneToOne(mappedBy="delivery")
	private Order order;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;
	
	public void deliveryComp() {
		this.status = DeliveryStatus.COMP;
	}
	
	@Builder
	public Delivery(Long id, Address address, Order order, DeliveryStatus status) {
		super();
		this.id = id;
		this.address = address;
		this.order = order;
		this.status = status;
	}
	
}
