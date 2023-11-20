<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/am/css/review/fixedForm.css">
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
						<div class="c_id" >작성자 | ${userId}</div>
						<div class="r_date">방문 날짜 | ${info.r_date}</div>
						<div class="m_name">방문 병원 | ${info.m_name}</div>
						
						<input type="hidden" name="num" value="${info.r_num }">
						<input type="hidden" name="cId" value="${userId}">
						<input type="hidden" name="rDate" value="${info.r_date}">
						<input type="hidden" name="mName" value="${info.m_name }">
					</div>
				</div>
				
				<div class="title">
					<textarea class="rvTitle" style="resize: none;" placeholder="제목를 작성해 주세요" name="rvTitle"></textarea>
				</div>
				<div>
					<textarea class="rvCont" style="resize: none;" placeholder="후기를 작성해 주세요" name="rvCont"></textarea>
				</div>
			</div>
			<div class="btn_a">
			    <button type="button" class="cancel" onclick="location.href='/am/reservationList?id=${userId}'">취소</button>
			    <button type="submit" class="completed" >저장</button>
			    
	    	</div>
		</form>		
	</div>
</body>
</html>