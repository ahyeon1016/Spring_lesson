package com.springmvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.springmvc.domain.Book;

public interface BookService {
	ArrayList<Book> getAllBookList();
	ArrayList<Book> getBookListByCategory(String category);
	Set<Book> getBookListByFilter(Map<String, List<String>> filter);
	Book getBookById(String bookId);
	void setNewBook(Book book);
}
