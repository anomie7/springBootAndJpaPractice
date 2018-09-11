package com.anomie.webservice.member;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		PageDTO pageDto = memberService.findMembers(pageable);
		pageDto.setDisplayPageNumbers();
		pageDto.setPageNumbers();
		model.addAttribute("PageDTO", pageDto);
		model.addAttribute("memberList", pageDto.getContent());
		return "member/list";
	}
	
	@GetMapping(path="/members")
	public String getMemberList(Model model) {
		List<MemberDto> ms = memberService.findMembers();
		model.addAttribute("memberList", ms);
		return "member/list";
	}
	
	@GetMapping(path="/api/members/{page}/{size}")
	@ResponseBody
	public PageDTO getJsonMemberList(@PathVariable int page,@PathVariable int size, MemberDto memberDto) {
		PageRequest pageable = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
		PageDTO pageDto = memberService.findMembersByNameAndAddress(pageable, memberDto);
		pageDto.setDisplayPageNumbers();
		pageDto.setPageNumbers();
		return pageDto;
	}
	
	@GetMapping(path="/vue/members")
	public String getVuejsList(Model model) {
		return "vue_list";
	}
}
