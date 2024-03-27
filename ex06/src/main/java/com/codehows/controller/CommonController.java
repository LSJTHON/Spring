package com.codehows.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {

	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) { 
		//model 은 자바 -> View : 값을 전달하는 용도
		//DTO : 자바 -> 자바
		//VO : DB -> 자바 (list나 set을 이용 
		log.info("access Denied : "+auth);
		
		model.addAttribute("msg","Access Denied");
	}
	
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		log.info("error: "+error);
		
		log.info("logout: "+logout);
		
		if(error != null) {
			model.addAttribute("error","Login Error Check Yout Account");
			
		}
		if(logout != null) {
			model.addAttribute("logout","Logout!!!!");
		}
	}
	
	@GetMapping("/customLogout")
	public void logoutGET() {
		log.info("cutomLogout");
	}
	
}
