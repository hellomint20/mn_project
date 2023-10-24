<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
	function detailView(mediName) {
		//let mediName = document.getElementById("mediList").innerText;
		console.log(mediName);	
		
		$.ajax({
			url : "reservation/mediInfo", type : "post",
			data : mediName,
			contentType : "application/json; charset=utf-8",
			success : (mediInfo) => {
				console.log("통신 성공")
				console.log(mediInfo)
				document.getElementById("mediDetail").innerHTML ="{ "+mediInfo['m_name'] + " } 상세 정보";
				document.getElementById("mediName").innerHTML = "이름 : "+mediInfo['m_name'];
				document.getElementById("mediAddr").innerHTML = "주소 : "+mediInfo['m_addr'];
				document.getElementById("mediTime").innerHTML = "영업시간 : "+mediInfo['open_time']+" - "+mediInfo['close_time'];
				document.getElementById("mediTel").innerHTML = "전화번호 : "+mediInfo['m_tel']
			},
			error : () => {
				console.log("문제 발생")
			}
		})
		
		
		
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
						<c:forEach var="list" items="${list}">
							<c:set var="i" value="${i+1 }" />
							<tr class="listTr">
								<td>${i }</td>
								<td id="mediList"><button class="listBtn" type="button" onclick="detailView('${list['m_name']}')">${list['m_name']}</button></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				
				<div id="detailDiv" style="display: none;">
					<table class="reservationDetail">
					<tr><td class="Xcla"><button id="X" type="button" onclick="Xclose()" >X</button></td></tr>
					<tr><td id="mediDetail" class="detailTd"></td></tr>
					<tr><td id="mediPhoto" class="detailTd"><img src="/am/resources/img/medi.jpeg"></td></tr>
					<tr><td id="mediName" class="detailTd"></td></tr>
					<tr><td id="mediAddr" class="detailTd"></td></tr>
					<tr><td id="mediTime" class="detailTd"></td></tr>
					<tr><td id="mediTel" class="detailTd"></td></tr>
					<tr><td class="detailTd"><button class="re" type="button" onclick="location.href='/am/reservationForm'">예약</button></td></tr>
					<tr></tr>
				</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>