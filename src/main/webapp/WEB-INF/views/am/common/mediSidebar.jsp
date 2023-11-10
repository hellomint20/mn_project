<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>side bar</title>
	<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="/am/css/mediSidebar.css">
    
</head>
<body>

<div class="menu">
    <ul>
		<li>
        	<a href="/am/mediInfo?id=${mediId }">
        		<span class="icon"></span>
        		<span class="title">병원 정보</span>
        	</a>
        </li>
        <li>
        	<a href="/am/mediPwdChk?id=${mediId }">
        		<span class="icon"></span>
        		<span class="title">정보 수정</span>
        	</a>
        </li>
        <li>
        	<a href="/am/reservationStateWait?id=${mediId }">
        		<span class="icon"></span>
        		<span class="title">예약 관리</span>
        	</a>
        </li>
        <li>
        	<a href="/am/mediDelete?id=${mediId }">
        		<span class="icon"></span>
        		<span class="title">회원 탈퇴</span>
        	</a>
        </li>
    </ul>
</div>

	
</body>
</html>