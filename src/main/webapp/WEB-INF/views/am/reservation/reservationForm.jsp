<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css"
	rel="stylesheet">
<title>예약 페이지</title>
<style>
.box-container {
	display: flex;
	justify-content: center;
	font-family: 'SUITE', sans-serif;
}
.reservation-form {
	margin: 150px 0 0 0;
	width: 500px;
	height: 500px;
	border: 2px solid #d7e6f4;
	align-items: center;
	padding: 60px 100px 100px 50px;
	font-size:22px;
	line-height: 50px;
}
.box-container-left{
	display: flex;
}
.calendar {
	margin: 150px 0 0 20px;
	width: 500px;
	height: 500px;
	border: 2px solid #d7e6f4;
	align-items: center;
	padding: 100px 100px 100px 130px;
	font-size:30px
}
.label{
	width: 150px;
}
.input-form{
	width: 200px;	
}
.content{
	line-height: 45px;
	word-spacing: 0.5cm;
}
#name{
	height: 35px;
}
#phone{
	height: 35px;
}
.reservation-submit{
  width: 130px;
  height: 40px;
  color: #fff;
  border-radius: 5px;
  padding: 10px 25px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
   box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
   					7px 7px 20px 0px rgba(0,0,0,.1),
  					 4px 4px 5px 0px rgba(0,0,0,.1);
  outline: none;
  background: linear-gradient(0deg, #1b4c68 0%, #1b4c68 100%);
  border: none;
}
.reservation-submit:hover {
	background: linear-gradient(0deg, #2E82B3 0%, #2E82B3 100%);
}

</style>
</head>
<script type="text/javascript">

</script>
<body>
	<%@ include file="/WEB-INF/views/am/default/header.jsp"%>

	<div class="box-container">
		 <div class="reservation-form">
			<h2> 튼튼병원 예약 </h2><br>
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
    <input type="checkbox" value="" id="clinic" checked>
  	<label for="rContent">진료</label>
  	<input type="checkbox" value="" id="check">
  	<label for="rContent">검진</label>
  	<input type="checkbox" value="" id="vaccin">
  	<label for="rContent">접종</label>
  	</div>
  </div>
  <div class="box-container-right">
		<div class="calendar">
			<table>
				<tr>
					<td align="center"><label onclick="prevCalendar()"> ◀
					</label></td>
					<td colspan="5" align="center" id="calendarTitle">yyyy년 m월</td>
					<td align="center"><label onclick="nextCalendar()"> ▶
					</label></td>
				</tr>
				<tr>
					<td align="center"><font color="#F79DC2">일</td>
					<td align="center">월</td>
					<td align="center">화</td>
					<td align="center">수</td>
					<td align="center">목</td>
					<td align="center">금</td>
					<td align="center"><font color="skyblue">토</td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td>1</td><td>2</td><td>3</td><td>4</td>
				</tr>
			</table>
		</div>
		<div class="timeList">
			<table>
			</table>
		</div>
			<button class="reservation-submit" onclick="#">예약하기</button>
	</div>
</div>
</body>
</html>