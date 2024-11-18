<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<%
		System.out.println("books.jsp 도착");
	%>
	<link href="/Spring_BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
	<meta charset="UTF-8">
	<title>도서 목록</title>
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="./home">Home</a>
				<a class="navbar-brand" href="./books">Books</a>
				<a class="navbar-brand" href="./add">도서 등록</a>	
			</div>
		</div>
	</nav>
	<div class="hero bg-light py-5">
		<div class="container">
			<h1 class="display-3">도서 목록</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="row" align="center">
		
			<c:forEach items="${bookList}" var="book">
				<div class="col-md-4">
					<h3>${book.name}</h3>
					<p>${book.author}</p>
						<br>${book.publisher} | ${book.releaseDate}
					<p align=left>${fn:substring(book.description,0,100)}...
					<p>${book.unitPrice}원
					<p><a href="<c:url value="/books/book?id=${book.bookId}"/>"
						 class="btn btn-secondary" role="button">상세정보 &raquo;</a>
				</div> 
			</c:forEach>
		
		</div>
	</div>
	
	<footer class="container">
		<hr>
		<p>&copy; WebMarket</p>
	</footer>
</body>
</html>