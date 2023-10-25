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
			<p>${info.dto.mName } 정보입니다</p>
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
		   		<input type="text" id="t_b" name="c_id" readonly placeholder="${info.dto.mId }"><br>
		   		<input type="password" id="t_b" name="c_pw" value="${info.dto.mPw }"><br>
		   		<input type="text" id="t_b" name="c_name" value="${info.dto.mName }"><br>
		   		<input type="text" id="t_b" name="c_addr" value="${info.dto.mAddr }"><br>
		   		<input type="text" id="t_b" name="c_tel" value="${info.dto.mTel }"><br>
		   	</div>
		   	
		   	<div class="info-pic">
				${info.dto.mPhoto }
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