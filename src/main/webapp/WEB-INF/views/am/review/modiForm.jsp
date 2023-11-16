<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/am/css/review/myReview.css">
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<%@ include file = "../common/customerSidebar.jsp" %>
	
	<div class="board_wrap">
		<div class="board_title">
			<strong>수정</strong>
			<p>후기 수정</p>
		</div>
		<form action="#" method="post">
			<div class="board_list_wrap">
				<div class="board_list">
					<div class="top">
						<div class="c_id">작성자 | ${list.cId}</div>
						<div class="r_date">방문 날짜 | ${list.rDate}</div>
						<div class="m_name">병원 | ${list.mName}</div>
					</div>
				</div>
				
				<div>
					<textarea placeholder="후기를 작성해 주세요">${list.rvCont }</textarea>
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