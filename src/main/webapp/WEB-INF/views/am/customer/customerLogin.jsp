<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="/am/resources/css/common/login.css" rel="stylesheet">
<link rel="canonical"
	href="https://getbootstrap.com/docs/4.0/examples/sign-in/">

<!-- Bootstrap core CSS -->
<link href="/am/resources/css/common/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css"
	rel="stylesheet">
<style>
* {
	font-family: 'SUITE', sans-serif !important;
}
</style>
</head>
<body>
	<form class="form-signin" action="customerLogin" method="post">
		<a href="/am"><img class="mb-2"
			src="/am/resources/img/common/logoLogin.png" width="270px"
			height="110px"></a>
		<h2 class="h3 mb-3 font-weight-normal">보호자 로그인</h2>
		<label for="inputId" class="sr-only">id</label> <input type="text"
			id="inputId" name="id" class="form-control" placeholder="input id"
			required autofocus> <label for="inputPassword"
			class="sr-only">Password</label> <input type="password"
			id="inputPassword" class="form-control" name="pw"
			placeholder="input Password" required>
		<div id="autoLoginChk" style="text-align: left">
			<input type="checkbox" id="inputChk" name="autoLogin"><label
				for="inputChk">자동 로그인</label> <br>
		</div>
		<button class="btn btn-lg btn-block"
			style="background-color: #0B1F54; color: white;" type="submit">
			Sign in</button>
		<br>
		<div class="checkbox mb-3">
			<a class="loginAtag" href="customerRegister">회원가입</a> &nbsp; | &nbsp;
			<a class="loginAtag" href="customerSearchIdPw">아이디/비밀번호 찾기</a>
			<hr>
		</div>
		<div>
			간편 로그인
			<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=552b94427c4a76a3adae3c4f8183915b&redirect_uri=http://localhost:8090/am/kakaoCallback">카카오</a>
		</div>
	</form>
</body>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
	
</html>