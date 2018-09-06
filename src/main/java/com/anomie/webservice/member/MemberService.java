package com.anomie.webservice.member;

import java.util.ArrayList;
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
	
	public List<MemberDto> findMembers() {
		List<Member> ms = memberRepository.findAll();
		List<MemberDto> result = new ArrayList<>();
		for (Member member : ms) {
			Address address = member.getAddress();
			MemberDto tmp = MemberDto.builder()
									.id(member.getId())
									.name(member.getName())
									.city(address.getCity())
									.street(address.getStreet())
									.zipcode(address.getZipcode()).build();
			result.add(tmp);
		}
		return result;
	}
	
	public List<Member> findByName(String name){
		return memberRepository.findByName(name);
	}
	
	public List<Member> findByAddress(Address address){
		return memberRepository.findByAddress(address);
	}
}
