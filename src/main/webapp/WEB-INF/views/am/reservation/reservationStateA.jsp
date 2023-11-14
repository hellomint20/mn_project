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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script type="text/javascript">
	function Popup(rNum) {
		var popupURI1 = '/am/reservationApplyPopup?rNum=' + rNum;
		var reserv = encodeURI(popupURI1);
		var popup = window.open(reserv, '예약수락',
				'width=600px,height=700px,scrollbars=yes,resizable=no');
	}
	
	function fix(){
		const r_fix = confirm("진료가 끝났다면 확인을 눌러 주세요.");
		if(r_fix == true){
			document.r_fix = 1;
		}
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

			<!-- 승인/취소 테이블 -->
			<div id="ACTable" width="800px">
				<table class="col-100 col">
					<colgroup>
						<col width="23%">
						<col width="15%">
						<col width="20%">
						<col width="13%">
						<col width="13%">
						<col width="23%">
					</colgroup>
					<thead>
						<tr>
							<th>날짜</th>
							<th>시간</th>
							<th>과</th>
							<th>접수내용</th>
							<th>접수상태</th>
							<th>진료여부</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="a" items="${a }">
							<tr>
								<td>${a.year }년${a.month }월${a.day }일</td>
								<td>${a.hour }시${a.min }분</td>
								<td>${a.p_type }</td>
								<td>${a.r_content }</td>
								<td>${a.r_apply }</td>
								<td>
								<form action="reservationStateA" method="post">
									<input type="hidden" name="r_fix" value="1">
									<input type="hidden" name="id" value="${mediId }">
									<input type="hidden" name="r_num" value="${a.r_num }">
									<c:choose>
										<c:when test="${a.r_fix ==1 }">
										<button id="fix"onclick="fix()" disabled="disabled">완료</button>
										</c:when>
										<c:otherwise>
											<button id="fix"onclick="fix()">완료</button>
										</c:otherwise>
									</c:choose>
								</form>
								
								</td>							
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:if test="${a.size()==0 }">
					<div id="size">승인 내역이 없습니다.</div>
				</c:if>
			</div>
			<!-- 승인 취소 테이블div -->

			<div class="acPage">
				<c:choose>
					<%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
					<c:when test="${APaging.page<=1}">
						<span></span>
					</c:when>
					<%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
					<c:otherwise>
						<a
							href="/am/reservationStateA?id=${mediId }&page=${APaging.page-1}">[이전]</a>
					</c:otherwise>
				</c:choose>

				<%--  for(int i=startPage; i<=endPage; i++)      --%>
				<c:forEach begin="${APaging.startPage}" end="${APaging.endPage}"
					var="i" step="1">
					<c:choose>
						<%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
						<c:when test="${i eq APaging.page}">
							<span style="font-weight: bold;" >${i}</span>
						</c:when>

						<c:otherwise>
							<a href="/am/reservationStateA?id=${mediId }&page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${APaging.page>=APaging.maxPage}">
						<span></span>
					</c:when>
					<c:otherwise>
						<a
							href="/am/reservationStateA?id=${mediId }&page=${APaging.page+1}">[다음]</a>
					</c:otherwise>
				</c:choose>
			</div>
			<!-- acPage div -->

		</div>
	</div>


</body>
</html>