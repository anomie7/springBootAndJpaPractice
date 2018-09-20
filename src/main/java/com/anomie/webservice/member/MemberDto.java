package com.anomie.webservice.member;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
	@Id @GeneratedValue
    @Column(name = "MEMBER_ID")
	private Long id;
	@NotEmpty
	private String name;
	private String city;
	private String street;
	private String zipcode;
	
	
	public Member toEntity() {
		return Member.builder()
				.name(name)
				.id(id)
				.address(
				Address.builder().city(city).stress(street
						)
				.zipcode(zipcode).build())
				.build();
	}

	@Builder
	public MemberDto(Long id, String name, String city, String street, String zipcode) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
}
