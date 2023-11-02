<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<link rel="stylesheet" href="/am/css/medi/pwdChk.css">
<body>
	<%@ include file = "../default/header_page.jsp" %>
	
	<div class="all">
		<div class="all_title">
			<strong>비밀번호 변경</strong>
			<p>새로운 비밀번호로 변경해주세요</p>
		</div>
	<form action="mediPwdChg" method="post">
		<div class="info">
			<div class="info-title">
				기존 비밀번호: <br>
				새로운 비밀번호: 
			</div>
			<div class="info-content">
				<input type="hidden" name="mId" value="${mediId}">
				<input type="password" id="t_b" name="pw" required><br>
				<input type="password" id="t_b" name="newPw" required><br>
			</div>
		</div>
		<button type="submit" class="b">확인</button>
	</form>
	</div>
</body>
</html>