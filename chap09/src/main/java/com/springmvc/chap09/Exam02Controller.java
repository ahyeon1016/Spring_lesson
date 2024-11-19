package com.springmvc.chap09;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Exam02Controller {
	
	//���� ���ε带 ���� �� ������ ����
	@GetMapping("/form")
	public String requestForm() {
		System.out.println("��Ʈ�ѷ� | requestForm() ȣ��");
		return "webpage09_01";
	}
	
	//�� ���������� �Է��� �̸��� ������ �ް�, images������ ������ �����ϴ� �Լ�
	@PostMapping("/form")
	public String submitForm(@RequestParam("name") String name, @RequestParam("fileImage") MultipartFile file, HttpServletRequest request) {
		System.out.println("��Ʈ�ѷ� | submitForm() ȣ��");
		String filename = file.getOriginalFilename();
		// ������ ������ 
		String images = request.getServletContext().getRealPath("resources/images");
		System.out.println(images);
		File f = new File(images+"\\"+name+"_"+filename);
		
		try {
			file.transferTo(f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "webpage09_submit";
	}
}
