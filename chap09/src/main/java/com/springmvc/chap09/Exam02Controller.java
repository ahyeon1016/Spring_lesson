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
	
	//파일 업로드를 위한 폼 페이지 제공
	@GetMapping("/form")
	public String requestForm() {
		System.out.println("컨트롤러 | requestForm() 호출");
		return "webpage09_01";
	}
	
	//폼 페이지에서 입력한 이름과 파일을 받고, images폴더에 파일을 저장하는 함수
	@PostMapping("/form")
	public String submitForm(@RequestParam("name") String name, @RequestParam("fileImage") MultipartFile file, HttpServletRequest request) {
		System.out.println("컨트롤러 | submitForm() 호출");
		String filename = file.getOriginalFilename();
		// 파일을 저장할 
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
