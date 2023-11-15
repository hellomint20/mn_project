<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">

</head>
<body>
	<%@ include file="/WEB-INF/views/am/admin/adminHeader.jsp"%>

					<div id="detailDiv" style="display: none;">
					<table class="reservationDetail">
						<tr>
							<td colspan="2" class="Xcla"><button id="X" type="button" onclick="Xclose()">X</button></td>
						</tr>
						<tr>
							<td class="detailTd" colspan="2" id="mediDetail"></td>
						</tr>
						<tr>
							<td class="detailTd" colspan="2" id="mediPhoto">
							<img src="/am/resources/img/common/default.jpg" width="230px;" height="200px;"></td>
						</tr>
						<tr>
							<td class="detailTd">이름</td>
							<td id="mediName"></td>
						</tr>
						<tr>
							<td class="detailTd">주소</td>
							<td id="mediAddr"></td>
						</tr>
						<tr>
							<td class="detailTd">영업시간</td>
							<td id="mediTime"></td>
						</tr>
						<tr>
							<td class="detailTd">전화번호</td>
							<td id="mediTel"></td>
						</tr>
						<tr>
							<td class="detailTd" colspan="2" id="reBtn">
								<form action="reservationForm/page" method="post">
									<input type="hidden" name="mediId" id="mId">
									<button class="re" type="submit">예약</button>
								</form>
							</td>
						</tr>
					</table>
				</div>


	
</body>
</html>