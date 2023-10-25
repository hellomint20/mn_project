<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">
	<style>
		* {
		font-family: 'SUITE', sans-serif !important;
		}
	</style>

<link href="/am/css/header.css" rel="stylesheet">

</head>
<body>
	<div class="all-h">
		<div id="imgHeader">	
            <c:if test="${userId == null }">
             <span id="headerText"><a href="/am/customerInfo" id="text">마이페이지</a> | 
            <a href="/am/customerLogin" id="text">로그인</a></span>
        	 </c:if>         
        	 <c:if test="${userId != null }">
            <span id="headerText"><a href="/am/mediInfo?id=${userId}" id="text">마이페이지</a> | 
            <a href="/am/logout" id="text">로그아웃</a></span>
         </c:if>   
		</div>
		<div id="headerLogo">
			<a href="/am"><img src="/am/resources/img/logo2.png" width="250px" height="100px"></a>
		</div>
	
		<br><br><br><br><br>
		<hr>
	</div>

</body>
</html>