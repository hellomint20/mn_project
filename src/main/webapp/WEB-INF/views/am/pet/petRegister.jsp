<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펫 등록</title>
	<link rel="stylesheet" href="/am/css/pet/register.css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script type="text/javascript">
		var arr = new Array();
		
		function pSec(e){
			console.log(e.value);
			console.log(${list.size()});
			console.log('${list.get(20).pType}');
			
			for(var i=0; i < ${list.size()}; i++){
				if(${list.get(i).tNum} == e.value){
					arr.push('${list.get(i).pType}');
					console.log('${list.get(i).pType}');
				}
			}
			
			var target = document.getElementById("pType");
			
			target.options.length = 0;

			for (x in arr) {
				var opt = document.createElement("option");
				opt.value = arr[x];
				opt.innerHTML = arr[x];
				target.appendChild(opt);
			}	
		}
	</script>
	
</head>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<%@ include file = "../common/sidebar.jsp" %>

	<div class="all">
		<div class="all_title">
			<strong>펫 등록</strong>
			<p>반려동물을 등록해주세요</p>
		</div>
		
		<form action="petRegister" method="post">
			<div class="info">
				<div class="info-title">
		    		이름 : <br>
		    		나이 : <br>
		    		성별 : <br>
		    		과 : <br>
		    		종 : <br>
				</div>
				<input type="hidden" name="cId" value="${userId }">
				<div class="info-content">
			   		<input type="text" id="t_b" name="pName" placeholder="이름"><br>
			   		<input type="text" id="t_b" name="pAge" placeholder="나이"><br>
			   		<div class="rb">
				   		<input type="radio" id="m" name="pSex">남
				   		<input type="radio" id="f" name="pSex">여<br>
			   		</div>
			   		
					<select onchange="pSec(this)">
						<option>선택해주세요</option>
						<option value="1">개</option>
						<option value="2">고양이</option>
						<option value="3">기타</option>
					</select>
					
					<select id="pType">
						<option>선택해주세요</option>
					</select>
					
					<%--
					<select id="pType">
					<option selected>선택하세요</option>
					    <c:forEach var="list" items = "${ptList }">
						<option><c:out value="${list.pType }"/>
					</c:forEach>
					</select>
					--%>
					<br>
			   	</div>
				<div class="info-pic">
					사진
					<input type="file" class="pic-btn">
				</div>
			</div>
		    <div class="btn_a">
			    <button type="button" class="cancel" onclick="location.href='/am/pet/petList?id=${userId}'">취소</button>
			    <button type="submit" class="completed" onclick="location.href='/am/pet/petList?id=${userId}'">등록</button>
		    </div>
		</form>
	
	</div>
</body>
</html>