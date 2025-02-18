package com.library_management_system.library_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.library_management_system.library_management_system.dto.Librarian;
import com.library_management_system.library_management_system.dto.Member;
import com.library_management_system.library_management_system.helper.AES;
import com.library_management_system.library_management_system.repository.LibrarianRepository;
import com.library_management_system.library_management_system.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;


@Service
public class GeneralService {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	LibrarianRepository librarianRepository;
	
	public String loginPage(String emailOrId, String password, HttpSession session, ModelMap map) {
	   
	    Member member = memberRepository.findByEmail(emailOrId);
	    if (member != null && AES.decrypt(member.getPassword()).equals(password)) {
	        session.setAttribute("success", "Login Successful as Member!");
	        return "member-home";
	    }

	    Librarian librarian = librarianRepository.findByLibrarianId(emailOrId);
	    if (librarian != null && AES.decrypt(librarian.getPassword()).equals(password)) {
	        session.setAttribute("success", "Login Successful as Librarian!");
	        return "librarian-home"; 
	    }

	    map.put("member", new Member());
	    session.setAttribute("error", "Invalid Email/ID or Password");
	    return "login";
	}


}
