<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AniMedi</title>
	<link rel="stylesheet" href="/am/css/customer/info.css">
</head>
<body>

	<%@ include file = "../default/header.jsp" %>
	
	<div class="title"><h2>회원 정보</h2></div>
	
	<div class="all">
	<%@ include file = "../common/sidebar.jsp" %>
	
		<div id="info">
			<table>
				<tr>
					<th>ID</th>
					<td>angduzzang</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>앵두맘</td>
				</tr>
				<tr>
					<th>email</th>
					<td>angdu@naver.com</td>
				</tr>
				<tr>
					<th>Tel</th>
					<td>010-1234-1234</td>
				</tr>
			</table>
		</div>
	</div>
	
	<button class="modi-b" onclick="#">수정</button>
		
</body>
</html>