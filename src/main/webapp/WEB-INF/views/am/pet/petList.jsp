<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/am/css/pet/petList.css" rel="stylesheet">
<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">

</head>

<body>
	<%@ include file="../default/header_page.jsp"%>
	<%@ include file="../common/sidebar.jsp"%>


	<div class="petList-title-box">
		<h1>펫 관리</h1>
	</div>
	
	<%-- 등록된 펫 없을경우 --%>
<%-- 
	 <div class ="add-pet-box">
	<a href="petRegister" class="addButton"> + </a>
	<div class ="addText">등록하기</div>
</div>
--%>

	<%-- 등록된 펫 있는경우 --%>
	 <div class="box1">
		<div class="pet-List-box">
			<a href="petModify" class="petNameBtn">러바오</a>
			<div class="pet-info-box">
				<div class="pet-info-title">
					나이: <br> 성별: <br>
				</div>
				<div class="pet-info-content">
					13세<br> 남<br>
				</div>

			</div>
			<div class="pet-list-del">
			<button onclick="del()" class="delBtn">삭제</button>
			</div>
		</div>
		<div class="pet-info-image">
			<img src="/am/resources/img/러바오.jpg" width="250px" height="250px">
		</div>
	</div>
	<div class="box1">
		<div class="pet-List-box">
			<a href="petModify" class="petNameBtn">러바오</a>
			<div class="pet-info-box">
				<div class="pet-info-title">
					나이: <br> 성별: <br>
				</div>
				<div class="pet-info-content">
					13세<br> 남<br>
				</div>

			</div>
			<div class="pet-list-del">
			<button onclick="del()" class="delBtn">삭제</button>
			</div>
		</div>
		<div class="pet-info-image">
			<img src="/am/resources/img/러바오.jpg" width="250px" height="250px">
		</div>
	</div>


</body>
</html>