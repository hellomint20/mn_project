<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/am/css/customer/modi.css">
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<%@ include file = "../common/sidebar.jsp" %>
	
	<div class="all">
		<div class="all_title">
			<strong>정보 수정</strong>
			<p>회원님의 정보입니다</p>
		</div>
		
		<div class="info">
			<div class="info-title">
	    		아이디 : <br>
	    		비밀번호 : <br>
	    		이름 : <br>
	    		전화번호 :<br>
	    		e-mail : <br>
			</div>
			
			<div class="info-content">
		   		<input type="text" id="t_b" name="c_id" readonly placeholder="아이디"><br>
		   		<input type="password" id="t_b" name="c_pw" value="비밀번호"><br>
		   		<input type="text" id="t_b" name="c_name" value="이름"><br>
		   		<input type="text" id="t_b" name="c_tel" value="전화번호"><br>
		   		<input type="text" id="t_b" name="c_email" value="e-mail"><br>
		   	</div>
		</div>
	    
	    <div class="btn_a">
		    <button type="button" class="cancel" onclick="location.href='/am/customerInfo'">취소</button>
		    <button type="button" class="completed" onclick="#">완료</button>
	    </div>
	</div>
	
</body>
</html>