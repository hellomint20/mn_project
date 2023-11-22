<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/am/css/review/modiForm.css">
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<%@ include file = "../common/customerSidebar.jsp" %>
	
	<div class="board_wrap">
		<div class="board_title">
			<strong>수정</strong>
			<p>후기 수정</p>
		</div>
		<form action="modiForm" method="post">
			<div class="board_list_wrap">
				<div class="board_list">
					<div class="top">
						<div class="c_id"><input type="hidden" value="${list.cId}" name="cId">작성자 | ${list.cId}</div>
						<div class="r_date"><input type="hidden" value="${list.rDate}" name="rDate"> 방문 날짜 | ${list.rDate}</div>
						<div class="m_name"><input type="hidden" value="${list.mName}" name="mName">병원 | ${list.mName}</div>
						<input type="hidden" name="rvNo" value="${list.rvNo }">
						<input type="hidden" name="rvDate" value="${list.rvDate }">
					</div>
				</div>
				
				<div class="title">
					<textarea class="rvTitle" name="rvTitle">${list.rvTitle }</textarea>
				</div>
				<div>
					<textarea class="rvCont" name="rvCont">${list.rvCont }</textarea>
				</div>
			</div>
			<div class="btn_a">
			    <button type="button" class="cancel" onclick="location.href='/am/myReview?num=${list.rvNo}'">취소</button>
			    <button type="submit" class="completed">완료</button>
	    	</div>
		</form>		
	</div>
</body>
</html>