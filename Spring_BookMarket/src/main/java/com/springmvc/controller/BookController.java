package com.springmvc.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.Book;
import com.springmvc.service.BookService;

@Controller
@RequestMapping(value="/books", method = RequestMethod.GET)
public class BookController {
	
	// BookService��ü�� servlet-context���� component-scan���� ��ĵ �Ǿ�� @Autowired �����ϴ�.
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public String requestBookList(Model model) {
		System.out.println("��Ʈ�ѷ� BookController-requestBookList() ȣ��");
		ArrayList<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list);
		return "books";
	}
	
	@GetMapping(value="/all")
	public ModelAndView requestAllBook(Model model) {
		System.out.println("��Ʈ�ѷ� BookController-requestAllBook() ȣ��");
		ModelAndView mav = new ModelAndView();
		ArrayList<Book> list = bookService.getAllBookList();
		mav.addObject("bookList", list);
		mav.setViewName("books");
		return mav;
	}
	
	@GetMapping("/{category}")
	public String requestBooksByCategory(@PathVariable("category") String bookCategory, Model model) {
		System.out.println("PathVariable = "+bookCategory);
		ArrayList<Book> booksByCategory = bookService.getBookListByCategory(bookCategory);
		model.addAttribute("bookList", booksByCategory);
		return "books";
	}
}
