package com.springmvc.repository;

import java.util.ArrayList;

import com.springmvc.domain.Book;

public interface BookRepository {
	ArrayList<Book> getAllBookList();
}
