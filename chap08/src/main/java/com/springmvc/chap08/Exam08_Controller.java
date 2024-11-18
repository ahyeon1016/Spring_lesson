package com.springmvc.chap08;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Exam08_Controller {
	
	@GetMapping("/")
	public String requestMethod00(Model model) {
		System.out.println("컨트롤러 | index.jsp가 없으므로 대체 webpage08_01.jsp로 이동");
		return "webpage08_01";
	}
	
	@GetMapping("/exam01")
	public String requestMethod(Model model) {
		System.out.println("컨트롤러 | /exam01");
		return "webpage08_01";
	}
	
	@GetMapping("/admin/main")
	public String requestMethod02(Model model) {
		System.out.println("컨트롤러 | /admin/main");
		model.addAttribute("data", "adminPage.jsp");
		return "adminPage"; 
	}
	
	@GetMapping("/manager/main")
	public String requestMethod03(Model model) {
		System.out.println("컨트롤러 | /manager/main");
		model.addAttribute("data", "managerPage.jsp");
		return "managerPage";
	}
	
	@GetMapping("/member/main")
	public String requestMethod04(Model model) {
		System.out.println("컨트롤러 | /member/main");
		model.addAttribute("data", "memberPage.jsp");
		return "memberPage";
	}
	
	@GetMapping("/home/main")
	public String requestMethod05(Model model) {
		System.out.println("컨트롤러 | /home/main");
		model.addAttribute("data", "homePage.jsp");
		return "homePage";
	}
}
