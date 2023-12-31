<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="/am/css/reservation/reservationState.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script type="text/javascript">
	function Popup(rNum){
		console.log(rNum);
		var popupURI1='/am/reservationApplyPopup?rNum='+rNum ;
		var reserv = encodeURI(popupURI1);
	    var popup = window.open(reserv, '예약수락', 'width=600px,height=700px,scrollbars=yes,resizable=no');
	}
</script>




</head>
<body>

	<%@ include file="../default/header_page.jsp"%>

	<div class="r_table">
		<div class="buttonbox">
			<a href="/am/reservationStateWait?id=${mediId }"><button type="button" id="wait">새로운접수</button></a> 
			<a href="/am/reservationStateA?id=${mediId }"><button type="button" id="wait">승인</button></a>
			<a href="/am/reservationStateC?id=${mediId }"><button type="button" id="wait">취소</button></a>

		</div>
		<div style="clear: both;"></div>
		<div style="width: 800px; height: 400px;">
			<!-- 새로운 접수 테이블 -->
			<div id="waitTable" width="900px">
				<table class="col-100 col">
					<colgroup>
						<col width="30%">
						<col width="20%">
						<col width="20%">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th>날짜</th>
							<th>시간</th>
							<th>과</th>
							<th>접수내용</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="wait" items="${wait }">
							<tr>
								<td><a onclick="Popup(${wait.r_num})">${wait.year }년 ${wait.month }월 ${wait.day }일</a></td>
								<td>${wait.hour }시${wait.min }분</td>
								<td>${wait.p_type }</td>
								<td>${wait.r_content }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:if test="${wait.size()==0 }">
					<div id="size">새로운 접수 내역이 없습니다.</div>
				</c:if>
			</div>

			<div class="waitPage">
				<c:choose>
					<%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
					<c:when test="${waitPaging.page<=1}">
						<span></span>
					</c:when>
					<%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
					<c:otherwise>
						<a
							href="/am/reservationStateWait?id=${mediId }&page=${waitPaging.page-1}">[이전]</a>
					</c:otherwise>
				</c:choose>

				<%--  for(int i=startPage; i<=endPage; i++)      --%>
				<c:forEach begin="${waitPaging.startPage}"
					end="${waitPaging.endPage}" var="i" step="1">
					<c:choose>
						<%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
						<c:when test="${i eq waitPaging.page}">
							<span style="font-weight: bold;">${i}</span>
						</c:when>

						<c:otherwise>
							<a href="/am/reservationStateWait?id=${mediId }&page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${waitPaging.page>=waitPaging.maxPage}">
						<span></span>
					</c:when>
					<c:otherwise>
						<a
							href="/am/reservationStateWait?id=${mediId }&page=${waitPaging.page+1}">[다음]</a>
					</c:otherwise>
				</c:choose>
			</div>
			<!-- waitPage div --> 

		</div>
	</div>

</body>
</html>