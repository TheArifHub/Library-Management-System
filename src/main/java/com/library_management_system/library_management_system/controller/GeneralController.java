package com.library_management_system.library_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.library_management_system.library_management_system.dto.Member;
import com.library_management_system.library_management_system.service.GeneralService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GeneralController {

	@Autowired
	GeneralService generalService;

	@GetMapping("/")
	public String loadHomePage() {
		return "home";
	}

	@GetMapping("/login")
	public String loadLoginPage(Member member, ModelMap map) {
		map.put("member", member);
		return "login";
	}

	@PostMapping("/login")
	public String loginPage(@RequestParam String email, @RequestParam String password, HttpSession session,
			ModelMap map) {
		return generalService.loginPage(email, password, session, map);
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		return generalService.logout(session);
	}

}
