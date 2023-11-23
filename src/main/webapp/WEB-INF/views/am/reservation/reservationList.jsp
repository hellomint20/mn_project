<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보호자 예약확인</title>
<link rel="stylesheet" href="/am/css/reservation/c_reservation.css">
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>

<script type="text/javascript">

	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="reservationList?id=${userId}&nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
	
	function get_Date(num){
		let date = document.getElementById(num).innerHTML;
		
		var nowTime = new Date();
		var year = nowTime.getFullYear(); //현재 년도
		var month = nowTime.getMonth()+1; //현재 월	
		var day = nowTime.getDate(); //현재 일
		<!-- ///////////////////////////////////// -->
		var checkYear = date.slice(0, 4);
		var checkMonth = date.slice(6, 8);
		var checkDay = date.slice(10, 12);
		if(checkYear-year == 0 && checkMonth-month == 0 && checkDay-day == 1){ //전 날일 때
			return 2500;
		}else {
			return 5000;
		}
	}

	function reserCancel(num) {
		let payment = get_Date(num);
		
		let msg = confirm("예약 접수일이 내일입니다. \n취소 시 "+payment+"원이 환불됩니다. \n정말로 취소하시겠습니까?");
		if (msg == true){	
			let cId = document.getElementById("cId").value;
			
			var map = {};
			map['num'] = num;
			map['payment'] = payment;
			
			$.ajax({
				url : "/am/payResRefund", type: "post",
				data : JSON.stringify(map),
				contentType : "application/json; charset=utf-8",
				success : (result) => {
					if(result == '1'){
						alert("예약 취소 및 예약금이 환불되었습니다.")
						location.href='/am/reservationList?id='+cId;
					}else if(result == '98'){
						alert("환불 중 문제가 생겼어요!")
						location.href='/am/reservationList?id='+cId;
					}
					else{
						alert("문제 발생")
						return false;
					}
				},
				error : () => {
					alert("문제 발생")
				}
			})
		}
	}
</script>
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<%@ include file = "../common/customerSidebar.jsp" %>
	<%@ include file="../common/recentlyView.jsp" %>
	
	<div class="board_wrap">
		<div class="board_title">
			<strong>예약 정보</strong>
			<p>예약 현황이 확정되었는지 꼭 확인해주세요! &nbsp; &nbsp;<span class="mesCancle">※ 예약 당일에는 취소가 불가능합니다.</span></p>
		</div>
		<input type="hidden" id="cId" value="${userId}">
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
					<div class="r_fix">병원 후기</div>
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
									<a onclick="Popup(${list.r_num})">
									<span id="${list.r_num}">${list.year}년 ${list.month}월 ${list.day}일</span></a>
								</div>
								<div class="r_time">${list.hour	}시${list.min}분</div>
								<div class="p_name">${list.p_name}</div>
								<div class="m_name">${list.m_name}</div>
								<div class="r_content">${list.r_content}</div>
								<div class="r_apply">${list.r_apply}</div>
								<div class="r_cancel">
								
								<c:set var="now" value="<%=new java.util.Date()%>" />
								<fmt:formatDate var="nYear" value="${now}" pattern="yyyy" />
								<fmt:formatDate var="nMonth" value="${now}" pattern="MM" />
								<fmt:formatDate var="nDay" value="${now}" pattern="dd" />
															
									<c:choose>
										<c:when test="${list.r_apply eq '대기'}">
											<c:choose>
												<c:when test="${list.year == nYear and list.month == nMonth and list.day == nDay}">
													<span class="nowCancle">취소 불가능</span>
												</c:when>
												<c:otherwise>
													<button onclick="reserCancel(${list.r_num})">취소</button>
												</c:otherwise>
											</c:choose>
										</c:when>
									</c:choose>
								</div>
								<div class="r_fix">
									<c:choose>
										<c:when test="${list.r_fix == 1}">
											<button onclick="location.href='/am/fixedForm?id=${userId }&num=${list.r_num}'">작성</button>
										</c:when>
										<c:otherwise>
											<button disabled="disabled">작성</button>
										</c:otherwise>
									</c:choose>
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