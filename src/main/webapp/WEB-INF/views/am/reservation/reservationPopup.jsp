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
<!-- 포트원 결제 -->
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
    <!-- jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<!-- 포트원 결제 -->
	
</head>

<body>
    <h1 id="title">예약정보</h1>
    <input id="cId" type="hidden" value="${cId}">
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
    	<button type="button" id="reservationBtn" onclick="moneyBtn()">예약 확정</button>
    	<br>예약 확정 버튼을 누르면 예약금 5,000원이 결제 됩니다
    </div>
    
    <script type="text/javascript">
    var IMP = window.IMP; // 생략가능
    IMP.init('imp16774044'); // <-- 본인 가맹점 식별코드 삽입
    
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
	
 	function moneyBtn() {
 		// IMP.request_pay(param, callback) 결제창 호출
 		let mId = opener.$("#mName").text();
		let cId = document.getElementById("cId").value;
 		IMP.init("imp16774044");
 		IMP.request_pay({
 			pg: "kakaopay.TC0ONETIME",
 		    pay_method: "card",
 		    merchant_uid : 'merchant_'+new Date().getTime(),
 		    name : mId +" 예약금 결제",
 		    buyer_name : cId,
 		    amount : 100
 		}, function (rsp) { // callback
			// 결제검증
			$.ajax({
	        	type : "POST",
	        	url : "/am/verifyIamport/" + rsp.imp_uid 
	        }).done(function(data) {
	        	// 위의 rsp.paid_amount 와 data.response.amount를 비교한후 로직 실행 (import 서버검증)
	        	if(rsp.paid_amount == data.response.amount){
		        	alert("결제 및 결제검증완료");
		    		var map = {};
		    		map['mId'] = opener.document.getElementById("mId").value;
		    		map['mName'] = opener.$("#mName").text();
		    		map['rDate'] = document.getElementById("rDate").innerText;
		    		map['rTime'] = document.getElementById("rTime").innerText;
		    		map['rName'] = document.getElementById("rName").innerText;
		    		map['pName'] = document.getElementById("pName").innerText;
		    		map['rContent'] = document.getElementById("rContent").innerText;
		    		map['rTel'] = document.getElementById("rTel").innerText;
		    		
		        	$.ajax({
		    			url : "/am/payResRegister", type : "post",
		    			data : JSON.stringify(map),
		    			contentType : "application/json; charset=utf-8",
		    			success : (result) => {
		    				console.log(result)
		    				if(result['result'] == '1'){
		    					alert("예약이 접수 되었습니다.");
		    					window.opener.location.href="/am/reservationList?id="+result['userId']
		    					window.close();
		    				}else if(result['result'] == '99'){
		    					alert("이미 예약이 꽉 찼습니다");
		    					window.opener.location.href="/am/reservation";
		    					window.close();
		    				}
		    			},
		    			error : () => {
		    				alert("문제 발생")
		    			}
		    		})
	        	} else {
	        		alert("결제 실패");
	        	}
	        });
		});
  	}
	
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
				
		$.ajax({
			url : "/am/reservationRegister", type : "post",
			data : JSON.stringify(map),
			contentType : "application/json; charset=utf-8",
			success : (result) => {
				if(result['result'] == '1'){
					alert("예약이 접수 되었습니다.");
					window.opener.location.href="/am/reservationList?id="+result['userId']
					window.close();
				}else if(result['result'] == '99'){
					alert("이미 예약이 꽉 찼습니다");
					window.opener.location.href="/am/reservation";
					window.close();
				}
			},
			error : () => {
				alert("문제 발생")
			}
		})
  	}
	
	function closeForm(){
		window.close();
	}
	
	</script>
</body>
</html>