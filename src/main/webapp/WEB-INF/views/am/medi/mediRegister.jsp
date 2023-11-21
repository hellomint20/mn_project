<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>

<link href="/am/resources/css/common/register.css" rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css"
	rel="stylesheet">
<style>
* {
	font-family: 'SUITE', sans-serif !important;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/am/resources/js/daumPost.js"></script>
<script>
  const hypenAdd = (target) => {
	 target.value = target.value
    .replace(/[^0-9]/g, '')
   .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}
</script>
 <script type="text/javascript">
    $(document).ready(function() {
    	//ID 중복 확인
    	//id를 입력할 수 있는 input text 영역을 벗어나면 동작한다.
    	$("#id").on("focusout", function() {
    		
    		var id = $("#id").val();
    		
    		if(id == '' || id.length == 0 || !/^[a-zA-Z0-9]+$/.test(id)) {
    			$("#label1").css("color", "red").text("공백 또는 한글은 ID로 사용할 수 없습니다.");
    			return false;
    		}
    		
        	//Ajax로 전송
        	$.ajax({
        		url : '/am/mediIdCheck',
        		data : {
        			id : id
        		},
        		type : 'POST',
        		dataType : 'json',
        		success : function(result) {
        			if (result == true) {
        				$("#label1").css("color", "black").text("사용 가능한 ID 입니다.");
        			} else{
        				$("#label1").css("color", "red").text("사용 불가능한 ID 입니다.");
        				$("#id").val('');
        			}
        		}
        	}); //End Ajax
    	});
    })
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
						oninput="hypenAdd(this)" maxlength="13"   placeholder="input Tel" required>
				</div>
			</div>
			<div id="registerFormContent" class="form-group row">
				<label for="inputAddr" class="col-sm-3 col-form-label">우편번호</label>
				<div class="col-sm-7">
					<input type="text" name="mAddr" readonly class="form-control" id="addr1"
						placeholder="input post" required>
				</div>
				<div class="col-sm-2">
					<input type="button" class="post-btn" onclick="daumPost()" value="우편번호 검색" required>
				</div>
			</div>
			<div id="registerFormContent" class="form-group row">
				<label for="inputAddr2" class="col-sm-3 col-form-label">주소</label>
				<div class="col-sm-7">
					<input type="text" name="mAddr" readonly class="form-control" id="addr2"
						placeholder="input Addr" required>
				</div>
			</div>
			<div id="registerFormContent" class="form-group row">
				<label for="inputAddr2" class="col-sm-3 col-form-label">상세주소</label>
				<div class="col-sm-7">
					<input type="text" name="mAddr" class="form-control" id="addr3"
						placeholder="input extra Addr" required>
				</div>
			</div>
			<div id="registerFormContent" class="form-group row">
				<label for="inputId" class="col-sm-3 col-form-label">아이디</label>
				<div class="col-sm-7">
					<input type="text" name="mId" class="form-control" id="id"
						placeholder="inputId" autofocus>
					<label id="label1" style="text-align: left"></label>
				</div>
			</div>
			<div id="registerFormContent" class="form-group row">
				<label for="inputPassword" class="col-sm-3 col-form-label">비밀번호</label>
				<div class="col-sm-7">
					<input type="password" name="mPw" class="form-control" id="inputPassword"
						placeholder="input Password" required>
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