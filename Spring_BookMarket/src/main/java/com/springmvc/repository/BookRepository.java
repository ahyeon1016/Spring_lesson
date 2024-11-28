package com.springmvc.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.springmvc.domain.Book;

public interface BookRepository {
	ArrayList<Book> getAllBookList();
	ArrayList<Book> getBookListByCategory(String category);	
	Set<Book> getBookListByFilter(Map<String, List<String>> filter);
	Book getBookById(String bookId);
	void setNewBook(Book book);
	void setUpdateBook(Book book);
	void setDeleteBook(String bookID);
}
