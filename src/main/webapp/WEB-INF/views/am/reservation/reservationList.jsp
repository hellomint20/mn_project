<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
					<div class="r_date">날짜</div>
					<div class="r_time">시간</div>
					<div class="p_name">환자</div>
					<div class="m_name">병원이름이 길수도 있잖아요</div>
					<div class="r_content">접수 내용</div>
					<div class="r_apply">예약 현황</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>