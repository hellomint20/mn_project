<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.petList-title{
	margin : 50px 0 0 500px;
	font-size: 25px;
	
	}

</style>
</head>

<body>
	<%@ include file = "../default/header.jsp" %>
	<%@ include file = "../common/sidebar.jsp" %>

<%-- 등록된 펫 없을경우 --%>
<div class ="petList-title">
	펫 관리
</div>

<div class ="add-pet"></div>
</body>
</html>