package com.anomie.webservice.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
	public List<Member> findByName(String name);
	public List<Member> findByAddress(Address address);
}
