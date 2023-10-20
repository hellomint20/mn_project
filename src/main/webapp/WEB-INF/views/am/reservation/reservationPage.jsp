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


	<script type="text/javascript">
	function detailView() {
		$("#detailDiv").css("display", "block");
	}
	
	function Xclose(){
		console.log("X")
		$("#detailDiv").css("display", "none");
	}
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
						<tr class="trCla">
							<th>번호</th><th>병원명</th>
						</tr>
						<tr class="listTr">
							<td>1</td>
							<td><button class="listBtn" type="button" onclick="detailView()">이리온 동물병원</button></td>
						</tr>
						<tr class="listTr">
							<td>2</td>
							<td><button class="listBtn" type="button" onclick="detailView()">가 동물병원</button></td>
						</tr>
						<tr class="listTr">
							<td>3</td>
							<td><button class="listBtn" type="button" onclick="detailView()">나 동물병원</button></td>
						</tr>
						<tr class="listTr">
							<td>4</td>
							<td><button class="listBtn" type="button" onclick="detailView()">다 동물병원</button></td>
						</tr>
					</table>
				</div>
				
				<div id="detailDiv" style="display: none;">
					<table class="reservationDetail">
					<tr><td class="Xcla"><button id="X" type="button" onclick="Xclose()" >X</button></td></tr>
					<tr><td class="detailTd">튼튼병원 상세 정보</td></tr>
					<tr><td class="detailTd"><img src="/am/resources/img/medi.jpeg"></td></tr>
					<tr><td class="detailTd">이름 : 튼튼병원</td></tr>
					<tr><td class="detailTd">주소 : 서울시</td></tr>
					<tr><td class="detailTd">영업시간 : 09:00 - 18:00</td></tr>
					<tr><td class="detailTd">전화번호 : 010 - 0000 - 0000</td></tr>
					<tr><td class="detailTd"><button class="re" type="button" onclick="location.href='/am/reservationForm'">예약</button></td></tr>
					<tr></tr>
				</table>
				</div>
			</div>
		</div>
	</div>


</body>
</html>