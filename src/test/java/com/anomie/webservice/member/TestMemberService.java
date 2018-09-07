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
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMemberService {

	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	private Member member;
	private Member member2;
	private Member member3;
	Address address;

	@Before
	public void 객체_생성() {
		address = Address.builder().city("대구").stress("교학로 38").zipcode("854-9").build();
		member = Member.builder().name("민우").address(address).build();
		member2 = Member.builder().name("도담").address(address).build();
		member3 = Member.builder().name("민우").address(address).build();

		memberService.save(member2);
		memberService.save(member);
		memberService.save(member3);
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
}
