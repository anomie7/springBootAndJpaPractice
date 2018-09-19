package com.anomie.webservice.member;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.anomie.webservice.order.Order;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {
	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	@Column(nullable = false)
	private String name;
	@Embedded
	private Address address;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="member")
	private List<Order> orders = new ArrayList<>();

	@Builder
	public Member(Long id, String name, Address address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
}
