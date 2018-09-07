package com.anomie.webservice.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anomie.webservice.commons.PageDTO;

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
	
	public PageDTO findMembers(Pageable pageable) {
		Page<Member> ms = memberRepository.findAllByOrderByIdDesc(pageable);
		List<MemberDto> result = new ArrayList<>();
		
		if(ms.getTotalPages() < pageable.getPageNumber()) {
			throw new IllegalArgumentException("총 페이지 개수보다 요청하신 페이지의 값이 초과합니다.");
		}
		
		for (Member member : ms.getContent()) {
			Address address = member.getAddress();
			MemberDto tmp = MemberDto.builder()
									.id(member.getId())
									.name(member.getName())
									.city(address.getCity())
									.street(address.getStreet())
									.zipcode(address.getZipcode()).build();
			result.add(tmp);
		}
		
		PageDTO res = PageDTO.builder().content(result)
										.currentPage(ms.getNumber())
										.numberOfElements(ms.getNumberOfElements())
										.totalPage(ms.getTotalPages())
										.isFirst(ms.isFirst())
										.isLast(ms.isLast())
										.hasNext(ms.hasNext())
										.hasPrivious(ms.hasPrevious()).build();
		return res;
	}
	
	public List<Member> findByName(String name){
		return memberRepository.findByName(name);
	}
	
	public List<Member> findByAddress(Address address){
		return memberRepository.findByAddress(address);
	}
}
