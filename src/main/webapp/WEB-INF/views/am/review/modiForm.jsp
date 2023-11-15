<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/am/css/board/fixedForm.css">
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<%@ include file = "../common/customerSidebar.jsp" %>
	
	<div class="board_wrap">
		<div class="board_title">
			<strong>후기 작성</strong>
			<p>병원 방문 후기를 작성해 주세요!</p>
		</div>
		<form action="fixedForm" method="post">
			<div class="board_list_wrap">
				<div class="board_list">
					<div class="top">
						<div class="c_id">작성자 | ${userId}</div>
						<div class="r_date">날짜 | ${list.year}년${list.month}월 ${list.day}일</div>
						<div class="m_name">병원 | ${list.m_name}</div>
					</div>
				</div>
				
				<div>
					<textarea placeholder="후기를 작성해 주세요">${list.cont }</textarea>
				</div>
			</div>
			<div class="btn_a">
			    <button type="button" class="cancel" onclick="location.href='/am/customerList?id=${userId}'">취소</button>
			    <button type="submit" class="completed">완료</button>
	    	</div>
		</form>		
	</div>
</body>
</html>