package com.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.Book;
import com.springmvc.exception.BookIdException;
import com.springmvc.exception.CategoryException;
import com.springmvc.service.BookService;

@Controller

@RequestMapping(value="/books") //, method = RequestMethod.GET
public class BookController {
	
	// BookService��ü�� servlet-context���� component-scan���� ��ĵ �Ǿ�� @Autowired �����ϴ�.
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public String requestBookList(Model model) {
		System.out.println("��Ʈ�ѷ� | BookController-requestBookList() ȣ��");
		ArrayList<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list);
		return "books";
	}
	
	@GetMapping(value="/all")
	public ModelAndView requestAllBook(Model model) {
		System.out.println("��Ʈ�ѷ� | BookController-requestAllBook() ȣ��");
		ModelAndView mav = new ModelAndView();
		ArrayList<Book> list = bookService.getAllBookList();
		mav.addObject("bookList", list);
		mav.setViewName("books");
		return mav;
	}
	
	@GetMapping("/{category}")
	public String requestBooksByCategory(@PathVariable("category") String bookCategory, Model model) {
		System.out.println("��Ʈ�ѷ� | PathVariable = "+bookCategory);
		ArrayList<Book> booksByCategory = bookService.getBookListByCategory(bookCategory);
		
		if(booksByCategory==null||booksByCategory.isEmpty()) {
			throw new CategoryException();
		}
		
		model.addAttribute("bookList", booksByCategory);
		return "books";
	}
	
	@GetMapping("/filter/{bookFilter}")
	//                                                                          Ű ���� �ΰ��̱� ������ List�� ����ϴ� �� �ϳ���� String ����ص� ������.
	public String requestBooksByFilter(@MatrixVariable(pathVar="bookFilter") Map<String, List<String>> bookFilter, Model model){ 
		System.out.println("��Ʈ�ѷ� | requestBooksByFilter ȣ��. �Ķ���� : /filter/"+bookFilter);	
		Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);		
		model.addAttribute("bookList", booksByFilter);
		return "books";
	}
	
	@GetMapping("/book")
	public String requestBookById(@RequestParam("id") String bookId, Model model) {
		System.out.println("��Ʈ�ѷ� | requestBookById()ȣ�� �Ķ���� bookId�� �� = "+bookId);
		Book bookById = bookService.getBookById(bookId);	
		model.addAttribute("book", bookById);
		return "book";
	}
	
	@GetMapping("/add")
	public String requestAddBookForm(@ModelAttribute Book book) {
		System.out.println("��Ʈ�ѷ� | requestAddBookForm() ȣ�� addBook �� ������ �̵�");
		return "addBook";
	}
	
	@PostMapping("/add")
	public String submitAddNewBook(@Valid @ModelAttribute Book book, BindingResult result, Model model, HttpServletRequest request) {
		System.out.println("��Ʈ�ѷ� | submitAddNewBook() ȣ�� addBook �� ���������� submitŬ����");
		
		if(result.hasErrors()) {
			return "addBook";
		}
		
		//1. ���� �̸� �����
		MultipartFile bookImage = book.getBookImage();
		
		String savePath = request.getServletContext().getRealPath("/resources/img");
		String saveName = bookImage.getOriginalFilename();
		
		System.out.println("����ѷ� | ���� ���� ��� : "+savePath);
		System.out.println("����ѷ� | ������ �߰��� ������ �̸� : "+saveName);
		
		//2. �� ���� ����
		File file = new File(savePath, saveName);
		 
		//3. ������ ���Ͽ� ���� �ۼ�
		if(bookImage!=null && !bookImage.isEmpty()) {
			try {
				bookImage.transferTo(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("���� �̹��� ���ε忡 �����߽��ϴ�.", e);
			}
		}
		
		bookService.setNewBook(book);
		return "redirect:/books";
	}
	
	@ModelAttribute
	public void addAttribute(Model model) {
		System.out.println("��Ʈ�ѷ� | addAttribute() ȣ��");
		model.addAttribute("addTitle", "�ű� ���� ���");
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		System.out.println("��Ʈ�ѷ� | initBiner()ȣ�� ���ε� ��� �׸� ����");
		binder.setAllowedFields("bookId", "name", "unitPrice", "author", "description", "publisher", "category", "unitsInStock", "releaseDate", "condition", "bookImage","totalPages");
	}

	@ExceptionHandler(value={BookIdException.class})
	public ModelAndView handleError(HttpServletRequest request, BookIdException exception) {
		System.out.println("��Ʈ�ѷ� | handleError()");
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidBookId", exception.getBookId());
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL()+"?"+request.getQueryString());
		mav.setViewName("errorBook");
		return mav;
	}
}

