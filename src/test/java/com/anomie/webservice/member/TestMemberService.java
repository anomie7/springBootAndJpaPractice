package com.anomie.webservice.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.anomie.webservice.commons.PageDTO;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class TestMemberService {

	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	private Member member;
	private Member member2;
	private Member member3;
	private Member member4;
	private Member member5;
	
	Address address;

	@Before
	public void 객체_생성() {
		address = Address.builder().city("대구").stress("교학로 38").zipcode("854-9").build();
		member = Member.builder().name("민우").address(address).build();
		member2 = Member.builder().name("도담").address(address).build();
		member3 = Member.builder().name("민우").address(Address.builder().city("대구").stress("대학로").zipcode("853-9").build()).build();
		member4 = Member.builder().name("현우").address(address).build();
		member5 = Member.builder().name("강한").address(address).build();
		
		memberService.save(member2);
		memberService.save(member);
		memberService.save(member3);
		memberService.save(member4);
		memberService.save(member5);
	}
	
	@After
	public void removeAllObj() {
		memberRepository.deleteAll();
	}
	
	@Test
	public void testFindMembers() {
		List<MemberDto> memberList = memberService.findMembers();
		assertThat(memberList.get(0).getId()).isEqualTo(member2.getId());
	}

	@Test
	public void testFindByName() {
		List<Member> testMember = memberService.findByName("도담");
		for (Member member : testMember) {
			assertEquals("도담", member.getName());
		}
	}

	@Test
	public void testFindByAddress() {
		List<Member> testMember = memberService.findByAddress(address);
		for (Member member : testMember) {
			assertThat(member.getAddress()).isEqualTo(address);
		}
	}
	
	@Test
	public void testPaging() {
		int page = 2;
		Pageable pageable = new PageRequest(page, 2);
		PageDTO res = memberService.findMembers(pageable);
		assertEquals("총 페이지 수가 일치하지 않습니다.", 3, res.getTotalPage());
		assertEquals("현재 페이지의 값이 일치하지 않습니다.", 3, res.getCurrentPage());
		assertEquals("불러온 컨첸츠의 총 개수가 일치하지 않습니다.", 2, res.getNumberOfElements());
	}
	
	@Test 
	public void testFindMembersByNameAndAddress() {
		int page = 0;
		Pageable pageable = new PageRequest(page, 2, new Sort(Direction.DESC, "id"));
		MemberDto memberDto = MemberDto.builder().name("민우").city("대구").street("").build();
		PageDTO pageDTO = memberService.findMembersByNameAndAddress(pageable, memberDto);
		
		assertEquals(true, pageDTO.getContent() != null);
		
		List<MemberDto> ms = (List<MemberDto>) pageDTO.getContent();
		
		for (MemberDto member : ms) {
			assertThat(member.getName()).isEqualTo("민우");
			assertThat(member.getCity()).isEqualTo("대구");
		}
	}
}
