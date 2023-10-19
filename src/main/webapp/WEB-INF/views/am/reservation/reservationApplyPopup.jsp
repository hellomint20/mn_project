<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약정보</title>
<link
	href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css"
	rel="stylesheet">
<link href="/am/css/reservation/reservationState.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
* {
	font-family: 'SUITE', sans-serif !important;
}
</style>
</head>
<script type="text/javascript">
	function ok_Form() {
		window.close();
	}

	function nook_Form() {
		window.close();
	}
	
	$(function (){
		$("#btn_no").click(function (){
	  	$("#Toggle").toggle();
	  });
	});
</script>
<body>


	<h1 id="title">상세내용</h1>
	<br>

	<hr>
	<div class="popup">
		<table class="popup_table">
			<tr>
				<td>예약일시</td>
				<td>2023-10-26 10시</td>
			</tr>
			<tr>
				<td>보호자</td>
				<td>최재연</td>
			</tr>
			<tr>
				<td>진료동물</td>
				<td>개 | 시츄(앵두)</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>여자</td>
			</tr>
			<tr>
				<td>나이</td>
				<td>13</td>
			</tr>
			<tr>
				<td>진료내용</td>
				<td>접종</td>
			</tr>

		</table>

		<div class="ani_pic">동물사진</div>
	</div>



	<div class="rap_button">
		<div class="popup_no_box">
			<button type="button" id="btn_no">취소</button>
		</div>
		<div class="popup_ok_box">
			<button type="button" id="btn_ok" onclick="ok_Form()">확인</button>
		</div>
	</div>
	
	
	<div id="Toggle" style="display:none">
		<textarea>취소사유</textarea>
		
		<button type="button" id="btn_nook" onclick="nook_Form()">취소완료</button>
	</div>
	
	
	
</body>
</html>