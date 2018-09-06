package com.anomie.webservice.member;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
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
}
