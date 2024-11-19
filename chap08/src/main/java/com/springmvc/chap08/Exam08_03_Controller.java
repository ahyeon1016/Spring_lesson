package com.springmvc.chap08;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Exam08_03_Controller {

	@GetMapping("/exam03")
	public String requestMethod(Model model) {
		System.out.println("��Ʈ�ѷ� | /exam03 ����");
		return "webpage08_03";
	}
	
	@GetMapping("/admin/tag")
	public String requestMethod02(Model model) {
		System.out.println("��Ʈ�ѷ� | /admin/tag ����, ��������");
		return "webpage08_03";
	}
}
