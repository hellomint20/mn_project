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
		
		<form name="frm" action="petRegister" method="post" enctype="multipart/form-data">
			<div class="info">
				<div class="info-title">
		    		이름 : <br>
		    		나이 : <br>
		    		성별 : <br>
		    		과 : <br>
		    		종 : <br>
				</div>
				
				<div class="info-content">
					<input type="hidden" name="cId" value="${userId }">
			   		<input type="text" id="t_b" name="pName" placeholder="이름"><br>
			   		<input type="text" id="t_b" name="pAge" placeholder="나이"><br>
			   		<div class="rb">
				   		<input type="radio" id="m" name="pSex" value="남">남
				   		<input type="radio" id="f" name="pSex" value="여">여<br>
			   		</div>
					<select id="pSection" name="pSec" required>
						<option value="">--선택해주세요--</option>
						<option value="1">개</option>
						<option value="2">고양이</option>
						<option value="3">기타</option>
					</select>
					<br>
					<div id="pTypeCont">
						<select id="pType" name="pType">
							<option value="">--선택해주세요--</option>
						</select>
						<input type="text" id="writeType" name="writeType" placeholder="예시) 햄스터" style="display: none;">
					</div>
					<br>
			   	</div>
				<div class="info-pic">
					<img id="pPhoto" src="/am/resources/img/petDefault.jpg">
					<input type="file" name="file" class="pic-btn" onchange="readURL(this)">
				</div>
			</div>
		    <div class="btn_a">
			    <button type="button" class="cancel" onclick="location.href='/am/pet/petList?id=${userId}'">취소</button>
			    <button type="submit" class="completed" onclick="return checkForm()">등록</button>
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
		
		flag = false;
		for(i=0; i < frm.pSex.length; i++){
			if(frm.pSex[i].checked){				// 이 부분과
				str += frm.pSex[i].value + "\n";	// 이 부분도 매우 중요한 부분
				flag = true;
				break;								// 라디오박스에서는 1개만 선택될 수 있으므로 사용가능
			}
		}		
		if(flag == false){
			alert("성별을 체크해 주세요.");
			return false;
		}
	}


	function readURL(input) {
	    var file = input.files[0] //파일에 대한 정보
	    console.log(file)
	    if (file != '') {
	       var reader = new FileReader();
	       reader.readAsDataURL(file); //파일의 정보를 토대로 파일을 읽고 
	       reader.onload = function (e) { // 파일 로드한 값을 표현한다
	        //e : 이벤트 안에 result값이 파일의 정보를 가지고 있다.
	         $('#pPhoto').attr('src', e.target.result);
	        }
	    }
	}
	
	setTimeout(function(){
	    if (self.name != 'reload') {
	         self.name = 'reload';
	         self.location.reload(true);
	     }
	     else self.name = ''; 
	},1)

	// type 리스트 select option
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
						ch2.style.display = "inline-block";
						ch2.innerHTML = '';
						
						$.each(data, function(index, item) {
							var option = document.createElement("option");
							option.value = item.pType;
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