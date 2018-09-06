package com.anomie.webservice.member;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
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
}
