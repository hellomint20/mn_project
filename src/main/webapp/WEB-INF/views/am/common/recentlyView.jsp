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
		<div class="whole_box">
			<b>최근 본 병원</b>
			<div class="recently_box">
			<c:forEach items="${viewList }" var="viewList">
					<div>
					<b>${viewList.m_name}</b><Br>
					${viewList.m_photo}
				</div>
			</c:forEach>
			</div>
		</div><body>
		<div class="whole_box">
			<b>최근 본 병원</b>
			<div class="recently_box">
			<c:forEach items="${viewList }" var="viewList">
					<div>
					<b>${viewList.m_name}</b><Br>
					${viewList.m_photo}
				</div>
			</c:forEach>
			</div>
		</div>
</body><body>
		<div class="whole_box">
			<b>최근 본 병원</b>
			<div class="recently_box">
			<c:forEach items="${viewList }" var="viewList">
					<div>
					<b>${viewList.m_name}</b><Br>
					${viewList.m_photo}
				</div>
			</c:forEach>
			</div>
		</div>
</body>
</body>
</html>