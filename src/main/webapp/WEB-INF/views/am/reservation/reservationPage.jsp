<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">
<link href="/am/css/reservation/reservationPage.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

</head>
<body>
	<%@ include file="/WEB-INF/views/am/default/header_reservationPage.jsp"%>
	
	<script type="text/javascript">

    function filter(){

        var value, name, item, i;

        value = document.getElementById("mediSearch").value.toUpperCase(); //검색창
        item = document.getElementsByClassName("listTr"); //병원 이름 부분
        
        console.log(document.getElementById("mediSearch").value)
        
        if(!document.getElementById("mediSearch").value){
        	location.reload();
        }
		
        for(i=0;i<item.length;i++){
          name = item[i].getElementsByClassName("listBtn");
          if(name[0].innerHTML.toUpperCase().indexOf(value) > -1){
              item[i].style.display = "";
            }else{
              item[i].style.display = "none";
            }
        }
      }
	
	function detailView(mediId) {
		console.log(mediId);	
		$.ajax({
			url : "reservation/mediInfo", type : "post",
			data : mediId,
			contentType : "application/json; charset=utf-8",
			success : (mediInfo) => {
				console.log("통신 성공")
				console.log(mediInfo)
				let name = mediInfo['m_name'];
				
				document.getElementById("mediDetail").innerHTML ="{ "+mediInfo['m_name'] + " } 상세 정보";
				document.getElementById("mediName").innerHTML = mediInfo['m_name'];
				document.getElementById("mediAddr").innerHTML = mediInfo['m_addr'];
				document.getElementById("mediTime").innerHTML = mediInfo['open_time']+" - "+mediInfo['close_time'];
				document.getElementById("mediTel").innerHTML = mediInfo['m_tel'];
				document.getElementById("mId").value =  mediInfo['m_id']

			},
			error : () => {
				console.log("문제 발생")
			}
		})
		
		$("#detailDiv").css("display", "block");
	}
	
	function Xclose(){
		console.log("X")
		$("#detailDiv").css("display", "none");
	}
	</script>


	<div id="searchDiv">
		<div id="reservationSearchWindow">
			<input class="reservationSearch" id="mediSearch" type="text" placeholder="예약할 병원 이름 검색"> 
			<a id="mediSearch" onclick="filter()"><img src="/am/resources/img/searchIcon.png" width="25px" height="25px"></a>
		</div>
	</div>
	<div id="tableDiv">
		<div id="centerDiv">
			<div id="contentDiv">
				<div id="listDiv">
					<table class="listTable">
						<tr class="trCla">
							<th>번호</th><th>병원명</th>
						</tr>
						<c:forEach var="list" items="${list}">
							<c:set var="i" value="${i+1 }" />
							<tr class="listTr">
								<td class="listTd">${i }</td>
								<td id="mediList"><button class="listBtn" type="button" onclick="detailView('${list['m_id']}')" style="width: 100px;">${list['m_name']}</button></td>
							</tr>
							
						</c:forEach>
					</table>
				</div>
				
				<div id="detailDiv" style="display: none;">
					<table class="reservationDetail">
						<tr><td colspan="2" class="Xcla"><button id="X" type="button" onclick="Xclose()" >X</button></td></tr>
						<tr><td class="detailTd" colspan="2" id="mediDetail" ></td></tr>
						<tr><td class="detailTd" colspan="2" id="mediPhoto" ><img src="/am/resources/img/common/default.jpg" width="230px;" height="200px;"></td></tr>
						<tr><td class="detailTd">이름</td><td id="mediName"></td></tr>
						<tr><td class="detailTd">주소</td><td id="mediAddr" ></td></tr>
						<tr><td class="detailTd">영업시간</td><td id="mediTime" ></td></tr>
						<tr><td class="detailTd">전화번호</td><td id="mediTel" ></td></tr>
						<tr><td class="detailTd" colspan="2" id="reBtn">
							<form action="reservationForm/page" method="post">
								<input type="hidden" name="mediId" id="mId">
								<button class="re" type="submit">예약</button>
							</form>
						</td></tr>
				</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>