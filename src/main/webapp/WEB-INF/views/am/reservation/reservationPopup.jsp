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
</head>
<script type="text/javascript">
	function closeForm(){
		window.close();
	}
	
</script>
<body>
	
  
    <h1 id="title">예약정보</h1>
    <div class="popup-main-box">
		<div class="info-title">
    		날짜 : <br>
    		시간 : <br>
    		예약자 :<br>
    		반려동물 이름 : <br>
    		예약내용 : <br>
    		전화번호 :
		</div>
   		<div class="info-content">
   			2023년 10월 20일<br>
   			17:00<br>
   			최재연<br>
   			앵두<br>
   			접종<br>
   			010-3335-9998
   		</div>
    </div>
    <div class="popup-bottom-box">
    	<button type="button" id="btn-close" onclick="closeForm()">닫기</button>
    </div>
</body>
</html>