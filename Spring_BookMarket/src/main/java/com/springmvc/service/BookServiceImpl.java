package com.springmvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.domain.Book;
import com.springmvc.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public void test() {
		System.out.println("Service-test");
	}
	
	@Override
	public ArrayList<Book> getAllBookList() {
		System.out.println("서비스 | getAllBookList()호출 BookRepository의 getAllBookList()호출 후 리턴 값 리턴");
		return bookRepository.getAllBookList();
	}

	@Override
	public ArrayList<Book> getBookListByCategory(String category) {
		System.out.println("서비스 | getBookListByCategory()호출 BookRepository의 getBookListByCategory(category)호출 후 리턴 값 리턴");
		ArrayList<Book> booksByCategory = bookRepository.getBookListByCategory(category);
		return booksByCategory;
	}

	@Override
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		System.out.println("서비스 | getBookListByFilter()호출 리턴 값 리턴");
		Set<Book> booksByFilter = bookRepository.getBookListByFilter(filter);
		return booksByFilter; 
	}
	
	
}
