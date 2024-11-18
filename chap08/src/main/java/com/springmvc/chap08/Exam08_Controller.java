package com.springmvc.chap08;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Exam08_Controller {
	
	@GetMapping("/")
	public String requestMethod00(Model model) {
		System.out.println("��Ʈ�ѷ� | index.jsp�� �����Ƿ� ��ü webpage08_01.jsp�� �̵�");
		return "webpage08_01";
	}
	
	@GetMapping("/exam01")
	public String requestMethod(Model model) {
		System.out.println("��Ʈ�ѷ� | /exam01");
		return "webpage08_01";
	}
	
	@GetMapping("/admin/main")
	public String requestMethod02(Model model) {
		System.out.println("��Ʈ�ѷ� | /admin/main");
		model.addAttribute("data", "adminPage.jsp");
		return "adminPage"; 
	}
	
	@GetMapping("/manager/main")
	public String requestMethod03(Model model) {
		System.out.println("��Ʈ�ѷ� | /manager/main");
		model.addAttribute("data", "managerPage.jsp");
		return "managerPage";
	}
	
	@GetMapping("/member/main")
	public String requestMethod04(Model model) {
		System.out.println("��Ʈ�ѷ� | /member/main");
		model.addAttribute("data", "memberPage.jsp");
		return "memberPage";
	}
	
	@GetMapping("/home/main")
	public String requestMethod05(Model model) {
		System.out.println("��Ʈ�ѷ� | /home/main");
		model.addAttribute("data", "homePage.jsp");
		return "homePage";
	}
}
