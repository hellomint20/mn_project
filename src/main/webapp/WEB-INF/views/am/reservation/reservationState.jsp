<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="/am/css/reservation/reservationState.css">

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
window.onload = function() {

    $("#table2").show();
    $("#table3").hide();
}
$(document).ready(function() {
   
    $("#btn_2").click(function() {

        $("#table1").hide();
        $("#table2").show();
        $("#table3").hide();
        $("#table4").hide();
    })
    $("#btn_3").click(function() {

        $("#table1").hide();
        $("#table2").hide();
        $("#table3").show();
        $("#table4").hide();
    })
   
})

function Popup(){
	var popupURI1='/am/reservationApplyPopup';
	var reserv = encodeURI(popupURI1);
    var popup = window.open(reserv, '예약수락', 'width=510px,height=700px,scrollbars=yes,resizable=no');
}

 </script>
 

</head>
<body>

<%@ include file = "../default/header_page.jsp" %>


<div class="r_table">
 <div class="buttonbox">
        <button type="button" id="btn_2">새로운접수</button>
        <button type="button" id="btn_3">승인/취소</button>

    </div>
    <div style="clear:both;"></div>
    <div style="width : 800px; height: 400px;">
        
      <!-- 새로운 접수 테이블 -->
        <div id="table2" width="900px">
            <table class="col-100 col">
                <colgroup>
                    <col width="30%">
                    <col width="20%">
                    <col width="20%">
                    <col width="20%">
                </colgroup>
                <thead>
                    <tr>
                        <th>날짜</th>
                        <th>시간</th>
                        <th>과</th>
                        <th>접수내용</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="list" items="${list }">
                		<tr>
                			<td><a href="#" onclick="Popup()" >${list.date }</a></td>
                			<td>${list.hour }시 ${list.min }분</td>
                			<td>${list.type }</td>
                			<td>${list.content }</td>
                			
                		</tr>
                	</c:forEach>
                    <tr>
                        <td> <a href="#" onclick="Popup()" >2023-10-16</a></td>
                        <td>16:00</td>
                        <td>개</td>
                        <td>접종</td>
                        
                    </tr>
                    
                </tbody>
            </table>
        </div>


      <!-- 승인/취소 테이블 -->
      <!-- 접수상태에 '취소'들어올 시 그 열 글씨 색 gray로 변경 -->
        <div id="table3" width="800px">
            <table class="col-100 col">
                 <colgroup>
                    <col width="30%">
                    <col width="20%">
                    <col width="20%">
                    <col width="20%">
                    <col width="20%">
                </colgroup>
                <thead>
                    <tr>
                        <th>날짜</th>
                        <th>시간</th>
                        <th>과</th>
                        <th>접수내용</th>
                        <th>접수상태</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>2023-10-01</td>
                        <td>11:00</td>
                        <td>개</td>
                        <td>검진</td>
                        <td>승인</td>
                    </tr>
                    <tr>
                        <td>2023-10-01</td>
                        <td>14:00</td>
                        <td>개</td>
                        <td>접종</td>
                        <td>승인</td>
                    </tr>
                    <tr>
                        <td>2023-10-02</td>
                        <td>16:00</td>
                        <td>고양이</td>
                        <td>진료</td>
                        <td>취소</td>
                    </tr>
                    <tr>
                        <td>2023-10-02</td>
                        <td>17:00</td>
                        <td>앵무</td>
                        <td>검진</td>
                        <td>승인</td>
                    </tr>
                </tbody>
            </table>
        </div>

    </div>
  </div>
</body>
</html>