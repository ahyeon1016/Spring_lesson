<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
	System.out.println("updateForm.jsp 도착");
%>
<!DOCTYPE html>
<html>
<head>
	<link href="/Spring_BookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
	<meta charset="UTF-8">
	<title>도서 상세 정보</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<img alt="image" src="/Spring_BookMarket/resources/img/${book.fileName}" style="width: 100%">
				<p>${book.fileName}
			</div>
			<div class="col-md-7">
				<!-- action="add?${_csrf.parameterName}=${_csrf.token}" -->		
				<form:form modelAttribute="updateBook" action="update?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" enctype="multipart/form-data">
					<fieldset>
						<!-- 도서ID -->
						<div class="form-group row">
							<label class="col-sm-2 control-label">도서ID</label>
							<div class="col-sm-6">
								<form:input id="bookId" path="bookId" type="hidden" class="form-control" value="${book.bookId}"/>
								<span class="badge badge-Info">${book.bookId}</span>
							</div>
						</div>
						<!-- 도서명 -->
						<div class="form-group row">
							<label class="col-sm-2 control-label">도서명</label>
							<div class="col-sm-6">
								<form:input path="name" class="form-control" value="${book.name}"/>
							</div>
						</div>
						<!-- 가격 -->
						<div class="form-group row">
							<label class="col-sm-2 control-label">가격</label>
							<div class="col-sm-6">
								<form:input path="unitPrice" class="form-control" value="${book.unitPrice}"/>
							</div>
						</div>
						<!-- 저자 -->
						<div class="form-group row">
							<label class="col-sm-2 control-label">저자</label>
							<div class="col-sm-6">
								<form:input path="author" class="form-control" value="${book.author}"/>
							</div>
						</div>
						<!-- 상세정보 -->
						<div class="form-group row">
							<label class="col-sm-2 control-label">상세정보</label>
							<div class="col-sm-10">
								<form:input path="description" cols="50" rows="2" class="form-control" value="${book.description}"/>
							</div>
						</div>
						<!-- 출판사 -->
						<div class="form-group row">
							<label class="col-sm-2 control-label">출판사</label>
							<div class="col-sm-6">
								<form:input path="publisher" class="form-control" value="${book.publisher}"/>
							</div>
						</div>
						<!-- 분류 -->
						<div class="form-group row">
							<label class="col-sm-2 control-label">분류</label>
							<div class="col-sm-6">
								<form:input path="category" class="form-control" value="${book.category}"/>
							</div>
						</div>
						<!-- 재고수 -->
						<div class="form-group row">
							<label class="col-sm-2 control-label">재고수</label>
							<div class="col-sm-6">
								<form:input path="unitsInStock" class="form-control" value="${book.unitsInStock}"/>
							</div>
						</div>
						<!-- 출판일 -->
						<div class="form-group row">
							<label class="col-sm-2 control-label">출판일</label>
							<div class="col-sm-6">
								<form:input path="releaseDate" class="form-control" value="${book.releaseDate}"/>
							</div>
						</div>
						<!-- 상태 -->
						<div class="form-group row">
							<label class="col-sm-2 control-label">상태</label>
							<div class="col-sm-6">
								<form:radiobutton path="condition" value="New"/>New
								<form:radiobutton path="condition" value="Old"/>Old
								<form:radiobutton path="condition" value="E-Book"/>E-Book
							</div>
						</div>
						<!-- 이미지 -->
						<div class="form-group row">
							<label class="col-sm-2 control-label">이미지</label>
							<div class="col-sm-6">
								<form:input path="bookImage" type="file" class="form-control"/>
							</div>
						</div>	
						<!-- 전송 -->			
						<div class="form-group row">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" class="btn btn-primary" value="수정"/>
								<a href="/Spring_BookMarket/books" class="btn btn-primary">취소</a>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
		
	</div>
</body>
</html>