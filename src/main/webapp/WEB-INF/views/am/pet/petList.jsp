<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/am/css/pet/petList.css" rel="stylesheet">
<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">
<script type="text/javascript">
	function delPet(num) {
		let msg = confirm("정말로 삭제하시겠습니까?");
		if (msg == true)	location.href="/am/pet/petDelete?num="+num+"&id=${userId}";
	}
</script>
</head>

<body>
	<%@ include file="../default/header_page.jsp"%>
	<%@ include file="../common/customerSidebar.jsp"%>
	<%@ include file="../common/recentlyView.jsp" %>


	<div class="all">
		<div class="all_title">
			<strong>펫 관리</strong>
			<p>반려동물의 정보를 관리해주세요</p>
		</div>

		<%-- 등록된 펫 있는경우 --%>
		<c:if test="${list.size() != 0 }">
			<c:forEach var="list" items="${list}">
				<div class="box1">
					<div class="pet-List-box">
						<a href="petModify?num=${list.pNum}" class="petNameBtn">${list.pName }</a>
						<div class="pet-info-box">
							<div class="pet-info-title">
								나이: <br> 성별: <br> 종: <br>
							</div>
							<div class="pet-info-content">
								${list.pAge }<br> ${list.pSex }<br>	${list.pType }<br>
							</div>
						</div>
						<div class="pet-list-del">
							<button onclick="delPet(${list.pNum})" class="delBtn">삭제</button>
						</div>
					</div>
					<div class="pet-info-image">
						<c:choose>
							<c:when test="${list.pPhoto != null}">
								<img src="/am/resources/img/${list.pPhoto }" width="250px" height="250px">
							</c:when>
							<c:otherwise>
								<img src="/am/resources/img/petDefault.jpg" width="250px" height="250px">
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:forEach>
		</c:if>
		<%-- 등록된 펫 없을경우 이것만 표시 되도록해주기--%>
		<div class="add-pet-box">
			<a href="petRegister?id=${userId }" class="addButton"> + </a>
			<div class="addText">등록하기</div>
		</div>
	</div>


</body>
</html>