package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class test {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String tCon(Model model) {
		String app ="appapapp";
		model.addAttribute("web", app);
		return "home";
	}
}
