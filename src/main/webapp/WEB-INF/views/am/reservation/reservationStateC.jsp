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
	
	function show(button, num) {
	    console.log(num);
	    let msg = confirm("취소상태를 승인으로 바꾸고 싶다면 확인을 눌러주세요");

	    if (msg) {
	        let rFixInput = button.parentElement.querySelector("[name='r_fix']");
	        rFixInput.value = 0;
	    } else {
	        let rFixInput = button.parentElement.querySelector("[name='r_fix']");
	        rFixInput.value = num;
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
						<col width="25%">
						<col width="20%">
						<col width="20%">
						<col width="20%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>날짜</th>
							<th>시간</th>
							<th>과</th>
							<th>접수내용</th>
							<th>접수상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="c" items="${c }">
							<tr>
								<td>${c.year }년${c.month }월${c.day }일</td>
								<td>${c.hour }시${c.min }분</td>
								<td>${c.p_type }</td>
								<td>${c.r_content }</td>
								<td>
								<form action="reservationStateC" method="post">
									<input type="hidden" name="r_fix">
									<input type="hidden" name="id" value="${mediId }">
									<input type="hidden" name="r_num" value="${c.r_num }">
									<input type="hidden" name="page" value="${CPaging.page }">
									<c:choose>
										<c:when test="${c.r_apply.equals('취소')}">
											<button id="fix" disabled="disabled">취소</button>
										</c:when>
										<c:otherwise>								
											<button id="fix" onclick="show(this, ${c.r_fix})">취소</button>
										</c:otherwise>
									</c:choose>
								</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:if test="${c.size()==0 }">
					<div id="size">취소 내역이 없습니다.</div>
				</c:if>
			</div>
			<!-- 승인 취소 테이블div -->

			<div class="acPage">
				<c:choose>
					<%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
					<c:when test="${CPaging.page<=1}">
						<span></span>
					</c:when>
					<%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
					<c:otherwise>
						<a
							href="/am/reservationStateC?id=${mediId }&page=${CPaging.page-1}">[이전]</a>
					</c:otherwise>
				</c:choose>

				<%--  for(int i=startPage; i<=endPage; i++)      --%>
				<c:forEach begin="${CPaging.startPage}" end="${CPaging.endPage}"
					var="i" step="1">
					<c:choose>
						<%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
						<c:when test="${i eq CPaging.page}">
							<span style="font-weight: bold;" >${i}</span>
						</c:when>

						<c:otherwise>
							<a href="/am/reservationStateC?id=${mediId }&page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${CPaging.page>=CPaging.maxPage}">
						<span></span>
					</c:when>
					<c:otherwise>
						<a
							href="/am/reservationStateC?id=${mediId }&page=${CPaging.page+1}">[다음]</a>
					</c:otherwise>
				</c:choose>
			</div>
			<!-- acPage div -->

		</div>
	</div>


</body>
</html>