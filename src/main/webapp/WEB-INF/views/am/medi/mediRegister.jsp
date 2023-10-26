<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>

<link href="/am/resources/css/common/register.css" rel="stylesheet">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/am/resources/js/daumPost.js"></script>
<script>
  const hypenAdd = (target) => {
	 target.value = target.value
    .replace(/[^0-9]/g, '')
   .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}
</script>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/am/resources/css/bootstrap/bootstrap.css">

</head>
<body id="registerFormBody">
	<div id="registerForm">
		<a href="/am"><img src="/am/resources/img/common/logoLogin.png"></a>
		<h3 style="text-align: left;">병원 회원가입</h3>
		<hr>
		<form action="mediRegister" method="post">
			<div id="registerFormContent" class="form-group row">
				<label for="inputName" class="col-sm-3 col-form-label">병원명</label>
				<div class="col-sm-7">
					<input  type="text" name="mName" class="form-control" id="inputName"
						placeholder="input Name">
				</div>
			</div>
			<div id="registerFormContent" class="form-group row">
				<label for="inputTel" class="col-sm-3 col-form-label">전화번호</label>
				<div class="col-sm-7">
					<input type="text" name="mTel" class="form-control" id="inputTel"
						oninput="hypenAdd(this)" maxlength="13" placeholder="input Tel">
				</div>
			</div>
			<div id="registerFormContent" class="form-group row">
				<label for="inputAddr" class="col-sm-3 col-form-label">우편번호</label>
				<div class="col-sm-7">
					<input type="text" name="mAddr" readonly class="form-control" id="addr1"
						placeholder="input post">
				</div>
				<div class="col-sm-2">
					<input type="button" class="btn btn-dark" onclick="daum
					Post()" value="주소검색">
				</div>
			</div>
			<div id="registerFormContent" class="form-group row">
				<label for="inputAddr2" class="col-sm-3 col-form-label">주소</label>
				<div class="col-sm-7">
					<input type="text" name="mAddr" readonly class="form-control" id="addr2"
						placeholder="input Addr">
				</div>
			</div>
			<div id="registerFormContent" class="form-group row">
				<label for="inputAddr2" class="col-sm-3 col-form-label">상세주소</label>
				<div class="col-sm-7">
					<input type="text" name="mAddr" class="form-control" id="addr3"
						placeholder="input extra Addr">
				</div>
			</div>
			<div id="registerFormContent" class="form-group row">
				<label for="inputId" class="col-sm-3 col-form-label">아이디</label>
				<div class="col-sm-7">
					<input type="text" name="mId" class="form-control" id="inputId"
						placeholder="inputId">
				</div>
			</div>
			<div id="registerFormContent" class="form-group row">
				<label for="inputPassword" class="col-sm-3 col-form-label">비밀번호</label>
				<div class="col-sm-7">
					<input type="password" name="mPw" class="form-control" id="inputPassword"
						placeholder="input Password">
				</div>
			</div>
			<br>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="submit" class="btn" style="background-color: #0B1F54; color: white;">회원가입</button>
					<button type="button" onclick="location.href='/am/mediLogin';" class="btn" style="background-color: #0B1F54; color: white;">로그인</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>