package com.spring.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.spring.dto.BookDTO;
import org.springframework.web.bind.annotation.RequestParam;


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
	public String project05() {
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
		
		return "index";
	}
	
	@GetMapping("/project06")
	public String project06() {
		String client_id = "1qw1dlvhsi";
		String client_secret = "azocJAoLaBhX3SA0sAArK4mio1ulJgwgI5OfZDwo";
		//Ű���带 �����Ͽ� InputStreamReader Ŭ������ ���� 
		//����Ʈ ������ �Է¹ް� ���� ������ �б� ���ؼ� ���۸����� ������.
		try {
			//Step 1 : �Ķ���� Ȯ���ϱ�( �˻��� �ּ� ������ �ַܼ� Ȯ�� )
			BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("�ּҸ� �Է��ϼ���.");
			String address = io.readLine();
			//�Է¹޴� ���ڿ��� �������� ���ؼ� �����͸� ������ �ν��ϹǷ� UTF-8�� �����ϸ� %20���� ��ȯ��.
			//��, ������ ��ū�� ������ ���ؼ� �Ǵܵ�
			String addr = URLEncoder.encode(address,"UTF-8");
			
			//Step 2 : URL �ۼ��ϱ�
			String reqUrl = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="+addr;
			
			//Step 3 : �ۼ��� URL�� �̿��Ͽ� ��û�� �߻� ��Ŵ
			URL url = new URL(reqUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);
			
			//Step 4 : ��û �� ���� ������ �����ϱ�
			int responseCode = con.getResponseCode();
			BufferedReader br = new BufferedReader(
								new InputStreamReader(con.getInputStream(), "UTF-8"));
			//Step 5 : ������ ������ ���ڿ� �����ͷ� ��ȯ�ϱ�
			String line;
			StringBuffer data = new StringBuffer();
			//JSON�� ���پ� �о ���� Ŭ���� �ȿ� ���پ� �Է�
			while((line=br.readLine())!=null) {
				data.append(line);
			}
			br.close();
			System.out.println(data);
			
			//Step 6 : JSON ��ü�� ��ȯ
			//������ ������ �νĽ�Ű�� ���ؼ� �ʿ��ϴ�.
			JSONTokener tok = new JSONTokener(data.toString());
			//{ }
			JSONObject obj = new JSONObject(tok);
			System.out.println(obj.getString("status"));
			//meta : { }
			JSONObject meta = obj.getJSONObject("meta");
			int totalCount = meta.getInt("totalCount");
			System.out.println(totalCount);
			//addresses : [ ]
			JSONArray arr = obj.getJSONArray("addresses");
			//0 : {}
			JSONObject first = (JSONObject)arr.get(0);
			//x : ? | y : ?
			String x = first.getString("x");
			String y = first.getString("y");
			
			System.out.println(x);
			System.out.println(y);
			
			getImage(x,y, addr);
			
			//addressElements : []
			JSONArray addressElements = first.getJSONArray("addressElements");
			//7 : {}
			JSONObject nine = (JSONObject) addressElements.get(7);
			//longName : ? | shortName : ?
			String longName = nine.getString("longName");
			String shortName = nine.getString("shortName");
			System.out.println(longName);
			System.out.println(shortName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	public void getImage(String x, String y, String addr) {
		//Step 1 : URL �ۼ�
		// https://naveropenapi.apigw.ntruss.com/map-static/v2/raster? 
		// w=300&
		// h=300&
		// center=127.1054221,37.3591614&
		// level=16
	
		// x-ncp-apigw-api-key-id: {API Key ID} 
		// x-ncp-apigw-api-key: {API Key}
		String client_id = "1qw1dlvhsi";
		String client_secret = "azocJAoLaBhX3SA0sAArK4mio1ulJgwgI5OfZDwo";
		String url = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
		String pos;
		try {
			url += "w=300&h=300&";
			url += "level=16&";
			url += "center="+x+","+y+"&";
			pos = URLEncoder.encode(x+" "+y,"UTF-8");
			url += "&markers=type:t|size:mid|pos:"+pos+"|label:"+URLEncoder.encode(addr, "UTF-8");
			
			//Step 2 : ��û �߻�
			
			URL ur = new URL(url);
			HttpURLConnection con = (HttpURLConnection) ur.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);
			
			//Step 3 : ������ ����
			InputStream is = con.getInputStream();
			//�̹����� ����Ʈ �����̱� ������ ����Ʈ �迭�� ����Ѵ�.
			byte[] bytes = new byte[1024];
			//�����̸� ����
			String imgName = Long.valueOf(new Date().getTime()).toString();
			//���ϻ���
			File file = new File(imgName+".jpg");
			file.createNewFile();
			int read = 0;
			OutputStream outputstream = new FileOutputStream(file);
			while((read=is.read(bytes))!= -1) {
				outputstream.write(bytes, 0, read);
			}
			is.close();
			outputstream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getMethodName(@RequestParam String param) {
		return new String();
	}
	
}
