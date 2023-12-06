<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="/am/css/common/recentlyView.css">
<script type="text/javascript">
$(document).ready(function () {
	
    var tmp = parseInt($("#whole_box").css('top'));

    $(window).scroll(function () {
        var scrollTop = $(window).scrollTop();
        var box_position = scrollTop + tmp + "px";

        $("#whole_box").stop().animate({
            "top": box_position
        }, 500);
    }).scroll();
});
</script>
</head>
<body>
	<c:if test="${sessionScope.recentlyViewList.size()>0}">
		<div class="whole_box">
			<b>최근 본 병원</b> 
			<div class="recently_box">
				<c:forEach items="${sessionScope.recentlyViewList}" var="view">
		                <div>
		                <form action="/am/reservationForm/page" method="post">
		                <input type="hidden" name="mediId" value="${view.m_id}" id="mId">
		                <button type="submit"><img src="/am/resources/img/${view.m_photo}" width="100px" height="100px"><br>
		                <b>${view.m_name}</b>
		                </button>
		                </form>
		                </div>
		            </c:forEach>
			</div>
		</div>
		</c:if>
</body>
</html>