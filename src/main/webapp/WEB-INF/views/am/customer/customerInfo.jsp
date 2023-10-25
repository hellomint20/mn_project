<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AniMedi</title>

	<link rel="stylesheet" href="/am/css/customer/info.css">
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<%@ include file = "../common/sidebar.jsp" %>
	
	<div class="all">
		<div class="all_title">
			<strong>회원 정보</strong>
			<p>${dto.cName} 회원님의 정보입니다</p>
		</div>
		<div class="info">
			<div class="info-title">
		    		아이디 : <br>
		    		이름 : <br>
		    		전화번호 :<br>
		    		e-mail : <br>
			</div>
			
	  		<div class="info-content">
	  			${dto.cId}<br>
	  			${dto.cName}<br>
	  			${dto.cTel}<br>
	  			${dto.cEmail}<br>
	  		</div>
		</div>
	   
	    <button type="button" class="b" onclick="location.href='/am/customerModify?id=${userId}'">수정</button>
	</div>
	
</body>
</html>