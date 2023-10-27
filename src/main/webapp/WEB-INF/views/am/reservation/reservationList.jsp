<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보호자 예약확인</title>
		<link rel="stylesheet" href="/am/css/reservation/c_reservation.css">
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<%@ include file = "../common/sidebar.jsp" %>

	<div class="board_wrap">
		<div class="board_title">
			<strong>예약 정보</strong>
			<p>확정되었는지 꼭 확인해주세요!</p>
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
				</div>
				<!-- for문으로 리스트 뽑아오기 -->
				<div>
					<div class="r_date">${list["year"]}년 ${list.month}월 ${list.day}일</div>
					<div class="r_time">${list.hour}시 ${list.min}분</div>
					<div class="p_name">${list['p_name']}</div>
					<div class="m_name">${list['m_name']}</div>
					<div class="r_content">${list['r_content']}</div>
					<div class="r_apply">${list['r_apply']}</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>