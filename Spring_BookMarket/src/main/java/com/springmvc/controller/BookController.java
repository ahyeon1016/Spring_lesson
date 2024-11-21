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
	
	// BookService객체가 servlet-context에서 component-scan으로 스캔 되어야 @Autowired 가능하다.
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public String requestBookList(Model model) {
		System.out.println("컨트롤러 | BookController-requestBookList() 호출");
		ArrayList<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list);
		return "books";
	}
	
	@GetMapping(value="/all")
	public ModelAndView requestAllBook(Model model) {
		System.out.println("컨트롤러 | BookController-requestAllBook() 호출");
		ModelAndView mav = new ModelAndView();
		ArrayList<Book> list = bookService.getAllBookList();
		mav.addObject("bookList", list);
		mav.setViewName("books");
		return mav;
	}
	
	@GetMapping("/{category}")
	public String requestBooksByCategory(@PathVariable("category") String bookCategory, Model model) {
		System.out.println("컨트롤러 | PathVariable = "+bookCategory);
		ArrayList<Book> booksByCategory = bookService.getBookListByCategory(bookCategory);
		
		if(booksByCategory==null||booksByCategory.isEmpty()) {
			throw new CategoryException();
		}
		
		model.addAttribute("bookList", booksByCategory);
		return "books";
	}
	
	@GetMapping("/filter/{bookFilter}")
	//                                                                          키 값이 두개이기 때문에 List를 사용하는 것 하나라면 String 사용해도 괜찮음.
	public String requestBooksByFilter(@MatrixVariable(pathVar="bookFilter") Map<String, List<String>> bookFilter, Model model){ 
		System.out.println("컨트롤러 | requestBooksByFilter 호출. 파라미터 : /filter/"+bookFilter);	
		Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);		
		model.addAttribute("bookList", booksByFilter);
		return "books";
	}
	
	@GetMapping("/book")
	public String requestBookById(@RequestParam("id") String bookId, Model model) {
		System.out.println("컨트롤러 | requestBookById()호출 파라미터 bookId의 값 = "+bookId);
		Book bookById = bookService.getBookById(bookId);	
		model.addAttribute("book", bookById);
		return "book";
	}
	
	@GetMapping("/add")
	public String requestAddBookForm(@ModelAttribute Book book) {
		System.out.println("컨트롤러 | requestAddBookForm() 호출 addBook 폼 페이지 이동");
		return "addBook";
	}
	
	@PostMapping("/add")
	public String submitAddNewBook(@Valid @ModelAttribute Book book, BindingResult result, Model model, HttpServletRequest request) {
		System.out.println("컨트롤러 | submitAddNewBook() 호출 addBook 폼 페이지에서 submit클릭함");
		
		if(result.hasErrors()) {
			return "addBook";
		}
		
		//1. 파일 이름 만들기
		MultipartFile bookImage = book.getBookImage();
		
		String savePath = request.getServletContext().getRealPath("/resources/img");
		String saveName = bookImage.getOriginalFilename();
		
		System.out.println("컨드롤러 | 파일 저장 경로 : "+savePath);
		System.out.println("컨드롤러 | 폼에서 추가한 파일의 이름 : "+saveName);
		
		//2. 빈 파일 생성
		File file = new File(savePath, saveName);
		 
		//3. 생성한 파일에 내용 작성
		if(bookImage!=null && !bookImage.isEmpty()) {
			try {
				bookImage.transferTo(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("도서 이미지 업로드에 실패했습니다.", e);
			}
		}
		
		bookService.setNewBook(book);
		return "redirect:/books";
	}
	
	@ModelAttribute
	public void addAttribute(Model model) {
		System.out.println("컨트롤러 | addAttribute() 호출");
		model.addAttribute("addTitle", "신규 도서 등록");
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		System.out.println("컨트롤러 | initBiner()호출 바인딩 허용 항목 지정");
		binder.setAllowedFields("bookId", "name", "unitPrice", "author", "description", "publisher", "category", "unitsInStock", "releaseDate", "condition", "bookImage","totalPages");
	}

	@ExceptionHandler(value={BookIdException.class})
	public ModelAndView handleError(HttpServletRequest request, BookIdException exception) {
		System.out.println("컨트롤러 | handleError()");
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidBookId", exception.getBookId());
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL()+"?"+request.getQueryString());
		mav.setViewName("errorBook");
		return mav;
	}
}

