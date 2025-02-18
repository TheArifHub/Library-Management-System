package com.library_management_system.library_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.library_management_system.library_management_system.dto.Librarian;
import com.library_management_system.library_management_system.helper.AES;
import com.library_management_system.library_management_system.repository.LibrarianRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class LibrarianSerivice {

	@Autowired
	LibrarianRepository librarianRepository;
	
	public String registerPage(Librarian librarian, BindingResult result, HttpSession session) {
		
		if(!librarian.getPassword().equals(librarian.getConfirmPassword()))
			result.rejectValue("confirmPassword", "error.confirmPassword","* Password and ConfirmPassword should be mathing");
		
		if(librarianRepository.existsByEmail(librarian.getEmail()))
			result.rejectValue("email", "error.email","* Email must be unique");
		
		if(librarianRepository.existsByPhone(librarian.getPhone()))
			result.rejectValue("phone", "error.phone","* Phone Number should be unique");
		
		if(result.hasErrors())
			return "librarian-register.html";
		
		else {
			librarian.setPassword(AES.encrypt(librarian.getPassword()));
			librarianRepository.save(librarian);
			session.setAttribute("success", "Account Created Success");
			return "redirect:/login";
		}
		
	}

}
