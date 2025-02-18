package com.library_management_system.library_management_system.controller;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.library_management_system.library_management_system.dto.Librarian;
import com.library_management_system.library_management_system.service.LibrarianSerivice;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/librarian")
public class LibrarainController {

	@Autowired
	LibrarianSerivice librarianSerivice;

	@GetMapping("/register")
	public String registerPage(Librarian librarian, ModelMap map) {
		int id = new Random().nextInt(100, 999);
		String librarianId = "MLB" + id;
		librarian.setLibrarianId(librarianId);
		map.put("librarian", librarian);
		map.put("librarianId", librarianId);
		return "librarian-register";
	}

	@PostMapping("/register")
	public String registerPage(@Valid Librarian librarian, BindingResult result, HttpSession session) {
		return librarianSerivice.registerPage(librarian, result, session);
	}
}
