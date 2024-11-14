package com.springmvc.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.domain.Book;
import com.springmvc.repository.BookRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookService;
	
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public String requestBookList(Model model) {
		System.out.println("��Ʈ�ѷ� BookController-requestBookList() ȣ��");
		ArrayList<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list);
		return "books";
	}
}
