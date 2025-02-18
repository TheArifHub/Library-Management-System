package com.library_management_system.library_management_system.helper;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Service
public class MessageManage {
	
	public void remove()
	{
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		HttpSession session= request.getSession();
		
		session.removeAttribute("error");
		session.removeAttribute("success");
	}
}
