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
	<%@ include file = "../common/mediSidebar.jsp" %>

	<div class="all">
		<div class="all_title">
			<strong>비밀번호 확인</strong>
			<p>비밀번호를 입력해주세요</p>
		</div>
	<form action="mediPwdChk" method="post">
		<div class="info">
			<div class="info-title">
				비밀번호: 
			</div>
			<div class="info-content">
				<input type="hidden" name="id" value="${mediId}">
				<input type="password" id="t_b" name="pw" required>
			</div>
		</div>
		<button type="submit" class="b">확인</button>
	</form>
	</div>


</body>
</html>