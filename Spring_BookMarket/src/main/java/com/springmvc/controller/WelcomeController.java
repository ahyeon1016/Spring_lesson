package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String first(Model model) {
		model.addAttribute("greeting", "Welcome to BookMarket");
		model.addAttribute("strapline", "Welcom to Web Shopping Mall");
		System.out.println(model.getAttribute("greeting"));
		return "welcome";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String welcome(Model model) {
		model.addAttribute("greeting", "Welcome to BookMarket");
		model.addAttribute("strapline", "Welcom to Web Shopping Mall");
		System.out.println(model.getAttribute("greeting"));
		return "welcome";
	}
}
