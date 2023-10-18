<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/am/css/pet/register.css">
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<%@ include file = "../common/sidebar.jsp" %>

	<div class="all">
		<h1>펫 정보 수정</h1>
		
		<div class="info">
			<div class="info-title">
	    		이름 : <br>
	    		나이 : <br>
	    		성별 : <br>
	    		과 : <br>
	    		종 : <br>
			</div>
			
			<div class="info-content">
		   		<input type="text" id="t_b" name="pName" placeholder="이름"><br>
		   		<input type="text" id="t_b" name="pAge" placeholder="나이"><br>
		   		<div class="rb">
			   		<input type="radio" id="m" name="pSex">남
			   		<input type="radio" id="f" name="pSex">여<br>
		   		</div>
				<select id="pSection" name="pSection" required>
					<option value="">선택하세요</option>
					<option value="value">개</option>
					<option value="value">고양이</option>
					<option value="value">쥐</option>
				</select><br>
				<select id="pType" name="pType" required>
					<option value="">선택하세요</option>
					<option value="value">시츄</option>
					<option value="value">말티즈</option>
					<option value="value">허스키</option>
				</select><br>
		   	</div>
		   	
	
			<div class="info-pic">
				사진
				<input type="file" class="pic-btn">
			</div>
		
		</div>
	    
	    <div class="btn_a">
		    <button type="button" class="cancel" onclick="#">취소</button>
		    <button type="button" class="completed" onclick="#">수정</button>
	    </div>
	    
	</div>
</body>
</html>