package com.spring.controller;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.spring.dto.BookDTO;

@Controller
@RequestMapping("/test")
public class controller {
	
	public String json;
	Gson gs = new Gson();
	
	@RequestMapping("/case1")
	public String index() {
		return "index";
	}
	
	//DTO ㅡ> JSON
	@ResponseBody
	@GetMapping("/project01")
	public String project01() {
		BookDTO dto = new BookDTO("자바", 21000, "에이콘", 670);
		System.out.println(dto.toString());
		json = gs.toJson(dto);
		System.out.println(json);
		
		return json;
	} 
	
	//JSON ㅡ> DTO
	@GetMapping("/project02")
	public String project02() {
		BookDTO dto = gs.fromJson(json, BookDTO.class);
		System.out.println("project2 | "+dto.toString());
		return "index";
	}
	
	//(여러DTO ㅡㅡ> ArrayList) ㅡㅡ> JSON
	@ResponseBody
	@GetMapping("/project03")
	public String project03() {
		BookDTO dto1 = new BookDTO("자바1", 21000, "에이콘1", 670);
		BookDTO dto2 = new BookDTO("자바2", 21000, "에이콘2", 670);
		BookDTO dto3 = new BookDTO("자바3", 21000, "에이콘3", 670);
		
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		list.add(dto1);
		list.add(dto2);
		list.add(dto3);
		
		String jsonlist = gs.toJson(list);
		
		return jsonlist;
	}
	
	@ResponseBody
	@GetMapping("/project04")
	public String project04() {
		BookDTO dto1 = new BookDTO("자바1", 21000, "에이콘1", 670);
		BookDTO dto2 = new BookDTO("자바2", 21000, "에이콘2", 670);
		BookDTO dto3 = new BookDTO("자바3", 21000, "에이콘3", 670);
		
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		list.add(dto1);
		list.add(dto2);
		list.add(dto3);
		
		String jsonlist = gs.toJson(list);
		
		//JSON(ArrayList) ㅡㅡ> ArrayList<BookDTO>
		
		ArrayList<BookDTO> jsonToList = gs.fromJson(jsonlist, 
				new TypeToken<ArrayList<BookDTO>>(){}.getType());
		
		for(int i=0; i<jsonToList.size(); i++) {
			BookDTO tmp = jsonToList.get(i);
			System.out.println(tmp.toString());
		}
		
		return jsonlist;
	}
	
	@ResponseBody
	@GetMapping("/project05")
	public void project05() {
		JSONArray students = new JSONArray();
		
		JSONObject students1 = new JSONObject();
		students1.put("name", "홍길동");
		students1.put("phone", "010-1111-1111");
		students1.put("address", "서울");
		System.out.println(students1);
		
		JSONObject students2 = new JSONObject();
		students2.put("name", "나길동");
		students2.put("phone", "010-2222-2222");
		students2.put("address", "광주");
		System.out.println(students2);
		
		students.put(students1);
		students.put(students2);
		
		JSONObject object = new JSONObject();
		
		object.put("students", students);
		
		System.out.println(object.toString(2));
		
	}
}
