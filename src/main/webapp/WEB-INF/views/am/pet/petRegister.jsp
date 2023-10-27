<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펫 등록</title>
	<link rel="stylesheet" href="/am/css/pet/register.css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	
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
					<select id="pSection" name="sn">
						<option value="">--선택해주세요--</option>
						<option value="1">개</option>
						<option value="2">고양이</option>
						<option value="3">기타</option>
					</select>
					<br>
					<div id="pTypeCont">
						<select id="pType" name="tn">
							<option>--선택해주세요--</option>
						</select>
						<input type="text" id="writeType" name="writeType" placeholder="예시) 햄스터" style="display: none;">
					</div>
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

<script type="text/javascript">
	var ch1 = document.getElementById("pSection");
	var ch2 = document.getElementById("pType");
	var writeType = document.getElementById("writeType");

	ch1.onchange = function() {
		var chVal = $('#pSection').val();
		
			$.ajax({
				url : 'petType',
				type : 'post',
				data : {
					data : chVal
				},
				dataType : 'json',
				success : function(data) {
					if (chVal !== "3") {
						writeType.style.display = "none";
						ch2.style.display = "block";
						ch2.innerHTML = '';
						
						$.each(data, function(index, item) {
							var option = document.createElement("option");
							option.value = item.tNum;
							option.text = item.pType;
							ch2.appendChild(option); // 두 번째 select에 옵션 추가
						});
				    } else {
				    	ch2.style.display = "none";
						writeType.style.display = "block";
				    }
				},
				error : function(xhr, status, error) {
					console.error(error);
				}
			});
	};
</script>

</html>