<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약정보</title>
<link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">
<link href="/am/css/reservation/reservationState.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
* {
	font-family: 'SUITE', sans-serif !important;
}
</style>

<script type="text/javascript">
		
		function ok_Form(num) {
			let email = document.getElementById("cemail").value;
			console.log(num);
			console.log(email);
			location.href="/am/reserState1?num="+num+"&email="+email+"&mId="+'${mediId}';
			setTimeout(function() {
			    window.close();
			}, 3000);
		}
		
		function nook_Form(num) {
		    var form = document.createElement('form');
		    form.method = 'POST';
		    form.action = '/am/reserState2'; // POST 요청을 처리할 엔드포인트 URL

		    var emailInput = document.createElement('input');
		    emailInput.type = 'hidden';
		    emailInput.name = 'email';
		    emailInput.value = document.getElementById("cemail").value;
		    form.appendChild(emailInput);

		    var numInput = document.createElement('input');
		    numInput.type = 'hidden';
		    numInput.name = 'num';
		    numInput.value = num;
		    form.appendChild(numInput);

		    var mediIdInput = document.createElement('input');
		    mediIdInput.type = 'hidden';
		    mediIdInput.name = 'mId';
		    mediIdInput.value = '${mediId}';
		    form.appendChild(mediIdInput);

		    var contInput = document.createElement('input');
		    contInput.type = 'hidden';
		    contInput.name = 'cont';
		    contInput.value = encodeURIComponent(document.getElementById("reason").value);
		    form.appendChild(contInput);
		    document.body.appendChild(form);
		    form.submit();
		    setTimeout(function() {
			    window.close();
			  }, 3000);
		}
		
		
		$(function (){
			$("#btn_no").click(function (){
		  	$("#Toggle").toggle();
		  });
		});
	</script>
</head>
<body>
	<h1 id="title">상세내용</h1>
	<br>

	<hr>
	<div class="popup">
		<input type="hidden" id="cemail" value="${info.c_email }">
		<table class="popup_table">

			<tr>
				<td>예약날짜</td>
				<td>${info.year }년${info.month }월 ${info.day }일</td>
			</tr>
			<tr>
				<td>예약시간</td>
				<td>${info.hour}시${info.min}분</td>
			</tr>
			<tr>
				<td>보호자(예약자) 성명</td>
				<td>${info.r_name }</td>
			</tr>
			<tr>
				<td>보호자(예약자) 전화번호</td>
				<td>${info.c_tel }</td>
			</tr>
			<tr>
				<td>진료동물</td>
				<td>${info.p_type }(${info.p_name })</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>${info.p_sex }</td>
			</tr>
			<tr>
				<td>나이</td>
				<td>${info.p_age }</td>
			</tr>
			<tr>
				<td>진료내용</td>
				<td>${info.r_content }</td>
			</tr>

		</table>

		<div class="ani_pic">
			<img id="photo" src="/am/resources/img/${info.p_photo}">
		</div>
	</div>

	<div class="rap_button">
		<div class="popup_no_box">
			<button type="button" id="btn_no">취소</button>
		</div>
		<div class="popup_ok_box">
			<button type="button" id="btn_ok" onclick="ok_Form(${num})">확인</button>
		</div>
	</div>


	<div id="Toggle" style="display: none">
		<textarea id="reason" placeholder="취소사유를 작성해주세요"></textarea>

		<button type="button" id="btn_nook" onclick="nook_Form(${num})">취소완료</button>
	</div>

</body>
</html>