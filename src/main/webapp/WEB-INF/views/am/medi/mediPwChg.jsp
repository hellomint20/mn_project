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
<script type="text/javascript">
	history.replaceState({}, null, '/am/mediPwChg');
</script>
</head>
<body id="searchIdFormBody">
	<div id="searchIdForm">
		<a href="/am"><img src="/am/resources/img/common/logoLogin.png"></a>
			<h4 style="text-align: left; margin: 20px 0px;">비밀번호 재설정</h4>
			<form action="mediPwChg" method="post">
				<div id="searchIdPwFormContent" class="form-group row" >
					<label for="newPw" class="col-sm-3 col-form-label">새 비밀번호</label>
					<div class="col-sm-9">
					<input type="hidden" name="id" value="${id}">
						<input type="text" class="form-control" id="newPw"
							name="newPw" placeholder="input Pw" required>
					</div>
				</div>
				<div id="searchIdPwFormContent" class="form-group row">
					<div class="col-sm-12">
						<button type="submit" class="btn" style="background-color: #0B1F54; margin-top:20px; color: white;">확인</button>
					</div>
				</div>
			</form>
		</div>
</body>
</html>