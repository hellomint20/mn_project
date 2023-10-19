<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<div class="all_title">
			<strong>탈퇴하기</strong>
			<p>비밀번호를 입력해주세요</p>
		</div>
		<div class="info">
			<div class="info-title">
				비밀번호: 
			</div>
			<div class="info-content">
				<input type="password" id="t_b" name="c_pw">
			</div>
		</div>
		<button type="button" class="b" onclick="#">탈퇴하기</button>
	</div>
</body>
</html>