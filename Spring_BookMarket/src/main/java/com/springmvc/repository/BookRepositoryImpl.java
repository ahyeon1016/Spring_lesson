package com.springmvc.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springmvc.domain.Book;
import com.springmvc.exception.BookIdException;

@Repository
public class BookRepositoryImpl implements BookRepository {
	
	private JdbcTemplate template;
	
	@Autowired
	public void setJdbctemplate(DataSource dataSource) {
		System.out.println("������ �ҽ� ���� �Ϸ�");
		this.template = new JdbcTemplate(dataSource);
	}
	
	private ArrayList<Book> listOfBooks = new ArrayList<Book>();
	
	public BookRepositoryImpl() {		
		System.out.println("BookRepositoryImpl ��ü ����");
		Book book1 = new Book("ISBN1234", "C# ������", 30000);
		book1.setAuthor("�ڿ���");
		book1.setDescription("C# �������� ���� ù ���α׷��� ���� C#�� �����ϴ� ���ڸ� ������� �Ѵ�. Ư�� ���� ���α׷��Ӹ� ���� C# �Թ�����, C#�� ����Ͽ� ����(����Ƽ), ��, �����, IoT ���� ������ �� �ʿ��� C# ���� ������ ������ �⺻�⸦ źź�ϰ� ������ ���� �����̴�.");
		book1.setPublisher("���");
		book1.setCategory("IT������");
		book1.setUnitsInStock(1000);
		book1.setReleaseDate("2020/05/29");
		
		Book book2 = new Book("ISBN1235", "Node.js ������", 36000);
		book2.setAuthor("������");
		book2.setDescription("�� å�� ����Ʈ���� ����, �����ͺ��̽�, �������� �ƿ츣�� �������� ������ �ٷ��. �������� ���� �������� �������� �⺻ ������ Ȯ���� �����ϰ�, ����� ��ɰ� ���°踦 ����� ���鼭 ������ �����ϴ� ������ ������. ������ �ڵ�� �ֽ� ������ ����߰� �ǹ��� �����ϰų� ���� ������ �� �ִ�.");
		book2.setPublisher("���");
		book2.setCategory("IT������");
		book2.setUnitsInStock(1000);
		book2.setReleaseDate("2020/07/25");
		
		Book book3 = new Book("ISBN1236", "��� XD CC 2020", 25000);
		book3.setAuthor("�����");
		book3.setDescription("��� XD ���α׷��� ���� UI/UX �������� ������ �ϴ� ���� �����̳��� �����̿� �°� �⺻���� ������ Ȱ���� ������ �����ΰ� ��&�� ������ ������, UI ������, �� �����ο� �ִϸ��̼ǰ� ���ͷ����� ������ ������Ÿ������ �н��մϴ�.");
		book3.setPublisher("���");
		book3.setCategory("ITȰ�뼭");
		book3.setUnitsInStock(1000);
		book3.setReleaseDate("2019/05/29");
		
		listOfBooks.add(book1);
		listOfBooks.add(book2);
		listOfBooks.add(book3);
	}
	
	@Override
	public ArrayList<Book> getAllBookList() {
		System.out.println("�������丮 | getAllBookList()ȣ�� listOfBooks����");
		String SQL = "SELECT * FROM book";
		listOfBooks = (ArrayList<Book>)template.query(SQL, new BookRowMapper()); 
		return listOfBooks;
	}

	@Override
	public ArrayList<Book> getBookListByCategory(String category) {
		System.out.println("�������丮 | getBookListByCategory()ȣ�� �Ķ���ͷ� �Ѱ� ���� category�� ���� book�� category�� ��ҹ��ڸ� �������� �ʰ� ���Ͽ� ��ġ�ϴ� ī�װ��� booksByCategory�� ��� ����");
		ArrayList<Book> booksByCategory = new ArrayList<Book>();
		String SQL = "SELECT * FROM book WHERE b_category LIKE '%"+category+"%'";
		booksByCategory = (ArrayList<Book>) template.query(SQL, new BookRowMapper());
		for(int i=0; i<listOfBooks.size(); i++) {
			Book book = listOfBooks.get(i);
			if(category.equalsIgnoreCase(book.getCategory())) {
				booksByCategory.add(book);
			}
		}
		return booksByCategory;
	}

