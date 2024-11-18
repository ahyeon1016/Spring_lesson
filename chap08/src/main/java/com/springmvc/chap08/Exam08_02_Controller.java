package com.springmvc.chap08;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Exam08_02_Controller {

	@GetMapping("/exam02")
	public String requestMethod(Model model) {
		System.out.println("컨트롤러 | /exam02");
		return "webpage08_02";
	}
	
	@GetMapping("/manager/tag")
	public String requestMethod02(Model model) {
		System.out.println("컨트롤러 | /manager/tag");
		return "webpage08_02";
	}
}
