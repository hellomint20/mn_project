<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/am/css/reservation/reservationState.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
window.onload = function() {

    $("#table2").show();
    $("#table3").hide();
}
$(document).ready(function() {
   
    $("#btn_2").click(function() {

        $("#table1").hide();
        $("#table2").show();
        $("#table3").hide();
        $("#table4").hide();
    })
    $("#btn_3").click(function() {

        $("#table1").hide();
        $("#table2").hide();
        $("#table3").show();
        $("#table4").hide();
    })
   
})

function Popup(rNum){
	var popupURI1='/am/reservationApplyPopup?rNum='+rNum ;
	var reserv = encodeURI(popupURI1);
    var popup = window.open(reserv, '예약수락', 'width=600px,height=700px,scrollbars=yes,resizable=no');
}

 </script>


</head>
<body>

	<%@ include file="../default/header_page.jsp"%>
	<%@ include file="../common/mediSidebar.jsp"%>

	<div class="r_table">
		<div class="buttonbox">
			<button type="button" id="btn_2">새로운접수</button>
			<button type="button" id="btn_3">승인/취소</button>

		</div>
		<div style="clear: both;"></div>
		<div style="width: 800px; height: 400px;">
			<!-- 새로운 접수 테이블 -->
			<div id="table2" width="900px">
				<table class="col-100">
					<colgroup>
						<col width="30%">
						<col width="20%">
						<col width="20%">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th id="basic">날짜</th>
							<th id="basic">시간</th>
							<th id="basic">과</th>
							<th id="basic">접수내용</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="wait" items="${waitList }">

							<tr>
								<td><a href="#" onclick="Popup(${wait.r_num})">
								${wait.year }년 ${wait.month }월
										${wait.day }일</a></td>
								<td>${wait.hour }시${wait.min }분</td>
								<td>${wait.p_type }</td>
								<td>${wait.r_content }</td>
							</tr>
						</c:forEach>


					</tbody>
				</table>
			</div>


			<!-- 승인/취소 테이블 -->
			<!-- 접수상태에 '취소'들어올 시 그 열 글씨 색 gray로 변경 -->
			<div id="table3" width="800px">
				<table class="col-100 col">
					<colgroup>
						<col width="30%">
						<col width="15%">
						<col width="20%">
						<col width="17.5%">
						<col width="17.5%">
					</colgroup>
					<thead>
						<tr>
							<th id="basic">날짜</th>
							<th id="basic">시간</th>
							<th id="basic">과</th>
							<th id="basic">접수내용</th>
							<th id="basic">접수상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${list }">
							<tr>
								<td>${list.year }년${list.month }월 ${list.day }일</td>
								<td>${list.hour }시${list.min }분</td>
								<td>${list.p_type }</td>
								<td>${list.r_content }</td>
								<td>${list.r_apply }</td>
							</tr>
						</c:forEach>


					</tbody>
				</table>

			</div>
		</div>
	</div>


</body>
</html>
