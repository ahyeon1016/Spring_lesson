package com.springmvc.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.domain.Book;
import com.springmvc.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public ArrayList<Book> getAllBookList() {
		System.out.println("getAllBookList()호출 BookRepository의 getAllBookList()호출 후 리턴 값 리턴");
		return bookRepository.getAllBookList();
	}

}
