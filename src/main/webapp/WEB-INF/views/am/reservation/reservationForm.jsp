<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>예약 페이지</title>
<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">
<link href="/am/css/reservation/reservationForm.css" rel="stylesheet">
<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">
</head>
<script type="text/javascript">
function reservationChk(){
	alert('예약이 접수되었습니다');
}
function reservationPopup(){
	var popupURI1='/am/reservationPopup';
	var reserv = encodeURI(popupURI1);
    var popup = window.open(reserv, '예약확인', 'width=510px,height=600px,scrollbars=yes,resizable=no');
}

</script>

<body>
	<%@ include file="../default/header_reservationPage.jsp"%>

	<div class="box-container">
	
		 <div class="reservation-form">
		 
		 <div class="box-hospital-name">
			<strong>병원 예약</strong>
			<p>튼튼병원 예약 페이지입니다</p>
		</div>
		
			<div class="box-container-left">
				<div class="label">
					<label for="name">예약자 성함</label><br>
					<label for="phone">전화번호</label><br>
					<label for="pName">동물이름</label><br>
				</div>
   				<div class="input-form">
				    <input type="text" id="name" name="cId" placeholder="홍길동" required><br>
				    <input type="tel" id="phone" name="cPhone" placeholder="010-1234-5678" pattern=(\d{3})-?\s?(\d{4})-?\s?(\d{4}) required><br>
				    <select id="pName" name="pName" required>
			        <option value="">선택하세요</option>
			        <option value="connecting">앵두</option>
			        <option value="adjoining">체리</option>
			        <option value="adjacent">도니</option>
			    	</select><br>
			    </div>
			    </div>
	<div class="content">
	    <label for="rContent">접수내용</label><br>
	    <input type="radio" name="rContent" id="clinic">진료
	  	<input type="radio" name="rContent" id="check">검진
	  	<input type="radio" name="rContent" id="vaccin">접종
  	</div>
  </div>
  <div class="box-container-right">
		<div class="calendar">
			<table id="calendar_tb">
				<tr>
					<td align="center"><label onclick="prevCalendar()"> ◀
					</label></td>
					<td colspan="5" align="center" id="calendarTitle">2023년 11월</td>
					<td align="center"><label onclick="nextCalendar()"> ▶
					</label></td>
				</tr>
				<tr>
					<td align="center"><font color="red">일</td>
					<td align="center">월</td>
					<td align="center">화</td>
					<td align="center">수</td>
					<td align="center">목</td>
					<td align="center">금</td>
					<td align="center"><font color="blue">토</td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td>1</td><td>2</td><td>3</td><td>4</td>
				</tr>
				<tr>
					<td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td>
				</tr>
			</table>
			</div>
			
			<div class="timeList" >
				<div class="btn-group-vertical btn-group-lg" role="group" aria-label="Vertical radio toggle button group">
				  <input type="radio" class="btn-check" name="vbtn-radio" id="vbtn-radio1" autocomplete="off" checked>
				  <label class="btn btn-outline-dark" for="vbtn-radio1">9:00</label>
				  <input type="radio" class="btn-check" name="vbtn-radio" id="vbtn-radio2" autocomplete="off">
				  <label class="btn btn-outline-dark" for="vbtn-radio2">10:00</label>
				  <input type="radio" class="btn-check" name="vbtn-radio" id="vbtn-radio3" autocomplete="off">
				  <label class="btn btn-outline-dark" for="vbtn-radio3">11:00</label>
				</div>
				<div class="btn-group-vertical btn-group-lg" role="group" aria-label="Vertical radio toggle button group">
					 <input type="radio" class="btn-check" name="vbtn-radio" id="vbtn-radio4" autocomplete="off">
				 	 <label class="btn btn-outline-dark" for="vbtn-radio4">13:00</label>
				 	 <input type="radio" class="btn-check" name="vbtn-radio" id="vbtn-radio5" autocomplete="off">
					 <label class="btn btn-outline-dark" for="vbtn-radio5">14:00</label>
					 <input type="radio" class="btn-check" name="vbtn-radio" id="vbtn-radio6" autocomplete="off">
					 <label class="btn btn-outline-dark" for="vbtn-radio6">15:00</label>
				</div>
				<div class="btn-group-vertical btn-group-lg" role="group" aria-label="Vertical radio toggle button group">
				   <input type="radio" class="btn-check" name="vbtn-radio" id="vbtn-radio7" autocomplete="off">
				   <label class="btn btn-outline-dark" for="vbtn-radio7">16:00</label>
				   <input type="radio" class="btn-check" name="vbtn-radio" id="vbtn-radio8" autocomplete="off">
				   <label class="btn btn-outline-dark" for="vbtn-radio8">17:00</label>
				   <input type="radio" class="btn-check" name="vbtn-radio" id="vbtn-radio9" autocomplete="off">
				   <label class="btn btn-outline-dark" for="vbtn-radio9">18:00</label>
				</div>
  	  	</div>
  	  	
	</div>
</div>
	<div class="box-container-bottom">
			<button class="reservation-submit" onclick="reservationChk();reservationPopup()">예약하기</button>
	</div>
	
	
</body>
</html>