package com.library_management_system.library_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.library_management_system.library_management_system.dto.Member;
import com.library_management_system.library_management_system.helper.AES;
import com.library_management_system.library_management_system.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;

	public String registerPage(@Valid Member member, BindingResult result, HttpSession session) {

		if (memberRepository.existsByEmail(member.getEmail()))
			result.rejectValue("email", "error.email", "* Email should be unique");

		if (memberRepository.existsByPhone(member.getPhone()))
			result.rejectValue("phone", "error.phone", "* Phone number should be unique");

		if (!member.getPassword().equals(member.getConfirmPassword()))
			result.rejectValue("confirmPassword", "error.confirmPassword",
					"* Password and Confirm Password should be matching");
		if (result.hasErrors()) {
			return "member-register";
		} else {
			member.setPassword(AES.encrypt(member.getPassword()));
			memberRepository.save(member);
			session.setAttribute("success", "Account Created Successfully!");
			return "login";
		}
	}
}
