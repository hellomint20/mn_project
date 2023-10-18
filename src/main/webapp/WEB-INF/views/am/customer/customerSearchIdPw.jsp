<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/am/resources/css/common/searchIdPw.css" rel="stylesheet">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/am/resources/css/bootstrap/bootstrap.css">
</head>
<body id="searchIdPwFormBody">
	<div id="searchIdPwForm">
		<img src="/am/resources/img/common/logoLogin.png">
		<h4 style="text-align: left; margin-bottom: 20px;">보호자 아이디 찾기</h4>
			<form>
				<div id="searchIdPwFormContent" class="form-group row">
					<label for="inputName" class="col-sm-3 col-form-label">이름</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputName"
							placeholder="input Name">
					</div>
				</div>
				<div id="searchIdPwFormContent" class="form-group row">
					<label for="inputTel" class="col-sm-3 col-form-label">연락처</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputTel"
							placeholder="input Tel">
					</div>
				</div>
				<div id="searchIdPwFormContent" class="form-group row">
					<label for="inputEmail" class="col-sm-3 col-form-label">E-mail</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputEmail"
							placeholder="input Email">
					</div>
				</div>
				<div id="searchIdPwFormContent" class="form-group row">
					<div class="col-sm-12">
					<button type="submit" class="btn" style="background-color: #0B1F54; color: white;">아이디 찾기</button>
					</div>
				</div>
			</form>
			<hr>
			<h4 style="text-align: left; margin-bottom: 20px;">보호자 비밀번호 찾기</h4>
			<form>
				<div id="searchIdPwFormContent" class="form-group row" >
					<label for="inputId" class="col-sm-3 col-form-label">아이디</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputId"
							placeholder="input Id">
					</div>
				</div>
				<div id="searchIdPwFormContent" class="form-group row">
					<label for="inputName" class="col-sm-3 col-form-label">이름</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputName"
							placeholder="input Name">
					</div>
				</div>
				<div id="searchIdPwFormContent" class="form-group row">
					<label for="inputTel" class="col-sm-3 col-form-label">연락처</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputTel"
							placeholder="input Tel">
					</div>
				</div>
		
				<div id="searchIdPwFormContent" class="form-group row">
					<div class="col-sm-12">
						<button type="submit" class="btn" style="background-color: #0B1F54; color: white;">비밀번호 찾기</button>
					</div>
				</div>
			</form>
		</div>
</body>
</html>