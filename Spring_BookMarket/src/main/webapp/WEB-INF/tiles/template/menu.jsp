<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand navbar-dark bg-dark">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath}">BookMarket</a>
		</div>
		<div>
			<ul class="navbar-nav mr-auto">
				<li class="item"><a class="nav-link" href="/Spring_BookMarket/home">Home</a></li>
				<li class="item"><a class="nav-link" href="/Spring_BookMarket/books">Books</a></li>
				<li class="item"><a class="nav-link" href="/Spring_BookMarket/books/add">Add Book</a></li>
				<li class="item"><a class="nav-link" href="/Spring_BookMarket/cart">Cart</a></li>
			</ul>
		</div>
	</div>
</nav>