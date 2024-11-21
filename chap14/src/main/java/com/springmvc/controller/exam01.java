package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam01")
public class exam01 {
	@GetMapping
	public String showForm() {
		return "webpage14_01";
	}

	@PostMapping
	public String submit(@RequestBody String param, Model model) {
		model.addAttribute("title", "@RequestBody로 정보 받기");
		model.addAttribute("result", param);
		return "webpage14_result";
	}
}
