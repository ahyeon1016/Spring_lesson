<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<!-- 정적 리소스는 절대경로 표시를 추천함 -->
	<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
	<meta charset="UTF-8">
<title>HOME</title>
</head>
<body>

	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="./home">Home</a>
				<a class="navbar-brand" href="./books">Books</a>
				<a class="navbar-brand" href="./addBook">도서 등록</a>	
			</div>
		</div>
	</nav>
	<div class="hero bg-light py-5">
		<div class="container">
			<h1 class="display-3">${greeting}</h1>
		</div>
	</div>
	<div class="container">
		<div class="text-center">
			<h3>${strapline}</h3>
		</div>
	</div>
	<footer class="container">
		<hr>
		<p>&copy; WebMarket</p>
	</footer>
</body>
</html>