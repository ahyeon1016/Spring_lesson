<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<!-- 정적 리소스는 절대경로 표시를 추천함 -->
	<link href="/Spring_BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
	<meta charset="UTF-8">
<title>HOME</title>
</head>
<body>
<!-- 
	<div class ="container py-4">
	  <div class = "p-5 mb-4 bg-body-tertiary rounded-3">
	    <div class = "container-fluid py-5">
	      <h1 class = "display-5 fw-bold">${greeting}</h1>
	      <p class = "col-md-8 fs-4">BookMarket</p>
	    </div>
	  </div>
	  
	  <div class = "row align-items-md-stretch text-center">
	    <div class = "col-md-12">
	      <div class = "h-100 p-5">
	        <h3>${strapline}</h3>
	      </div>
	    </div>
	  </div>
	</div>
-->	

	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="./home">Home</a>
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