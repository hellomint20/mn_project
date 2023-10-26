<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>예약 페이지</title>
<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">
<link href="/am/css/reservation/reservationForm.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<script type="text/javascript">

	window.onload = function() {
		buildCalendar();
	} // 웹 페이지가 로드되면 buildCalendar 실행

	let nowMonth = new Date(); // 현재 달을 페이지를 로드한 날의 달로 초기화
	let today = new Date(); // 페이지를 로드한 날짜를 저장
	today.setHours(0, 0, 0, 0); // 비교 편의를 위해 today의 시간을 초기화

	// 달력 생성 : 해당 달에 맞춰 테이블을 만들고, 날짜를 채워 넣는다.
	function buildCalendar() {

		let firstDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth(), 1); // 이번달 1일
		let lastDate = new Date(nowMonth.getFullYear(),
				nowMonth.getMonth() + 1, 0); // 이번달 마지막날

		let tbody_Calendar = document.querySelector(".Calendar > tbody");
		document.getElementById("calYear").innerText = nowMonth.getFullYear(); // 연도 숫자 갱신
		document.getElementById("calMonth").innerText = leftPad(nowMonth
				.getMonth() + 1); // 월 숫자 갱신

		while (tbody_Calendar.rows.length > 0) { // 이전 출력결과가 남아있는 경우 초기화
			tbody_Calendar.deleteRow(tbody_Calendar.rows.length - 1);
		}

		let nowRow = tbody_Calendar.insertRow(); // 첫번째 행 추가           

		for (let j = 0; j < firstDate.getDay(); j++) { // 이번달 1일의 요일만큼
			let nowColumn = nowRow.insertCell(); // 열 추가
		}

		for (let nowDay = firstDate; nowDay <= lastDate; nowDay.setDate(nowDay
				.getDate() + 1)) { // day는 날짜를 저장하는 변수, 이번달 마지막날까지 증가시키며 반복  

			let nowColumn = nowRow.insertCell(); // 새 열을 추가하고

			let newDIV = document.createElement("p");
			newDIV.innerHTML = leftPad(nowDay.getDate()); // 추가한 열에 날짜 입력
			nowColumn.appendChild(newDIV);

			if (nowDay.getDay() == 6) { // 토요일인 경우
				nowRow = tbody_Calendar.insertRow(); // 새로운 행 추가
			}

			if (nowDay < today) { // 지난날인 경우
				newDIV.className = "pastDay";
			} else if (nowDay.getFullYear() == today.getFullYear()
					&& nowDay.getMonth() == today.getMonth()
					&& nowDay.getDate() == today.getDate()) { // 오늘인 경우           
				newDIV.className = "today";
				newDIV.onclick = function() {
					choiceDate(this);
				}
			} else { // 미래인 경우
				newDIV.className = "futureDay";
				newDIV.onclick = function() {
					choiceDate(this);
				}
			}
		}
	}

	// 날짜 선택
	function choiceDate(newDIV) {
		if (document.getElementsByClassName("choiceDay")[0]) { // 기존에 선택한 날짜가 있으면
			document.getElementsByClassName("choiceDay")[0].classList
					.remove("choiceDay"); // 해당 날짜의 "choiceDay" class 제거
		}
		newDIV.classList.add("choiceDay"); // 선택된 날짜에 "choiceDay" class 추가
	}
	// 이전달 버튼 클릭
	function prevCalendar() {
	    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() - 1, nowMonth.getDate());   // 현재 달을 1 감소
	    buildCalendar();    // 달력 다시 생성
	}
	// 다음달 버튼 클릭
	function nextCalendar() {
	    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, nowMonth.getDate());   // 현재 달을 1 증가
	    buildCalendar();    // 달력 다시 생성
	}
	
	// input값이 한자리 숫자인 경우 앞에 '0' 붙혀주는 함수
	function leftPad(value) {
	    if (value < 10) {
	        value = "0" + value;
	        return value;
	    }
	    return value;
	}

	// input값이 한자리 숫자인 경우 앞에 '0' 붙혀주는 함수
	function leftPad(value) {
		if (value < 10) {
			value = "0" + value;
			return value;
		}
		return value;
	}

	function reservationPopup(){ //예약 확인 팝업에 띄울 데이터
		console.log(document.getElementById("rName").value) //예약자 이름
		console.log(document.getElementById("rTel").value) //예약자 전화번호
		console.log(document.getElementById('pName').options[document.getElementById('pName').selectedIndex].text) //동물 이름
		console.log(document.querySelector('input[name="rContent"]:checked').value) //진료 내용
		console.log($("#calYear").text())	//년도
		console.log($("#calMonth").text())	//월
		console.log($(".futureDay.choiceDay").text());	//일
		console.log(document.querySelector('input[name="vbtn-radio"]:checked').value) //시간
		var popupURI1='/am/reservationPopup';
		var reserv = encodeURI(popupURI1);
	    window.open(reserv, '예약확인', 'width=510px,height=600px,scrollbars=yes,resizable=no');
	}
	
