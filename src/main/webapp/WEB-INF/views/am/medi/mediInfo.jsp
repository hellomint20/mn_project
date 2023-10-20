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
			<p>(병원 이름) 정보입니다</p>
		</div>
	<div class="info">
		<div class="info-title">
	    		아이디 : <br>
	    		병원이름 : <br>
	    		주소 : <br>
	    		전화번호 :<br>
		</div>
		
  		<div class="info-content">
  			quddnjs<br>
  			행복병원<br>
  			서울시 종로구..<br>
  			010-1111-1111<br>
  		</div>
  		
  		<div class="pic">
			사진들어가기
		</div>
	</div>
	
	
   
    <button type="button" class="b" onclick="location.href='mediModify'">수정</button>
	</div>
	


</body>
</html>