<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h2>스프링 시큐리티 예</h2>
	<sec:authorize access="hasRole('ROLE_MANAGER')" var="isAdmin">
		<p><h3>매니저 권한 화면입니다.</h3>
	</sec:authorize>
	
	<c:choose>
		<c:when test="${isAdmin}">
			<p>ROLE_MANAGER 권한 로그인 중입니다.
			<p><a href="<c:url value='/exam02' />">웹 요청 URL /exam02로 이동하기</a>
		</c:when>
		<c:otherwise>
			<p>로그인 중이 아닙니다.
			<p><a href="<c:url value='/manager/tag' />">웹 요청 URL /manager/tag로 이동하기</a>			
		</c:otherwise>
	</c:choose>
</body>
</html>