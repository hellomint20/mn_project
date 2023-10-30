<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/am/resources/css/common/searchId.css" rel="stylesheet">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/am/resources/css/bootstrap/bootstrap.css">
<link
	href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css"
	rel="stylesheet">
<style>
* {
	font-family: 'SUITE', sans-serif !important;
}
</style>
</head>
<body id="searchIdFormBody">
	<div id="searchIdForm">
		<a href="/am"><img src="/am/resources/img/common/logoLogin.png"></a>
		<h6 style="text-align: center; margin-bottom: 20px;">다음 아이디로 가입한 계정이 있습니다.</h6>
			
				<div id="searchIdFormContent" class="form-group row">
					 <p class="searchId"><c:out value="${fn:substring(id, 0, fn:length(id) - 3)}" />***</p>
				</div>
			<div  class="bottombtn">
			<button type="button" onclick="location.href ='customerSearchIdPw'" class="btn" style="background-color: #0B1F54; color: white;">비밀번호 찾기</button>
			<button type="button" onclick="location.href ='customerLogin'" class="btn" style="background-color: #0B1F54; color: white;">로그인</button>
		</div>
		</div>
		
</body>
</html>