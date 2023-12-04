<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동물 정보 수정</title>
	<link rel="stylesheet" href="/am/css/pet/modi.css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
	<%@ include file="../default/header_page.jsp"%>
	<%@ include file="../common/customerSidebar.jsp"%>

	<div class="all">
		<div class="all_title">
			<strong>펫 정보 수정</strong>
			<p>정보를 수정해주세요</p>
		</div>

		<form name="frm" action="petModify" method="post" enctype="multipart/form-data">
			<div class="info">
				<div class="info-title">
					이름 : <br> 
					나이 : <br> 
				</div>
				
				<div class="info-content">
					<input type="hidden" name="cId" value="${userId }">
					<input type="hidden" name="pNum" value="${dto.pNum }">
					
					<input type="text" id="t_b" name="pName" value="${dto.pName }" ><br>
					<input type="text" id="t_b" name="pAge"value="${dto.pAge }" ><br>
				</div>
				<div class="info-pic">
				<c:if test="${dto.pPhoto != null }">
					<img id="pPhoto" src="/am/resources/img/${dto.pPhoto }">
				</c:if>
				<c:if test="${dto.pPhoto == null}">
					<img id="pPhoto" src="/am/resources/img/common/petDefault.jpg">
				</c:if>
					<input type="file" name="file" class="pic-btn" onchange="readURL(this)">
				</div>
			</div>
	
			<div class="btn_a">
				<button type="button" class="cancel"
					onclick="location.href='/am/pet/petList?id=${userId}'">취소</button>
				<button type="submit" class="completed" 
					onclick="location.href='/am/pet/petList?id=${userId}'">수정</button>
			</div>
		</form>
	</div>
</body>

<script type="text/javascript">

	function checkForm(){
		if(frm.pName.value == ""){
			alert('이름을 입력해 주세요');
			return false;
		}
		if(frm.pAge.value == ""){
			alert('나이를 입력해 주세요');
			return false;
		}
	}
	
	function readURL(input) {
	    var file = input.files[0] 			//파일에 대한 정보
	    console.log(file)
	    if (file != '') {
	       var reader = new FileReader();
	       reader.readAsDataURL(file); 		//파일의 정보를 토대로 파일을 읽고 
	       reader.onload = function (e) { 	// 파일 로드한 값을 표현한다
	        //e : 이벤트 안에 result값이 파일의 정보를 가지고 있다.
	         $('#pPhoto').attr('src', e.target.result);
	        }
	    }
	}
	

</script>

</html>