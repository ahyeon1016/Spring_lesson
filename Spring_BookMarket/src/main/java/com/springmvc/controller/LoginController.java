package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(Model model) {
		System.out.println("��Ʈ�ѷ� | �α���");
		return "login";
	}
	
	@GetMapping("/loginfailed")
	public String loginerror(Model model) {
		System.out.println("��Ʈ�ѷ� | �α��� ����");
		model.addAttribute("error", "ture");
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "login";
	}
}
