<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/am/css/medi/m_modi.css">
<link rel="stylesheet" href="/am/resources/css/bootstrap/bootstrap.css">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/am/resources/js/daumPost.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

</head>

<script type="text/javascript">
function readURL(input) {
    var file = input.files[0] //파일에 대한 정보
    console.log(file)
    if (file != '') {
       var reader = new FileReader();
       reader.readAsDataURL(file); //파일의 정보를 토대로 파일을 읽고 
       reader.onload = function (e) { // 파일 로드한 값을 표현한다
        //e : 이벤트 안에 result값이 파일의 정보를 가지고 있다.
         $('#photo').attr('src', e.target.result);
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
 
  const hypenAdd = (target) => {
	 target.value = target.value
    .replace(/[^0-9]/g, '')
   .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}
  const colonAdd = (target) =>{
	  target.value = target.value
	  .replace(/[^0-9]/g, '')
	  .replace(/^(\d{2})(\d{2})$/, `$1:$2`);
  }
  
  
  </script>
<body>
	<%@ include file = "../default/header_page.jsp" %>
	<%@ include file = "../common/mediSidebar.jsp" %>
	
	<div class="all">
		<div class="all_title">
			<strong>정보 수정</strong>
			<p>${info.dto.mName } 정보입니다</p>
		</div>
		
		<form action="mediModify" method="post" enctype="multipart/form-data">
			<div class="info">
				<div class="info-title">
					아이디 : <br>
		    		병원이름 : <br>
		    		주소 : <br><br><br>
		    		전화번호 :<br>
		    		영업시간 : <br>
		    		점심시간 : <br>
				</div>
				
				<div class="info-content">
				   		<input type="text" class="t_b" name="mId" readonly value="${info.dto.mId }" ><br>
				   		<input type="text" class="t_b" name="mName" value=" ${info.dto.mName }"><br>
				   		<input type="text" class="t_b_post" name="mAddr" id="addr1" value="${info.addr1 }" >
				   		<input type="button" class="btn btn-dark" onclick="daumPost()" value="주소검색">
				   		<input type="text" class="t_b" name="mAddr" id="addr2" value="${info.addr2 }" ><br>
				   		<input type="text" class="t_b" name="mAddr" id="addr3" value="${info.addr3 }"><br>
				   		<input type="text" class="t_b" name="mTel" oninput="hypenAdd(this)" maxlength="13" value="${info.dto.mTel }" ><br>
				   		<input type="text" class="t_b_time" name="openTime" oninput="colonAdd(this)" maxlength="4" placeholder="예시)0900 입력 " value="${info.dto.openTime }">&nbsp ~ 
				   				&nbsp<input type="text" class="t_b_time" oninput="colonAdd(this)" maxlength="4" name="closeTime" value="${info.dto.closeTime }"><br>
				   		<input type="text" class="t_b_time" name="lunchStartTime" oninput="colonAdd(this)" maxlength="4" placeholder="예시)1300" value="${info.dto.lunchStartTime }">&nbsp ~ 
				   				&nbsp<input type="text" class="t_b_time" oninput="colonAdd(this)" maxlength="4" name="lunchEndTime" value="${info.dto.lunchEndTime }"><br>
			   	</div>
			   	
			   	<div class="info-pic">
				 	<img id="photo" src="/am/resources/img/${info.dto.mPhoto }"> 
					<input type="file" class="pic-btn" name="file"  onchange="readURL(this)">
				</div>
			
			</div>
		    
			<div class="btn_a">
			    <button type="button" class="cancel" onclick="location.href='mediInfo?id=${mediId}'">취소</button>
			    <button type="submit" class="completed" onclick="location.href='mediInfo?id=${mediId }'">등록</button>
		    </div>
	    </form>
	</div>
	    
</body>
</html>