<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

	<div id="searchDiv">
		<div id="reservationSearchWindow">
			<input class="reservationSearch" id="mediSearch" type="text" placeholder="예약할 병원 이름 검색"> 
			<a id="mediSearch" onclick="filter()"> 
				<img src="/am/resources/img/searchIcon.png"	width="25px" height="25px">
			</a>
		</div>
	</div>
	<div id="tableDiv">
		<div id="centerDiv">
			<div id="contentDiv">
				<div id="listDiv">
					<table class="listTable">
						<tr class="trCla">
							<th>번호</th>
							<th>병원명</th>
						</tr>
						<c:forEach items="${viewAll }" var="list">
							<tr class="listTr">
								<td>${list.RN}</td>
								<td id="mediList"><button class="listBtn" type="button"
										onclick="detailView('${list['m_id']}')">${list['m_name']}</button></td>
							</tr>
						</c:forEach>
					</table>
					<br>
					<div style="display: block; text-align: center; margin-top: 20px;">
						<c:if test="${paging.startPage != 1 }">
							<c:when test="${ search == 0 }">
								<a href="/am/reservation?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
							</c:when>
							<c:otherwise>
								<a href="/am/reservationSearch?mName=${mName}&nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
							</c:otherwise>
						</c:if>
						<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
							<c:choose>
								<c:when test="${p == paging.nowPage }">
									<b>${p }</b>
								</c:when>
								<c:when test="${p != paging.nowPage }">
									<c:if test="${search == 0 }">
										<a href="/am/reservation?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
									</c:if>
									<c:if test="${search == 1 }">
										<a href="/am/reservationSearch?mName=${mName}&nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
									</c:if>
								</c:when>
							</c:choose>
						</c:forEach>
						<c:if test="${paging.endPage != paging.lastPage}">
							<c:when test="${search == 0 }">
								<a href="/am/reservation?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
							</c:when>
							<c:otherwise>
								<a href="/am/reservationSearch?mName=${mName}&nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
							</c:otherwise>
						</c:if>
					</div>
				</div>
				<div id="detailDiv" style="display: none;">
					<table class="reservationDetail">
						<tr>
							<td colspan="2" class="Xcla"><button id="X" type="button" onclick="Xclose()">X</button></td>
						</tr>
						<tr>
							<td class="detailTd" colspan="2" id="mediDetail"></td>
						</tr>
						<tr>
							<td class="detailTd" colspan="2" id="mediPhoto">
							<img src="/am/resources/img/common/default.jpg" width="230px;" height="200px;"></td>
						</tr>
						<tr>
							<td class="detailTd">이름</td>
							<td id="mediName"></td>
						</tr>
						<tr>
							<td class="detailTd">주소</td>
							<td id="mediAddr"></td>
						</tr>
						<tr>
							<td class="detailTd">영업시간</td>
							<td id="mediTime"></td>
						</tr>
						<tr>
							<td class="detailTd">전화번호</td>
							<td id="mediTel"></td>
						</tr>
						<tr>
							<td class="detailTd" colspan="2" id="reBtn">
								<form action="reservationForm/page" method="post">
									<input type="hidden" name="mediId" id="mId">
									<button class="re" type="submit">예약</button>
								</form>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">

    function filter(){
    	let mName = document.getElementById("mediSearch").value;
    	
        if(!document.getElementById("mediSearch").value){
        	 window.location.href = "reservation";
        }else{
        	window.location.href = "reservationSearch?mName="+mName;
        }     
      }
	
	function detailView(mediId) {
		$.ajax({
			url : "reservation/mediInfo", type : "post",
			data : mediId,
			contentType : "application/json; charset=utf-8",
			success : (mediInfo) => {
				let name = mediInfo['m_name'];
				
				document.getElementById("mediDetail").innerHTML ="{ "+mediInfo['m_name'] + " } 상세 정보";
				document.getElementById("mediName").innerHTML = mediInfo['m_name'];
				document.getElementById("mediAddr").innerHTML = mediInfo['m_addr'];
				document.getElementById("mediTime").innerHTML = mediInfo['open_time']+" - "+mediInfo['close_time'];
				document.getElementById("mediTel").innerHTML = mediInfo['m_tel'];
				document.getElementById("mId").value =  mediInfo['m_id']
			},
			error : () => {
				alert("문제 발생")
			}
		})
		
		$("#detailDiv").css("display", "block");
	}
	
	function Xclose(){
		$("#detailDiv").css("display", "none");
	}
	
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="boardList?nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
	</script>

</body>
</html>