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
window.onload = function() {
    $("#waitTable").show();
    $("#ACTable").hide();
    $(".waitPage").show();
    $(".acPage").hide();
}
$(document).ready(function() {
   
    $("#wait").click(function() {//새로운접수
        $("#waitTable").show(); //새로운접수
        $(".waitPage").show();
        $("#ACTable").hide(); //tm
        $(".acPage").hide();
    })

    $("#ac").click(function() { //승인취소
        $("#waitTable").hide();
        $(".waitPage").hide();
        $("#ACTable").show();
        $(".acPage").show();
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


	<div class="r_table">
		<div class="buttonbox">
			<button type="button" id="wait">새로운접수</button>
			<button type="button" id="ac">승인/취소</button>

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
								<td><a href="#" onclick="Popup(${wait.r_num})">${wait.year }년
										${wait.month }월 ${wait.day }일</a></td>
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
			<div id="ACTable" width="800px">
				<table class="col-100 col">
					<colgroup>
						<col width="30%">
						<col width="20%">
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
							<th>접수상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="ac" items="${ac }">
							<tr>
								<td>${ac.year }년${ac.month }월${ac.day }일</td>
								<td>${ac.hour }시${ac.min }분</td>
								<td>${ac.p_type }</td>
								<td>${ac.r_content }</td>
								<td>${ac.r_apply }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
			
			
			<div class="waitPage">
				<c:choose>
					<%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
					<c:when test="${waitPaging.page<=1}">
						<span>[이전]</span>
					</c:when>
					<%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
					<c:otherwise>
						<a href="/am/reservationState?id=${mediId }&page=${waitPaging.page-1}">[이전]</a>
					</c:otherwise>
				</c:choose>

				<%--  for(int i=startPage; i<=endPage; i++)      --%>
				<c:forEach begin="${waitPaging.startPage}"
					end="${waitPaging.endPage}" var="i" step="1">
					<c:choose>
						<%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
						<c:when test="${i eq waitPaging.page}">
							<span>${i}</span>
						</c:when>

						<c:otherwise>
							<a href="/am/reservationState?id=${mediId }&page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${waitPaging.page>=waitPaging.maxPage}">
						<span>[다음]</span>
					</c:when>
					<c:otherwise>
						<a href="/am/reservationState?id=${mediId }&page=${waitPaging.page+1}">[다음]</a>
					</c:otherwise>
				</c:choose>
			</div><!-- waitPage div -->
			
			<div class="acPage">
				<c:choose>
					<%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
					<c:when test="${ACPaging.page<=1}">
						<span>[이전]</span>
					</c:when>
					<%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
					<c:otherwise>
						<a href="/am/reservationState?id=${mediId }&page=${ACPaging.page-1}">[이전]</a>
					</c:otherwise>
				</c:choose>

				<%--  for(int i=startPage; i<=endPage; i++)      --%>
				<c:forEach begin="${ACPaging.startPage}"
					end="${ACPaging.endPage}" var="i" step="1">
					<c:choose>
						<%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
						<c:when test="${i eq ACPaging.page}">
							<span>${i}</span>
						</c:when>

						<c:otherwise>
							<a href="/am/reservationState?id=${mediId }&page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${ACPaging.page>=ACPaging.maxPage}">
						<span>[다음]</span>
					</c:when>
					<c:otherwise>
						<a href="/am/reservationState?id=${mediId }&page=${ACPaging.page+1}">[다음]</a>
					</c:otherwise>
				</c:choose>
			</div><!-- acPage div -->
			
		</div>
	</div>


</body>
</html>