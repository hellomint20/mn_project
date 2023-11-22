<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Info Search</title>

<link href="/am/resources/css/common/searchIdPw.css" rel="stylesheet">

<!-- Bootstrap CSS -->

<link rel="stylesheet" href="/am/resources/css/bootstrap/bootstrap.css">
<script>
  const hypenAdd = (target) => {
	 target.value = target.value
    .replace(/[^0-9]/g, '')
   .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}
</script>
</head>
<body id="searchIdPwFormBody">
	<div id="searchIdPwForm">
		<a href="/am"><img src="/am/resources/img/common/logoLogin.png"></a>
		<h4 style="text-align: left; margin-bottom: 20px;">병원 아이디 찾기</h4>
			<form action="mediSearchId" method="post">
				<div id="searchIdPwFormContent" class="form-group row">
					<label for="inputName" class="col-sm-3 col-form-label">이름</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputName"
							name="inputName" placeholder="input Name">
					</div>
				</div>
				<div id="searchIdPwFormContent" class="form-group row">
					<label for="inputTel" class="col-sm-3 col-form-label">전화번호</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputTel"
							oninput="hypenAdd(this)" maxlength="13"  name="inputTel" placeholder="input Tel">
					</div>
				</div>
				<div id="searchIdPwFormContent" class="form-group row">
					<div class="col-sm-12">
					<button type="submit" class="btn" style="background-color: #0B1F54; color: white;">아이디 찾기</button>
					</div>
				</div>
			</form>
			<hr>
			<h4 style="text-align: left; margin-bottom: 20px;">병원 비밀번호 찾기</h4>
			<form action="mediSearchPw" method="post">
				<div id="searchIdPwFormContent" class="form-group row" >
					<label for="inputId" class="col-sm-3 col-form-label">아이디</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputId"
							name="inputId" placeholder="input Id">
							
					</div>
				</div>
				<div id="searchIdPwFormContent" class="form-group row">
					<label for="inputName" class="col-sm-3 col-form-label">이름</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputName"
							name="inputName" placeholder="input Name">
					</div>
				</div>
				<div id="searchIdPwFormContent" class="form-group row">
					<label for="inputTel" class="col-sm-3 col-form-label">전화번호</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputTel"
						  oninput="hypenAdd(this)" maxlength="13" name="inputTel" placeholder="input Tel">
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