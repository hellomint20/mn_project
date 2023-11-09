<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<<<<<<< HEAD
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
=======
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
>>>>>>> b7e1dff30e18b6542767fbe51b68785c0b5d9830
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="/am/resources/css/common/login.css" rel="stylesheet">
<link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">
<!-- Bootstrap core CSS -->
<link href="/am/resources/css/common/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">

<style>
* {
	font-family: 'SUITE', sans-serif !important;
}
</style>
<<<<<<< HEAD
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<form  class="form-signin" action="cusloginChk" method="post">
		<a href="/am"><img class="mb-2" src="/am/resources/img/common/logoLogin.png" width="270px" height="110px"></a>
=======
</head>
<body>
	<form class="form-signin" action="customerLogin" method="post">
		<a href="/am"><img class="mb-2"
			src="/am/resources/img/common/logoLogin.png" width="270px"
			height="110px"></a>
>>>>>>> b7e1dff30e18b6542767fbe51b68785c0b5d9830
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
<<<<<<< HEAD
			<br><br>
			<!-- 네이버 로그인 화면으로 이동 시키는 URL -->
		<!-- 네이버 로그인 화면에서 ID, PW를 올바르게 입력하면 callback 메소드 실행 요청 -->
		<div id="naver_id_login" style="text-align:center"><a href="${url}">
		<img width="50px" src="/am/resources/img/common/btnG_아이콘원형.png"/></a></div>
		<!-- 구글 로그인 화면으로 이동 시키는 URL -->
		<!-- 구글 로그인 화면에서 ID, PW를 올바르게 입력하면 oauth2callback 메소드 실행 요청-->
		<div id="google_id_login" style="text-align:center"><a href="${google_url}">
		<img width="230" src="/am/resources/img/common/btn_google_signin_dark_normal_web@2x.png"/></a></div>
		<a href="https://accounts.google.com/o/oauth2/auth/oauthchooseaccount?client_id=481904311940-48g6tbsltu3g2op7upb6rtqp3qvs338g.apps.googleusercontent.com&response_type=code&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fplus.login%20https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&redirect_uri=http%3A%2F%2Flocalhost%3A8090%2Fam%2Fgooglecallback&service=lso&o2v=1&theme=glif&flowName=GeneralOAuthFlow">구글</a>
		
	</div>
=======
			<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=552b94427c4a76a3adae3c4f8183915b&redirect_uri=http://localhost:8090/am/kakaoCallback">카카오</a>
		</div>
>>>>>>> b7e1dff30e18b6542767fbe51b68785c0b5d9830
	</form>
	  			

</body>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
	
</html>