<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
	    		주소 : <br>
	    		전화번호 :<br>
		</div>
		
  		<div class="info-content">
  			${info.dto.mId }<br>
  			${info.dto.mName }<br>
  			${info.dto.mAddr }<br>
  			${info.dto.mTel }<br>
  		</div>
  		
  		<div class="pic">
			${info.dto.mPhoto }
		</div>
	</div>
	
	
   
    <button type="button" class="b" onclick="location.href='mediModify?id=${info.dto.mId }'">수정</button>
	</div>
	


</body>
</html>