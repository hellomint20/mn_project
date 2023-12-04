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
	<%@ include file = "../common/customerSidebar.jsp" %>
	
	<div class="all">
		<div class="all_title">
			<strong>정보 수정</strong>
			<p>${dto.cName} 회원님의 정보입니다</p>
		</div>
		<form action="customerModify" method="post">
		<div class="info">
			<div class="info-title">
	    		아이디 : <br>
	    		이름 : <br>
	    		전화번호 :<br>
	    		e-mail : <br>
			</div>
			
			<div class="info-content">
		   		<input type="text" class="t_b" name="cId" value="${userId}" readonly ><br>
		   		<input type="text" class="t_b" name="cName" value="${dto.cName}" required><br>
		   		<input type="text" class="t_b" name="cTel"  oninput="hypenAdd(this)" maxlength="13" value="${dto.cTel}" required><br>
		   		<input type="text" class="t_b" name="cEmail" value="${dto.cEmail}" required><br>
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