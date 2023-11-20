<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/am/css/review/myReview.css">
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<c:if test="${userId != null}">
		<%@ include file = "../common/customerSidebar.jsp" %>
	</c:if>
	
	<div class="board_wrap">
		<div class="board_title">
			<strong>후기</strong>
			<p>${detail.cId }님이 작성하신 ${detail.mName } 병원 후기입니다.</p>
		</div>
		<form action="fixedForm" method="post">
			<div class="board_list_wrap">
				<div class="board_list">
					<div class="top">
						<div class="flex">
							
							<div class="m_name" name="mName">방문 병원
								<div class="view">${detail.mName }</div>
							</div>
							<div class="rv_date" name="rv_date">작성 날짜
								<div class="view">${detail.rvDate }</div>
							</div>
						</div>
						<div class="rv_title" name="rvTitle">제목 
							<div class="view">${detail.rvTitle }</div>
						</div>
						<div class="rv_cont" name="rvCont">작성내용 
							<div class="view">${detail.rvCont }</div>
						</div>
						<div class="r_date" name="rDate" >방문 날짜&nbsp;&nbsp;<span class="view">${detail.rvDate }</span></div>
					</div>
				</div>
			</div>
			<c:if test="${userId eq detail.cId }">
				<div class="btn_a">
				    <button type="button" id="modi" onclick="location.href='/am/modiForm?num=${detail.rvNo}'">수정</button>
				    <button type="button" id="modi" onclick="location.href='/am/delete?id=${userId }&num=${detail.rvNo}'">삭제</button>
		    	</div>
	    	</c:if>
		</form>		
	</div>
</body>
</html>