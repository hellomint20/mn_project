<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AniMedi</title>

<link href="/am/resources/css/common/main.css" rel="stylesheet">
<link href="/am/resources/css/bootstrap/bootstrap.css" rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css"
	rel="stylesheet">
<style>
* {
	font-family: 'SUITE', sans-serif !important;
}
</style>

</head>
<body>

	<div id="containertMain">
		<div id="contentMain">
			<div id="leftMain">
				<div id="leftMainContent">
					<img src="/am/resources/img/main/logoMain.png"
						class="rounded mx-auto d-block" width="430px" height="200px"><br>
					<b>동물병원예약통합시스템</b>
				</div>
			</div>
			<div id="rightMain">
				<div id="rightMaincontainer">
					<div id="rightMainContent">
						<div id="rightMainContentFirst">
							<a href="mediSearch"><img
								src="/am/resources/img/main/dogMain.jpg" class="rounded"
								width="300px" height="200px" style="margin-bottom: 10px;"></a>
							<b class="mainBtag">병원 조회</b>
							<h6>주변의 동물병원을 찾아보세요</h6>
						</div>
						<div id="rightMainContentFirst">
							<a href="reservation"><img
								src="/am/resources/img/main/catMain.jpg" class="rounded"
								width="300px" height="200px" style="margin-bottom: 10px;"></a>
							<b class="mainBtag">병원 예약</b>
							<h6>편리하게 예약할 수 있습니다</h6>
						</div>
					</div>
				</div>
				<div id="rightMaincontainer2">
					<div id="rightMainContent2">
						<div id="rightMainContentSecond">
							<a href="mediLogin"><img
								src="/am/resources/img/main/mediLogin.png" width="300px;"
								height="150px;" style="border: solid white;"></a>
						</div>
						<div id="rightMainContentSecond">
							<a href="customerLogin"><img
								src="/am/resources/img/main/customerLogin.png" width="300px;"
								height="150px;" style="border: solid white;"></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>