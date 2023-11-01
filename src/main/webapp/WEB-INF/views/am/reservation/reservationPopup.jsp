<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약정보</title>	
<link href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css" rel="stylesheet">
<link href="/am/css/reservation/reservationForm.css" rel="stylesheet">
	<style>
		* {
		font-family: 'SUITE', sans-serif !important;
		}
	</style>
	
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

</head>

<body>
    <h1 id="title">예약정보</h1>
    <div class="popup-main-box">
		<div class="info-title">
    		날짜 : <br>
    		시간 : <br>
    		예약자 :<br>
    		반려동물 이름 : <br>
    		접수내용 : <br>
    		전화번호 :
		</div>
   		<div class="info-content">
   			<span id="rDate"></span><br>
   			<span id="rTime"></span><br>
   			<span id="rName"></span><br>
   			<span id="pName"></span><br>
   			<span id="rContent"></span><br>
   			<span id="rTel"></span>
   		</div>
    </div>
    <div class="popup-bottom-box">
    	<button type="button" id="reservationBtn" onclick="closeForm()">닫기</button>
    	<button type="button" id="reservationBtn" onclick="reservationRegister()">예약 확정</button>
    </div>
    
    <script type="text/javascript">
	var day = null;
	if (opener.$(".futureDay.choiceDay").val() == undefined){ //선택된 날짜가 오늘 이후가 아니라면
		day = opener.$(".today.choiceDay").text();
	}else{
		day = opener.$(".futureDay.choiceDay").text();
	}
	
	document.getElementById("rDate").innerHTML = opener.$("#calYear").text()+"년 "+opener.$("#calMonth").text()+"월 "+day+"일";
	document.getElementById("rTime").innerHTML = opener.document.querySelector('input[name="vbtn-radio"]:checked').value;
	document.getElementById("rName").innerHTML = opener.document.getElementById("rName").value;
	document.getElementById("pName").innerHTML = opener.$("#pName option:selected").text();
	document.getElementById("rContent").innerHTML = opener.document.querySelector('input[name="rContent"]:checked').value;
	document.getElementById("rTel").innerHTML = opener.document.getElementById("rTel").value;
	
	function reservationRegister(){
       	
		var map = {};
		map['mId'] = opener.document.getElementById("mId").value;
		map['mName'] = opener.$("#mName").text();
		map['rDate'] = document.getElementById("rDate").innerText;
		map['rTime'] = document.getElementById("rTime").innerText;
		map['rName'] = document.getElementById("rName").innerText;
		map['pName'] = document.getElementById("pName").innerText;
		map['rContent'] = document.getElementById("rContent").innerText;
		map['rTel'] = document.getElementById("rTel").innerText;
		
		console.log(map);
		
		$.ajax({
			url : "/am/reservationRegister", type : "post",
			data : JSON.stringify(map),
			contentType : "application/json; charset=utf-8",
			success : (result) => {
				console.log("통신 성공")
				if(result == 1){
					alert("예약이 접수 되었습니다.");
					window.opener.location.href="/am/reservationList?id="+${cId}
					window.close();
				}else if(result == 99){
					alert("이미 예약이 꽉 찼습니다");
					window.opener.location.href="/am/reservation";
					window.close();
				}
			},
			error : () => {
				console.log("문제 발생")
			}
		})
  	}
	
	function closeForm(){
		window.close();
	}
	
	</script>
</body>
</html>