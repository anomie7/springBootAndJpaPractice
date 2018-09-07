package com.anomie.webservice.member;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
	public List<Member> findByName(String name);
	public List<Member> findByAddress(Address address);
	public Page<Member> findByNameOrderByIdDesc(Pageable pageable);
	public Page<Member> findAllByOrderByIdDesc(Pageable pageable);
}
