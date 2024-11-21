package com.springmvc.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class exam01_2 {
	@GetMapping("/json")
	public String showForm() {
		return "webpage14_02";
	}

	@PostMapping("/test")
	public @ResponseBody HashMap<String, Object> submit(@RequestBody HashMap<String, Object> map) {
		System.out.println(map);
		return map;
	}
}
