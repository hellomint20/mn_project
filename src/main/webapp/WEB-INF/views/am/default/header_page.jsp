<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link
   href="https://cdn.jsdelivr.net/gh/sunn-us/SUITE/fonts/static/woff2/SUITE.css"
   rel="stylesheet">
<style>
* {
   font-family: 'SUITE', sans-serif !important;
}
</style>

<link href="/am/css/header.css" rel="stylesheet">

</head>
<body>
   <div class="all-h">
      <div id="imgHeader">
      <c:choose>
         <c:when test="${mediId != null}">
            <span id="headerText"><a href="/am/mediInfo?id=${mediId }"
               id="text">마이페이지</a> | <a href="/am/logout" id="text">로그아웃</a></span>
         </c:when>
         <c:when test="${userId != null}">
            <span id="headerText"><a href="/am/customerInfo"
               id="text">마이페이지</a> | <a href="/am/logout" id="text">로그아웃</a></span>
         </c:when>
         <c:otherwise>
            <span id="headerText"><a href="/am/customerInfo" id="text">마이페이지</a>
               | <a href="/am/main" id="text">로그인</a></span>
         </c:otherwise>
      </c:choose>
      </div>
      <div id="headerLogo">
         <a href="/am/reservationState?id=${mediId }"><img src="/am/resources/img/logo2.png"
            width="250px" height="100px"></a>
      </div>

      <br>
      <br>
      <br>
      <br>
      <br>
      <hr>
   </div>

</body>
</html>