	@Override
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		System.out.println("�������丮 | getBookListByFilter() ȣ��");
		Set<Book> booksByPublisher = new HashSet<Book>();
		Set<Book> booksByCategory = new HashSet<Book>();
		Set<String> criterias = filter.keySet();
		Set<String> booksByFilter = filter.keySet();

//		if(booksByFilter.contains("publisher")) {
//			for(int i=0; i<filter.get("publisher").size(); i++) {
//				String publisherName = filter.get("publisher").get(i);
//				for(int j=0; j<listOfBooks.size(); j++) {
//					Book book = listOfBooks.get(j);					
//					if(publisherName.equalsIgnoreCase(book.getPublisher())) {
//						booksByPublisher.add(book);
//					}
//				}
//			}
//		}
//		
//		if(booksByFilter.contains("category")) {
//			for(int i=0; i<filter.get("category").size(); i++) {
//				String category = filter.get("category").get(i);				
//				List<Book> list = getBookListByCategory(category);
//				
//				booksByCategory.addAll(list);
//			}
//		}
		
		if(criterias.contains("publisher")) {
			for(int i=0; i<filter.get("publisher").size(); i++) {
				String publisherName = filter.get("publisher").get(i);
				String SQL = "SELECT * FROM book WHERE b_publisher LIKE '%"+publisherName+"%'";
				booksByPublisher.addAll(template.query(SQL, new BookRowMapper()));
			}
		}
		
		if(criterias.contains("category")){
			for(int i=0; i<filter.get("category").size(); i++) {
				String category = filter.get("category").get(i);				
				String SQL = "SELECT * FROM book WHERE b_category LIKE '%"+category+"%'";
				booksByCategory.addAll(template.query(SQL, new BookRowMapper()));
			}
		}
		
		booksByCategory.retainAll(booksByPublisher);
		return booksByCategory;
	}

	@Override
	public Book getBookById(String bookId) {
		System.out.println("�������丮 | getBookById()�Լ� ȣ�� bookId�� ��ġ�ϴ� Book ����");
		Book bookInfo = null;
//		for(int i=0; i<listOfBooks.size();i++) {
//			Book book = listOfBooks.get(i);
//			if(book != null && book.getBookId() != null && book.getBookId().equals(bookId)) {
//				bookInfo = book;
//				break;
//			}
//		}
		String SQL = "SELECT count(*) FROM book WHERE b_bookId=?";
		int rowCount = template.queryForObject(SQL, Integer.class, bookId);
		if(rowCount != 0) {
			SQL = "SELECT * FROM book WHERE b_bookId=?";
			bookInfo = template.queryForObject(SQL, new Object[] {bookId}, new BookRowMapper());
		}
		if(bookInfo == null) {
			System.out.println("�������丮 | bookInfo == null");
			//throw new IllegalArgumentException("���� ID��"+bookId+"�� �ش� ������ ã�� �� �����ϴ�.");
			throw new BookIdException(bookId);
		}
		return bookInfo;
	}
	
	@Override
	public void setNewBook(Book book) {
		System.out.println("�������丮 | setNewBook() ȣ�� listOfBook�� ������ �� book �߰�");
		String SQL = "INSERT INTO book VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		template.update(SQL, book.getBookId(), book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(), book.getReleaseDate(), book.getCondition(), book.getFileName());
//		System.out.println("�������丮 | ���� �� listOfBook�� ũ��"+listOfBooks.size());
//		listOfBooks.add(book);
//		System.out.println("�������丮 | ���� �� listOfBook�� ũ��"+listOfBooks.size());
	}
	
	@Override
	public void setUpdateBook(Book book) {
		if(book.getFileName()!=null) {
			String SQL = "UPDATE book SET b_name=?, b_unitPrice=?, b_author=?, "
					+ "b_description=?, b_publisher=?, b_category=?, "
					+ "b_unitsInStock=?, b_releaseDate=?, b_condition=?, "
					+ "b_fileName=? WHERE b_bookId=?";
			template.update(SQL, book.getName(), book.getUnitPrice(), book.getAuthor(), 
					book.getDescription(), book.getPublisher(), 
					book.getCategory(), book.getUnitsInStock(),
					book.getReleaseDate(), book.getCondition(), 
					book.getFileName(), book.getBookId());
		} else if(book.getFileName()==null) {
			String SQL = "UPDATE book SET b_name=?, b_unitPrice=?, b_author=?, "
					+ "b_description=?, b_publisher=?, b_category=?, "
					+ "b_unitsInStock=?, b_releaseDate=?, b_condition=? "
					+ "WHERE b_bookId=?";
			template.update(SQL, book.getName(), book.getUnitPrice(), book.getAuthor(), 
					book.getDescription(), book.getPublisher(), 
					book.getCategory(), book.getUnitsInStock(),
					book.getReleaseDate(), book.getCondition(), 
					book.getBookId());
		}
	}

	
	@Override
	public void setDeleteBook(String bookID) {
		String SQL = "DELETE FROM book WHERE b_bookId=?";
		template.update(SQL, bookID);
	}	

	
}
