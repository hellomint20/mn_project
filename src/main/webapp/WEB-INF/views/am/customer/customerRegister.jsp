<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/am/resources/css/common/register.css" rel="stylesheet">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/am/resources/css/bootstrap/bootstrap.css">
</head>
<body id="registerFormBody">
	<div id="registerForm">
		<img src="/am/resources/img/common/logoLogin.png">
		<h3 style="text-align: left;">회원가입</h3><hr>
			<form>
				<div id="registerFormContent" class="form-group row" >
					<label for="inputId" class="col-sm-3 col-form-label">아이디</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputId"
							placeholder="input Id">
					</div>
				</div>
				<div id="registerFormContent" class="form-group row" >
					<label for="inputPassword" class="col-sm-3 col-form-label">비밀번호</label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id="inputPassword"
							placeholder="input Password">
					</div>
				</div>
				<div id="registerFormContent" class="form-group row">
					<label for="inputName" class="col-sm-3 col-form-label">이름</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputName"
							placeholder="input Name">
					</div>
				</div>
				<div id="registerFormContent" class="form-group row">
					<label for="inputTel" class="col-sm-3 col-form-label">연락처</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputTel"
							placeholder="input Tel">
					</div>
				</div>
				<div id="registerFormContent" class="form-group row">
					<label for="inputEmail" class="col-sm-3 col-form-label">E-mail</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputEmail"
							placeholder="input Email">
					</div>
				</div>
				<br>
				<div class="form-group row">
					<div class="col-sm-12">
						<button type="submit" class="btn" style="background-color: #0B1F54; color: white;">회원가입</button>
					</div>
				</div>
			</form>
		</div>
</body>
</html>