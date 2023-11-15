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
			<strong>내가 쓴 후기</strong>
			<p>님이 작성하신 후기입니다.</p>
		</div>
		<form action="fixedForm" method="post">
			<div class="board_list_wrap">
				<div class="board_list">
					<div class="top">
						<div class="c_id" name="cId" >작성자    
							<div class="view">작성자작성자</div>
						</div>
						<div class="r_date" name="rDate" >방문 날짜
							<div class="view">방문날짜날짜</div>
						</div>
						<div class="m_name" name="mName">방문 병원
							<div class="view">병원이름병원이름</div>
						</div>
						<div class="rv_title" name="rvTitle">제목 
							<div class="view">제목제목</div>
						</div>
						<div class="rv_cont" name="rvCont">작성내용 
							<div class="view">네용내용</div>
						</div>
					</div>
				</div>
			</div>
			<div class="btn_a">
			    <button type="button" id="modi" onclick="location.href='/am/modiForm?id=${userId}'">수정</button>
	    	</div>
		</form>		
	</div>
</body>
</html>