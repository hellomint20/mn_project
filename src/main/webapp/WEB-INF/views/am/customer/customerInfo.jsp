<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AniMedi</title>

	<link rel="stylesheet" href="/am/css/customer/info.css">
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<%@ include file = "../common/customerSidebar.jsp" %>
	<%@ include file="../common/recentlyView.jsp" %>

	<div class="all">
		<div class="all_title">
			<strong>회원 정보</strong>
			<p>${dto.cName}님의 회원 정보입니다</p>
		</div>
		
		<c:choose>
			<c:when test="${dto.cTel != 'null'}">
				<div class="info">
					<div class="info-title">
					
		    			아이디 : <br>
		    			이름 : <br>
		    			전화번호 :<br>
		    			e-mail : <br> 
					</div>
			
	  				<div class="info-content">
	  					${dto.cId}<br>
	  					${dto.cName}<br>
	  					${dto.cTel}<br>
	  					${dto.cEmail}<br>
	  				</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="info">
					<div class="info-title">
		    			아이디 : <br>
		    			이름 : <br>
		    			e-mail : <br>
					</div>
			
	  				<div class="info-content">
	  					${dto.cId}<br>
	  					${dto.cName}<br>
	  					${dto.cEmail}<br>
	  				</div>
				</div>
			</c:otherwise>
		</c:choose>
		
		<c:if test="${sns eq null }">
		    <button type="button" class="b" onclick="location.href='/am/customerPwdChk?id=${userId}'">수정</button>
		    <button type="button" class="b2" onclick="location.href='/am/customerPwdChg?id=${userId}'">비밀번호 변경</button>
	    </c:if>
	</div>
</body>
</html>