package com.anomie.webservice.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	public Member save(Member member) {
		return memberRepository.save(member);
	}
	
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public List<Member> findByName(String name){
		return memberRepository.findByName(name);
	}
	
	public List<Member> findByAddress(Address address){
		return memberRepository.findByAddress(address);
	}
}
