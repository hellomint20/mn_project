<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보호자 예약확인</title>
<link rel="stylesheet" href="/am/css/reservation/c_reservation.css">
<script type="text/javascript">
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="reservationList?id=${userId}&nowPage=${paging.nowPage}&cntPerPage="+sel;
	}

	function reserCancel(num) {
		let msg = confirm("정말로 취소하시겠습니까?");
		console.log(num)
		if (msg == true)	
			//location.href='/am/reservationCancel?id=${userId}&num='+num;
			location.href='/am/payResRefund?rNum='+num;
	}
</script>
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<%@ include file = "../common/customerSidebar.jsp" %>

	<div class="board_wrap">
		<div class="board_title">
			<strong>예약 정보</strong>
			<p>예약 현황이 확정되었는지 꼭 확인해주세요!</p>
		</div>
		<div class="board_list_wrap">
			<div class="board_list">
				<div class="top">
					<div class="r_date">날짜</div>
					<div class="r_time">시간</div>
					<div class="p_name">환자</div>
					<div class="m_name">병원</div>
					<div class="r_content">접수 내용</div>
					<div class="r_apply">예약 현황</div>
					<div class="r_cancel">예약 취소</div>
				</div>
				<!-- for문으로 리스트 뽑아오기 -->
				<c:choose>
					<c:when test="${list.size() == 0}">
						<div>
							<div class="listIsNull">예약내역이 없습니다.</div>
						</div>
					</c:when>
					<c:when test="${list.size()!=0 }">

						<c:forEach items="${viewAll }" var="list">
							<div class="listbox">
								<div class="r_date">
									<a onclick="Popup(${list.r_num})">${list.year}년
										${list.month}월 ${list.day}일</a>
								</div>
								<div class="r_time">${list.hour	}시${list.min}분</div>
								<div class="p_name">${list.p_name}</div>
								<div class="m_name">${list.m_name}</div>
								<div class="r_content">${list.r_content}</div>
								<div class="r_apply">${list.r_apply}</div>
								<div class="r_cancel">
									<c:if test="${list.r_apply eq '대기'}">
										<button onclick="reserCancel(${list.r_num})">취소</button>
									</c:if>
								</div>
							</div>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>
		</div>
		<div style="display: block; text-align: center; margin-top: 20px;">
			<c:if test="${paging.startPage != 1 }">
				<a class="pageLink"
					href="/am/reservationList?id=${userId}&nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
			</c:if>
			<c:forEach begin="${paging.startPage }" end="${paging.endPage }"
				var="p">
				<c:choose>
					<c:when test="${p == paging.nowPage }">
						<b>${p }</b>
					</c:when>
					<c:when test="${p != paging.nowPage }">
						<a class="pageLink"
							href="/am/reservationList?id=${userId}&nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${paging.endPage != paging.lastPage}">
				<a class="pageLink"
					href="/am/reservationList?id=${userId}&nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
			</c:if>
		</div>
	</div>
</body>
</html>