<%@ page language="java" contentType="text/html; charset=UTF-8"
<<<<<<< HEAD
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
=======
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
>>>>>>> 749d3afb9dc81727c76a8d98fb12d66cfe09af69
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css"
	rel="stylesheet">
<style>
* {
	font-family: 'SUITE', sans-serif !important;
}
</style>
<link href="/am/css/header.css" rel="stylesheet">

</head>
<body>
	<div>
		<div id="imgHeader">
			<img src="/am/resources/img/dog.jpg" width="100%" height="350px">
<<<<<<< HEAD

			<c:if test="${userId == null }">
				<span id="headerText"><a href="/am/customerInfo" id="text">마이페이지</a>
					| <a href="/am/customerLogin" id="text">로그인</a></span>
			</c:if>
			<c:if test="${userId != null }">
				<span id="headerText"><a href="/am/customerInfo?id=${userId}"
					id="text">마이페이지</a> | <a href="/am/logout" id="text">로그아웃</a></span>
			</c:if>
=======
			<c:if test="${userId == null }">
            <span id="headerText"><a href="/am/customerInfo" id="text">마이페이지</a> | 
            <a href="/am/customerLogin" id="text">로그인</a></span>
        	 </c:if>         
        	 <c:if test="${userId != null }">
            <span id="headerText"><a href="/am/customerInfo?id=${userId}" id="text">마이페이지</a> | 
            <a href="/am/logout" id="text">로그아웃</a></span>
         </c:if>   
>>>>>>> 749d3afb9dc81727c76a8d98fb12d66cfe09af69
		</div>
		<div id="headerLogo">
			<a href="/am"><img src="/am/resources/img/logo2.png"
				width="250px" height="100px"></a>
		</div>
	</div>

</body>
</html>