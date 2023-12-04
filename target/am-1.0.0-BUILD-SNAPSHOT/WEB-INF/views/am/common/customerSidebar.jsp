<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>side bar</title>

	<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    <link rel="stylesheet" href="/am/css/customerSidebar.css">
    
</head>
<body>
<c:choose>
	<c:when test="${sns eq null}">
		<div class="menu">
		    <ul>
				<li>
		        	<a href="/am/customerInfo?id=${userId }">
		        		<span class="icon"></span>
		        		<span class="title">마이페이지</span>
		        	</a>
		        </li>
		        <li>
		        	<a href="/am/customerPwdChk?id=${userId }">
		        		<span class="icon"></span>
		        		<span class="title">정보 수정</span>
		        	</a>
		        </li>
		        <li>
		        	<a href="/am/pet/petList?id=${userId}">
		        		<span class="icon"></span>
		        		<span class="title">펫 관리</span>
		        	</a>
		        </li>
		        <li>
		        	<a href="/am/reservationList?id=${userId }">
		        		<span class="icon"></span>
		        		<span class="title">예약 관리</span>
		        	</a>
		        </li>
		        <li>
		        	<a href="/am/reviewList?id=${userId }">
		        		<span class="icon"></span>
		        		<span class="title">내 글 관리</span>
		        	</a>
		        </li>
		        <li>
		        	<a href="/am/customerDelete?id=${userId }">
		        		<span class="icon"></span>
		        		<span class="title">회원 탈퇴</span>
		        	</a>
		        </li>
		    </ul>
		</div>
	
	</c:when>
	<c:otherwise>
		<div class="sns_menu">
		    <ul>
				<li>
		        	<a href="/am/customerInfo?id=${userId }">
		        		<span class="sns_icon"></span>
		        		<span class="sns_title">마이페이지</span>
		        	</a>
		        </li>
		        <li>
		        	<a href="/am/pet/petList?id=${userId}">
		        		<span class="sns_icon"></span>
		        		<span class="sns_title">펫 관리</span>
		        	</a>
		        </li>
		        <li>
		        	<a href="/am/reservationList?id=${userId }">
		        		<span class="sns_icon"></span>
		        		<span class="sns_title">예약 관리</span>
		        	</a>
		        </li>
		        <li>
		        	<a href="/am/reviewList?id=${userId }">
		        		<span class="sns_icon"></span>
		        		<span class="sns_title">내 글 관리</span>
		        	</a>
		        </li>
		    </ul>
		</div>
	</c:otherwise>
</c:choose>


	
</body>
</html>