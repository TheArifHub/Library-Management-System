package com.library_management_system.library_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
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
			session.setAttribute("member", member);
			return "login";
		}
	}

	public String homePage(HttpSession session) {
		if (session.getAttribute("member") != null) {
			return "member-home";
		} else {
			session.setAttribute("error", "Invalid Session Login Again");
			return "redirect:/login";
		}
	}

	public String memberProfile(HttpSession session) {
		if (session.getAttribute("member") != null) {
			return "member-profile";
		} else {
			session.setAttribute("error", "Invalid Sesssion Login Again");
			return "redirect:/login";
		}
	}

	public String editProfile(int id, HttpSession session, ModelMap map) {
		if (session.getAttribute("member") != null) {
			Member member = memberRepository.findById(id).orElseThrow();
			map.put("member", member);
			return "member-edit-profile.html";
		} else {
			session.setAttribute("error", "Invalid Session Login Again");
			return "redirect:/login";
		}
	}

	public String updateProfile(@Valid Member member, BindingResult bindingResult, HttpSession session) {
		if (session.getAttribute("member") != null) {
			if (bindingResult.hasErrors())
				return "member-edit-profile";

			Member existingMember = memberRepository.findById(member.getId()).orElseThrow();
			existingMember.setFullName(member.getFullName());
			existingMember.setEmail(member.getEmail());
			existingMember.setPhone(member.getPhone());
			existingMember.setAddress(member.getAddress());

			memberRepository.save(existingMember);
			session.setAttribute("success", "Profile Updated Successfully");
			return "redirect:/member/member-profile";

		} else {
			session.setAttribute("error", "Invalid Session, Login Again");
			return "redirect:/login";
		}
	}

}
