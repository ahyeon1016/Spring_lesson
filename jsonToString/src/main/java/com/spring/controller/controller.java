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
	
	//DTO ��> JSON
	@ResponseBody
	@GetMapping("/project01")
	public String project01() {
		BookDTO dto = new BookDTO("�ڹ�", 21000, "������", 670);
		System.out.println(dto.toString());
		json = gs.toJson(dto);
		System.out.println(json);
		
		return json;
	} 
	
	//JSON ��> DTO
	@GetMapping("/project02")
	public String project02() {
		BookDTO dto = gs.fromJson(json, BookDTO.class);
		System.out.println("project2 | "+dto.toString());
		return "index";
	}
	
	//(����DTO �Ѥ�> ArrayList) �Ѥ�> JSON
	@ResponseBody
	@GetMapping("/project03")
	public String project03() {
		BookDTO dto1 = new BookDTO("�ڹ�1", 21000, "������1", 670);
		BookDTO dto2 = new BookDTO("�ڹ�2", 21000, "������2", 670);
		BookDTO dto3 = new BookDTO("�ڹ�3", 21000, "������3", 670);
		
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
		BookDTO dto1 = new BookDTO("�ڹ�1", 21000, "������1", 670);
		BookDTO dto2 = new BookDTO("�ڹ�2", 21000, "������2", 670);
		BookDTO dto3 = new BookDTO("�ڹ�3", 21000, "������3", 670);
		
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		list.add(dto1);
		list.add(dto2);
		list.add(dto3);
		
		String jsonlist = gs.toJson(list);
		
		//JSON(ArrayList) �Ѥ�> ArrayList<BookDTO>
		
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
		students1.put("name", "ȫ�浿");
		students1.put("phone", "010-1111-1111");
		students1.put("address", "����");
		System.out.println(students1);
		
		JSONObject students2 = new JSONObject();
		students2.put("name", "���浿");
		students2.put("phone", "010-2222-2222");
		students2.put("address", "����");
		System.out.println(students2);
		
		students.put(students1);
		students.put(students2);
		
		JSONObject object = new JSONObject();
		
		object.put("students", students);
		
		System.out.println(object.toString(2));
		
	}
}
