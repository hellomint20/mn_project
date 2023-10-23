<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="/am/css/medi/m_modi.css">
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	
	<div class="all">
		<div class="all_title">
			<strong>정보 수정</strong>
			<p>(병원 이름) 정보입니다</p>
		</div>
		
		<div class="info">
			<div class="info-title">
	    		아이디 : <br>
	    		비밀번호 : <br>
	    		병원이름 : <br>
	    		주소 : <br>
	    		전화번호 :<br>
			</div>
			
			<div class="info-content">
		   		<input type="text" id="t_b" name="c_id" readonly placeholder="아이디"><br>
		   		<input type="password" id="t_b" name="c_pw" value="비밀번호"><br>
		   		<input type="text" id="t_b" name="c_name" value="이름"><br>
		   		<input type="text" id="t_b" name="c_addr" value="주소"><br>
		   		<input type="text" id="t_b" name="c_tel" value="전화번호"><br>
		   	</div>
		   	
		   	<div class="info-pic">
				사진
				<input type="file" class="pic-btn">
			</div>
		
		</div>
	    
		<div class="btn_a">
		    <button type="button" class="cancel" onclick="location.href='/am/mediInfo'">취소</button>
		    <button type="button" class="completed" onclick="#">등록</button>
	    </div>
	    
	</div>
	
</body>
</html>