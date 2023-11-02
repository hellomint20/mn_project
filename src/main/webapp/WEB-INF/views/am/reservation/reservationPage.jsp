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
		console.log(mediName);	
		$.ajax({
			url : "reservation/mediInfo", type : "post",
			data : mediName,
			contentType : "application/json; charset=utf-8",
			success : (mediInfo) => {
				console.log("통신 성공")
				console.log(mediInfo)
				let name = mediInfo['m_name'];
				
				document.getElementById("mediDetail").innerHTML ="{ "+mediInfo['m_name'] + " } 상세 정보";
				document.getElementById("mediName").innerHTML = mediInfo['m_name'];
				document.getElementById("mediAddr").innerHTML = mediInfo['m_addr'];
				document.getElementById("mediTime").innerHTML = mediInfo['open_time']+" - "+mediInfo['close_time'];
				document.getElementById("mediTel").innerHTML = mediInfo['m_tel'];
				document.getElementById("mediId").innerHTML = mediInfo['m_id'];
			},
			error : () => {
				console.log("문제 발생")
			}
		})
		
		$("#detailDiv").css("display", "block");
	}
	
	function choiceMedi(){
		console.log(document.getElementById("mediName").innerText)
		let a = document.getElementById("mediName").innerText;
		location.href = "/am/reservationForm/page/"+a
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
						<tr><td colspan="2" class="Xcla"><button id="X" type="button" onclick="Xclose()" >X</button></td></tr>
						<tr><td class="detailTd" colspan="2" id="mediDetail" ></td></tr>
						<tr><td class="detailTd" colspan="2" id="mediPhoto" ><img src="/am/resources/img/common/default.jpg" width="230px;" height="200px;"></td></tr>
						<tr><td class="detailTd">이름</td><td id="mediName"></td></tr>
						<tr><td class="detailTd">주소</td><td id="mediAddr" ></td></tr>
						<tr><td class="detailTd">영업시간</td><td id="mediTime" ></td></tr>
						<tr><td class="detailTd">전화번호</td><td id="mediTel" ></td></tr>
						<tr><td class="detailTd" colspan="2" id="reBtn"><button class="re" type="button" onclick="choiceMedi()">예약</button></td></tr>
				</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>