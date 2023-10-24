<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Register</title>
<link href="/am/resources/css/common/register.css" rel="stylesheet">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/am/resources/css/bootstrap/bootstrap.css">
<script type="text/javascript">
</script>
</head>
<body id="registerFormBody">
	<div id="registerForm">
		<a href="/am"><img src="/am/resources/img/common/logoLogin.png"></a>
		<h3 style="text-align: left;">보호자 회원가입</h3><hr>
			<form action="customerRegister" method="post">
				<div id="registerFormContent" class="form-group row" >
					<label for="inputId" class="col-sm-3 col-form-label">아이디</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputId" name="cId"
							placeholder="input Id">
					</div>
				</div>
				<div id="registerFormContent" class="form-group row" >
					<label for="inputPassword" class="col-sm-3 col-form-label">비밀번호</label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id="inputPassword" name="cPw"
							placeholder="input Password">
					</div>
				</div>
				<div id="registerFormContent" class="form-group row">
					<label for="inputName" class="col-sm-3 col-form-label">이름</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputName" name="cName"
							placeholder="input Name">
					</div>
				</div>
				<div id="registerFormContent" class="form-group row">
					<label for="inputTel" class="col-sm-3 col-form-label">연락처</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputTel" name="cTel"
							placeholder="input Tel">
					</div>
				</div>
				<div id="registerFormContent" class="form-group row">
					<label for="inputEmail" class="col-sm-3 col-form-label">E-mail</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputEmail" name="cEmail"
							placeholder="input Email">
					</div>
				</div>
				<br>
				<div class="form-group row">
					<div class="col-sm-12">
						<button type="submit" class="btn" style="background-color: #0B1F54; color: white;">회원가입</button>
						<button type="button" onclick="location.href='/am/customerLogin';" class="btn" style="background-color: #0B1F54; color: white;">로그인</button>
					</div>
				</div>
			</form>
		</div>
</body>
</html>