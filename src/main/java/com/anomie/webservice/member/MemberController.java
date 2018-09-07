package com.anomie.webservice.member;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.anomie.webservice.commons.PageDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping(path="/join")
	public String goToCreatePage(Model model) {
		return "member/join";
	}
	
	@PostMapping(path="/join")
	public String create(Model model,@Valid MemberDto memberDto) {
		Member member = memberService.save(memberDto.toEntity());
		model.addAttribute("member", member);
		return "redirect:/";
	}
	
	@GetMapping(path="/members/{page}/{size}")
	public String getMemberList(Model model,@PathVariable int page,@PathVariable int size) {
		PageRequest pageable = new PageRequest(page, size);
		PageDTO ms = memberService.findMembers(pageable);
		model.addAttribute("PageDTO", ms);
		model.addAttribute("memberList", ms.getContent());
		return "member/list";
	}
	
	@GetMapping(path="/members")
	public String getMemberList(Model model) {
		List<MemberDto> ms = memberService.findMembers();
		model.addAttribute("memberList", ms);
		return "member/list";
}
	
}
