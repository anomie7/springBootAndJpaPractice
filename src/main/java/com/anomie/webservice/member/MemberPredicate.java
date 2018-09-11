package com.anomie.webservice.member;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class MemberPredicate {
	public static Predicate memberSearch(MemberDto memberDto) {
		QMember member = QMember.member;
		BooleanBuilder builder = new BooleanBuilder();
		String userName = memberDto.getName();
		String city = memberDto.getCity();
		String street = memberDto.getStreet();
		String zipcode = memberDto.getZipcode();
		
		if(userName != null && userName != "") {
			builder.and(member.name.eq(userName));
		}
		
		if(city != null && city != "")
			builder.and(member.address.city.eq(city));
		
		if(street != null && street != "")
			builder.and(member.address.street.eq(street));
		
		if(zipcode != null && zipcode != "")
			builder.and(member.address.zipcode.eq(zipcode));
		
		return builder;
	}
}
