<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="/am/css/medi/info.css">

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 
 <%@ include file = "../default/header_page.jsp" %>
	
</head>
<body>
	<div class="all">
		<div class="all_title">
			<strong>병원 정보</strong>
			<p>${info.dto.mName } 정보입니다</p>
		</div>
	<div class="info">
		<div class="info-title">
	    		아이디 : <br>
	    		병원이름 : <br>
	    		주소 : <br><br><br>
	    		전화번호 :<br>
	    		영업시간 : <br>
	    		점심시간 : <br>
		</div>

  		<div class="info-content" id="addr">
  			${info.dto.mId }<br>
  			${info.dto.mName }<br>
  			${info.addr1 } <br>
  			${info.addr2 } <br>
  			${info.addr3 } <br>
  			${info.dto.mTel }<br>
  			${info.dto.openTime } - ${info.dto.closeTime }<br>
  			${info.dto.lunchStartTime} - ${info.dto.lunchEndTime } <br>
  		</div>
  		
  		<div class="pic">
  			<c:choose>
  				<c:when test="${info.dto.mPhoto != null }">
  					<img id="photo" src="/am/resources/img/${info.dto.mPhoto}" >
  				</c:when>
  				<c:when test="${info.dto.mPhoto == null }">
  					<img id="photo" src="/am/resources/img/default.jpg" >
  				</c:when>
  			</c:choose>
		</div>
	</div>
	
	
   
    <button type="button" class="b" onclick="location.href='mediModify?id=${info.dto.mId }'">수정</button>
	</div>
	


</body>
</html>