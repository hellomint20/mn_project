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
			<ul>
				<li onclick="kakaoLogin();"><a href="javascript:void(0)">kakao</a></li>
				<li onclick="kakaoLogout();"><a href="javascript:void(0)">logout</a></li>
			</ul>
		</div>
	</form>
</body>

	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script>
		Kakao.init('61eb6f16c6579789a47613966c3ee01b'); //발급받은 키 중 javascript키를 사용해준다.
		console.log("sdk: " +Kakao.isInitialized()); // sdk초기화여부판단 // true 나옴
		//카카오로그인
		function kakaoLogin() {
		    Kakao.Auth.login({
		      success: function (response) {
		        Kakao.API.request({
		          url: '/v2/user/me',
		          success: function (response) {
		        	  console.log("success: "+response)
		          },
		          fail: function (error) {
		            console.log("error: "+error)
		          },
		        })
		      },
		      fail: function (error) {
		        console.log(error)
		      },
		    })
		  }
		//카카오로그아웃  
		function kakaoLogout() {
		    if (Kakao.Auth.getAccessToken()) {
		      Kakao.API.request({
		        url: '/v1/user/logout',
		        success: function (response) {
		        	console.log(response)
		        },
		        fail: function (error) {
		          console.log(error)
		        },
		      })
		      Kakao.Auth.setAccessToken(undefined)
		    }
		  }  
	</script>


</html>