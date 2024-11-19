<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
	System.out.println("addBook.jsp 도착");
%>
<!DOCTYPE html>
<html>
<head>
	<link href="/Spring_BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
	<meta charset="UTF-8">
	<title>도서 등록</title>
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="./home">Home</a>
				<a class="navbar-brand" href="./books">도서 목록</a>
				<a class="navbar-brand" href="./add">도서 등록</a>				
			</div>
		</div>
	</nav>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">도서 등록</h1>
		</div>
	</div>
	<div class="container">
	
		<div class="float-right">
			<form:form action="${pageContext.request.contextPath}/logout" method="post">
				<input type="submit" class="btn btn-sm btn-success" value="Logout">
			</form:form>
		</div>
		<!-- action="add?${_csrf.parameterName}=${_csrf.token}" -->		
		<form:form modelAttribute="book" action="add?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" enctype="multipart/form-data">
			<fieldset>
				<legend>${addTitle}</legend>
				<div class="form-group row">
					<label class="col-sm-2 control-label">도서ID</label>
					<div class="col-sm-3">
						<form:input path="bookId" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">도서명</label>
					<div class="col-sm-3">
						<form:input path="name" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">가격</label>
					<div class="col-sm-3">
						<form:input path="unitPrice" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">저자</label>
					<div class="col-sm-3">
						<form:input path="author" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">상세정보</label>
					<div class="col-sm-5">
						<form:textarea path="description" cols="50" rows="2" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">출판사</label>
					<div class="col-sm-3">
						<form:input path="publisher" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">분야</label>
					<div class="col-sm-3">
						<form:input path="category" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">재고수</label>
					<div class="col-sm-3">
						<form:input path="unitsInStock" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">출판일</label>
					<div class="col-sm-3">
						<form:input path="releaseDate" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">상태</label>
					<div class="col-sm-3">
						<form:radiobutton path="condition" value="New"/>New
						<form:radiobutton path="condition" value="Old"/>Old
						<form:radiobutton path="condition" value="E-Book"/>E-Book
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-2 control-label">도서 이미지</label>
					<div class="col-sm-3">
						<form:input path="bookImage" type="file" class="form-control"/>
					</div>
				</div>	
							
				<div class="form-group row">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value="등록"/>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
	
	<hr>
	<footer class="container">
		<hr>
		<p>&copy; WebMarket</p>
	</footer>
</body>
</html>