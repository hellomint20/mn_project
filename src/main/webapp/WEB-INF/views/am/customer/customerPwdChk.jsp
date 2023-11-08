<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<link rel="stylesheet" href="/am/css/customer/delete.css">
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<%@ include file = "../common/sidebar.jsp" %>
	
	
	<div class="all">
	<c:choose>
		<c:when test="${dto.cPw == 'naver' }">
		<div class="all_title">
			<strong>정보 수정</strong>
			<p>네이버 로그인환경입니다</p>
		</div>
		<div class="info"></div>
		
		</c:when>
		<c:otherwise>
		<div class="all_title">
			<strong>정보 수정</strong>
			<p>비밀번호를 입력해주세요</p>
		</div>
		<form action="customerPwdChk" method="post">
			<div class="info">
			<div class="info-title">
				비밀번호: 
			</div>
			<div class="info-content">
				<input type="hidden" name="id" value="${userId}">
				<input type="password" id="t_b" name="pw" required>
			</div>
			</div>
		<button type="submit" class="b">확인</button>
		</form>
		</c:otherwise>
		</c:choose>
	</div>
</body>
</html>