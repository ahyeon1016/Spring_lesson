package com.springmvc.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.domain.Book;
import com.springmvc.service.BookService;

@Controller
@RequestMapping(value="/books", method = RequestMethod.GET)
public class BookController {
	
	// BookService객체가 servlet-context에서 component-scan으로 스캔 되어야 @Autowired 가능하다.
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public String requestBookList(Model model) {
		System.out.println("컨트롤러 BookController-requestBookList() 호출");
		ArrayList<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list);
		return "books";
	}
	
	@GetMapping(value="/all")
	public String requestAllBook(Model model) {
		System.out.println("컨트롤러 BookController-requestAllBook() 호출");
		ArrayList<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list);
		return "books";
	}
}
