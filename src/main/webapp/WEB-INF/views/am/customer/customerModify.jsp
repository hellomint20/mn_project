<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/am/css/customer/modi.css">
<script>
  const hypenAdd = (target) => {
	 target.value = target.value
    .replace(/[^0-9]/g, '')
   .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}
</script>
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<%@ include file = "../common/sidebar.jsp" %>
	
	<div class="all">
		<div class="all_title">
			<strong>정보 수정</strong>
			<p>${dto.cName} 회원님의 정보입니다</p>
		</div>
		<form action="customerModify" method="post">
		<div class="info">
			<div class="info-title">
	    		아이디 : <br>
	    		비밀번호 : <br>
	    		새 비밀번호 :<br>
	    		이름 : <br>
	    		전화번호 :<br>
	    		e-mail : <br>
			</div>
			
			<div class="info-content">
		   		<input type="text" id="t_b" name="cId" value="${userId}" readonly ><br>
		   		<input type="hidden" id="t_b" name="cPw" value="${dto.cPw}" >
		   		<input type="password" id="t_b" value="비밀번호수정" ><br>
		   		<input type="password" id="t_b" name="newPw"><br>
		   		<input type="text" id="t_b" name="cName" value="${dto.cName}"><br>
		   		<input type="text" id="t_b" name="cTel"  oninput="hypenAdd(this)" maxlength="13" value="${dto.cTel}"><br>
		   		<input type="text" id="t_b" name="cEmail" value="${dto.cEmail}"><br>
		   	</div>
		</div>
	    
	    <div class="btn_a">
		    <button type="button" class="cancel" onclick="location.href='/am/customerInfo?id=${userId}'">취소</button>
		    <button type="submit" class="completed">완료</button>
	    </div>
	    </form>
	</div>
	
</body>
</html>