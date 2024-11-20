<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%
		System.out.println("errorBook.jsp 도착");
		//request로 가져와짐.
		String url = (String) request.getAttribute("url");
		System.out.println(url);
	%>
	<link href="/Spring_BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
	<meta charset="UTF-8">
	<title>도서 상세 정보</title>
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="../home">Home</a>
				<a class="navbar-brand" href="../books">Books</a>
				<a class="navbar-brand" href="./add">도서 등록</a>					
			</div>
		</div>
	</nav>
	<div class="hero bg-light py-5">
		<div class="container">
			<h2 class="alert alert-danger">
				해당 도서가 존재하지 않습니다.<br>
				도서ID : ${invalidBookId} 
			</h2>
		</div>
	</div>	
	
	<div class="container">
		<p>${url}
		<p>${exception}
	</div>
	<div class="container">
		<a href="../books" class="btn btn-secondary">&copy; 도서목록</a>
	</div>	
</body>
</html>