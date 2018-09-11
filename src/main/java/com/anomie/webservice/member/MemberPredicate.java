package com.anomie.webservice.member;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class MemberPredicate {
	public static Predicate memberSearch(String userName, Address address) {
		QMember member = QMember.member;
		BooleanBuilder builder = new BooleanBuilder();
		if(userName != null && userName != "") {
			builder.and(member.name.eq(userName));
		}
		
		if(address != null) {
			String city = address.getCity();
			String street = address.getStreet();
			String zipcode = address.getZipcode();
			
			if(city != null && city != "")
				builder.and(member.address.city.eq(city));
			
			if(street != null && street != "")
				builder.and(member.address.street.eq(street));
			
			if(zipcode != null && zipcode != "")
			builder.and(member.address.zipcode.eq(zipcode));
		}
		
		return builder;
	}
}
