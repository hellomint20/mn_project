<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">
<link href="/am/css/reservation/reservationPage.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

</head>
<body>

	<%@ include file="/WEB-INF/views/am/default/header_reservationPage.jsp"%>

	<script>
		$(document).ready(function() {
			$("a.reservationList").click(function() {
				$("#detailDiv").show();
			});
		});
	</script>


	<div id="searchDiv">
		<div id="reservationSearchWindow">
			<input class="reservationSearch" type="text"
				placeholder="예약할 병원 이름 검색"> <a href="#"><img
				src="/am/resources/img/searchIcon.png" width="25px" height="25px"></a>
		</div>
	</div>
	<div id="tableDiv">
		<div id="centerDiv">
			<div id="contentDiv">
				<div id="listDiv">
					<table class="listTable">
						<tr class="listTr">
							<td class="reservationList"><a class="reservationList" href="#">이리온 동물병원</a></td>
						</tr>
						<tr>
							<td class="reservationList"><a class="reservationList" href="#">메디컬 최 동물병원</a></td>
						</tr>
						<tr>
							<td class="reservationList"><a class="reservationList" href="#">24시 메디컬센터</a></td>
						</tr>
						<tr>
							<td class="reservationList"><a class="reservationList" href="#">이리온 동물병원</a></td>
						</tr>
					</table>
				</div>
				
				<div id="detailDiv">
					<table class="reservationDetail">
					<tr></tr>
					<tr><td class="detailTd">튼튼병원 상세 정보</td></tr>
					<tr><td class="detailTd"><img src="/am/resources/img/medi.jpeg"></td></tr>
					<tr><td class="detailTd">이름 : 튼튼병원</td></tr>
					<tr><td class="detailTd">주소 : 서울시</td></tr>
					<tr><td class="detailTd">전화번호 : 010 - 0000 - 0000</td></tr>
					<tr><td class="detailTd"><button type="button">예약</button></td></tr>
					<tr></tr>
				</table>
				</div>
			</div>
		</div>
	</div>
	<br><br><br>



</body>
</html>