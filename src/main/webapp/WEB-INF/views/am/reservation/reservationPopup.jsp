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
<link href="/am/css/reservation/reservationForm.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<style>
* {
	font-family: 'SUITE', sans-serif !important;
}
</style>


</head>

<body>
	<h1 id="title">예약정보</h1>
	<div class="popup-main-box">
		<div class="info-title">
			날짜 : <br> 시간 : <br> 예약자 :<br> 반려동물 이름 : <br> 예약내용
			: <br> 전화번호 :
		</div>
		<form id="reservation_info">
			<div class="info-content">
				<span id="rDate"></span><br> <span id="rTime"></span><br>
				<span id="rName"></span><br> <span id="pName"></span><br>
				<span id="rContent"></span><br> <span id="rTel"></span>
			</div>
		</form>
	</div>
	<div class="popup-bottom-box">
		<button type="button" id="reservationBtn" onclick="closeForm()">닫기</button>
		<button type="button" id="reservationBtn"
			onclick="reservationRegister()">예약 확정</button>
	</div>

	<script type="text/javascript">
	document.getElementById("rDate").innerHTML = opener.$("#calYear").text()+"년 "+opener.$("#calMonth").text()+"월 "+opener.$(".futureDay.choiceDay").text()+"일";
	document.getElementById("rTime").innerHTML = opener.document.querySelector('input[name="vbtn-radio"]:checked').value;
	document.getElementById("rName").innerHTML = opener.document.getElementById("rName").value;
	document.getElementById("pName").innerHTML = opener.$("#pName option:selected").text();
	document.getElementById("rContent").innerHTML = opener.document.querySelector('input[name="rContent"]:checked').value;
	document.getElementById("rTel").innerHTML = opener.document.getElementById("rTel").value;
	
	function closeForm(){
		window.close();
	}
	
	function reservationRegister(){
		
	}
	
	</script>
</body>
</html>