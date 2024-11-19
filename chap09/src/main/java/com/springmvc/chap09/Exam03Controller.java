package com.springmvc.chap09;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.springmvc.domain.Member;

@Controller
@RequestMapping("/exam03")
public class Exam03Controller {
	
	@GetMapping("/form")
	public String requestForm(Member member) {
		System.out.println("��Ʈ�ѷ� | requestForm() ȣ�� �� ������ ����");
		return "webpage09_03";
	}
	
	@PostMapping("/form")
	public String submitForm(@ModelAttribute Member member, HttpServletRequest request, HttpSession session) {
		System.out.println("��Ʈ�ѷ� | submitForm() ȣ��");
		// 1. ���� �̸� �����
		String save = request.getServletContext().getRealPath("/resources/images");
		String name = member.getName();
		MultipartFile file = member.getImageFile();
		String filename = file.getOriginalFilename();
		
		System.out.println("����ѷ� | ��� : "+save);
		System.out.println("����ѷ� | ������ �Է��� name : "+name);
		System.out.println("����ѷ� | ������ �߰��� ������ �̸� : "+filename);		
		
		// 2. ����ִ� ���� ����
		File f = new File(save, name+"_"+filename);
		
		// 3. ������ ���Ͽ� ���� �ۼ�
		try {
			file.transferTo(f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "webpage09_03_submit";
	}
	
}