</script>

<body>
	<%@ include file="../default/header_reservationPage.jsp"%>
	<div class="box-container">

		<div class="reservation-form">

			<div class="box-hospital-name">
				<strong><b>${name}</b> 예약</strong>
			</div>

			<div class="box-container-left">
				<div class="label">
					<label for="rName">예약자 성함</label><br> <label for="rTel">전화번호</label><br>
					<label for="pName">동물이름</label><br>
				</div>
				<div class="input-form">
					<input type="text" id="rName" name="cId" placeholder="홍길동" required><br>
					<input type="tel" id="rTel" name="cPhone"
						placeholder="010-1234-5678"
						pattern=(\d{3})-?\s?(\d{4})-?\s?(\d{4}) required><br>
						
					<select id="pName" name="pName" required>
						<option value="">선택하세요</option>
						<c:forEach var="list" items="${p_list}">
						<option value="connecting" id="p_name">${list['p_name']}</option>
						</c:forEach>
					</select><br>
				</div>
			</div>
			<div class="content">
				<label for="rContent">접수내용</label><br> 
					<input type="radio" name="rContent" id="clinic" value="진료">진료 
					<input type="radio" name="rContent" id="check" value="검진">검진 
					<input type="radio" name="rContent" id="vaccin" value="접종">접종
			</div>
		</div>
		<div class="box-container-right">
			<div class="cal-all">
				<table class="Calendar">
					<thead>
						<tr>
							<td onClick="prevCalendar();" style="cursor: pointer;">&#60;</td>
							<td colspan="5"><span id="calYear"></span>년 <span
								id="calMonth"></span>월</td>
							<td onClick="nextCalendar();" style="cursor: pointer;">&#62;</td>
						</tr>
						<tr>
							<td>일</td>
							<td>월</td>
							<td>화</td>
							<td>수</td>
							<td>목</td>
							<td>금</td>
							<td>토</td>
						</tr>
					</thead>

					<tbody>
					</tbody>
				</table>
			</div>

			<div class="timeList">
				<c:forEach var="AM" items="${timeList}" varStatus="vs" step="3">
					<div class="btn-group btn-group-lg" >
						<c:choose>
							<c:when test="${vs.index == 0}">
								<input type="radio" class="btn-check" name="vbtn-radio"	id="${timeList[vs.index]}" value="${timeList[vs.index]}" autocomplete="off" checked> 
								<label id="reservationTime" class="btn btn-outline-dark" for="${timeList[vs.index]}">${timeList[vs.index]}</label>
							</c:when>
							<c:otherwise>
								<input type="radio" class="btn-check" name="vbtn-radio"	id="${timeList[vs.index]}" value="${timeList[vs.index]}" autocomplete="off"> 
								<label id="reservationTime" class="btn btn-outline-dark" for="${timeList[vs.index]}">${timeList[vs.index]}</label>
							</c:otherwise>
						</c:choose>
						
						<c:if test="${timeList[vs.index+1] != null}">
							<input type="radio" class="btn-check" name="vbtn-radio"	id="${timeList[vs.index+1]}" value="${timeList[vs.index+1]}" autocomplete="off"> 
							<label id="reservationTime" class="btn btn-outline-dark" for="${timeList[vs.index+1]}">${timeList[vs.index+1]}</label>
						</c:if>
						
						<c:if test="${timeList[vs.index+2] != null}">
							<input type="radio" class="btn-check" name="vbtn-radio"	id="${timeList[vs.index+2]}" value="${timeList[vs.index+2]}" autocomplete="off">
							<label id="reservationTime" class="btn btn-outline-dark" for="${timeList[vs.index+2]}">${timeList[vs.index+2]}</label>
						</c:if>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="box-container-bottom">
		<button class="reservation-submit" onclick="reservationPopup()">예약하기</button>
	</div>

</body>
</html>