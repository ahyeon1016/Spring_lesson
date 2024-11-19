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
		System.out.println("컨트롤러 | requestForm() 호출 폼 페이지 제공");
		return "webpage09_03";
	}
	
	@PostMapping("/form")
	public String submitForm(@ModelAttribute Member member, HttpServletRequest request, HttpSession session) {
		System.out.println("컨트롤러 | submitForm() 호출");
		// 1. 파일 이름 만들기
		String save = request.getServletContext().getRealPath("/resources/images");
		String name = member.getName();
		MultipartFile file = member.getImageFile();
		String filename = file.getOriginalFilename();
		
		System.out.println("컨드롤러 | 경로 : "+save);
		System.out.println("컨드롤러 | 폼에서 입력한 name : "+name);
		System.out.println("컨드롤러 | 폼에서 추가한 파일의 이름 : "+filename);		
		
		// 2. 비어있는 파일 생성
		File f = new File(save, name+"_"+filename);
		
		// 3. 생성한 파일에 내용 작성
		try {
			file.transferTo(f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "webpage09_03_submit";
	}
	
}
