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
	
	public void test() {
		System.out.println("Service-test");
	}
	
	@Override
	public ArrayList<Book> getAllBookList() {
		System.out.println("getAllBookList()ȣ�� BookRepository�� getAllBookList()ȣ�� �� ���� �� ����");
		return bookRepository.getAllBookList();
	}

	@Override
	public ArrayList<Book> getBookListByCategory(String category) {
		System.out.println("getBookListByCategory()ȣ�� BookRepository�� getBookListByCategory(category)ȣ�� �� ���� �� ����");
		ArrayList<Book> booksByCategory = bookRepository.getBookListByCategory(category);
		return booksByCategory;
	}
	
	
}
