<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>장바구니</title>
	<link href="/Spring_BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
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
			<h1 class="display-3">장바구니</h1>
		</div>
	</div>	
	
	<div class="container">
		<div>
			<a href="#" class="btn btn-success float-right">주문하기</a>
		</div>
		<div style="padding-top : 50px">
			<table class="table table-hover">
				<tr>
					<th>도서</th>
					<th>가격</th>
					<th>수량</th>
					<th>소계</th>
					<th>비교</th>
				</tr>
				<c:forEach items="${cart.cartItem}" var="item">
				<tr>
					<td>${item.value.book.bookId}-${item.value.book.name}</td>
					<td>${item.value.book.unitPrice}</td>
					<td>${item.value.quantity}</td>
					<td>${item.value.totalPrice}</td>
				</tr>
				</c:forEach>
				<tr>
					<th></th>
					<th></th>
					<th>총액</th>
					<th>${cart.grandTotal}</th>
					<th></th>
				</tr>
			</table>
			<a href="/Spring_BookMarket/books" class="btn btn-secondary">&laquo; 쇼핑 계속하기</a>
		</div>
		<hr>
		<footer class="container">
			<p>&copy; WebMarket</p>
		</footer>
	</div>
</body>
</html>