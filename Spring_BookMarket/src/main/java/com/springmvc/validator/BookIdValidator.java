package com.springmvc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.springmvc.domain.Book;
import com.springmvc.service.BookService;

public class BookIdValidator implements ConstraintValidator<BookId, String> {
	
	@Autowired
	private BookService bookService; 
	
	@Override
	public void initialize(BookId constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Book book;
		try {
			book = bookService.getBookById(value);
		} catch(Exception e) {
			return true;
		}
		
		if(book!=null) {
			return false;
		}
		return true;
	}	
}
