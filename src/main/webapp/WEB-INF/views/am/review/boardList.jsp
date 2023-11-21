<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
	<link rel="stylesheet" href="/am/css/review/reviewList.css">
	<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">
	
<script type="text/javascript">
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="reservationList?id=${userId}&nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
</script>
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<c:if test="${userId != null}">
		<%@ include file = "../common/customerSidebar.jsp" %>
	</c:if>

	<div class="board_wrap">
		<div class="board_title">
			<strong>전체 게시판</strong>
			<p>방문한 병원의 후기를 작성해주세요</p>
		</div>
		<div class="board_list_wrap">
			<div class="board_list">
				<div class="top">
					<div class="rv_no">글 번호</div>
					<div class="m_name">병원</div>
					<div class="rv_title">제목</div>
					<div class="rv_date">작성 날짜</div>
					<div class="c_id">작성자</div>
				</div>
				<!-- for문으로 리스트 뽑아오기 -->
				<c:choose>
					<c:when test="${list.size() == 0}">
						<div>
							<div class="listIsNull">후기가 없습니다. 후기를 작성해 주세요</div>
						</div>
					</c:when>
					<c:when test="${list.size()!=0 }">
						<c:forEach items="${list }" var="list">
							<div class="listbox">
								<div class="rv_no">${list.rvNo }</div>
								<div class="m_name">${list.mName}</div>
								<div class="rv_title"><a href="/am/myReview?num=${list.rvNo }">${list.rvTitle }</a></div>
								<div class="rv_date">
									${list.rvDate }
								</div>
								<div class="c_id">${list.cId}</div>
							</div>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>
		</div>
		
	</div>
</body>
</html>