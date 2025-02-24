package com.library_management_system.library_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library_management_system.library_management_system.dto.Member;
import com.library_management_system.library_management_system.repository.MemberRepository;
import com.library_management_system.library_management_system.service.MemberService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	@Autowired
	MemberRepository memberRepository;

	@GetMapping("/register")
	public String registerPage(Member member, ModelMap map) {
		map.put("member", member);
		return "member-register";
	}

	@PostMapping("/register")
	public String registerPage(@Valid Member member, BindingResult bindingResult, HttpSession session) {
		return memberService.registerPage(member, bindingResult, session);
	}

	@GetMapping("/member-home")
	public String homePage(HttpSession session) {
		return memberService.homePage(session);
	}

	@GetMapping("/member-profile")
	public String memberProfile(HttpSession session) {
		return memberService.memberProfile(session);
	}

	@GetMapping("/edit-profile/{id}")
	public String editProfile(@PathVariable int id, HttpSession session, ModelMap map) {
		return memberService.editProfile(id, session, map);
	}

	@PostMapping("/update")
	public String updateProfile(@Valid Member member, BindingResult bindingResult, HttpSession session) {
		return memberService.updateProfile(member, bindingResult, session);
	}

}
