<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>           
<!doctype html>
<html lang="ko">
<head>
<script type="text/javascript"
  src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js"
  charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    var name = ${result}.response.name;
    var email = ${result}.response.email;
    $("#name").html("환영합니다. "+name+"님");
    $("#email").html(email);
    });
  //location.href = "${pageContext.request.contextPath}/";
</script>
<link href="/am/resources/css/customer/naverLogin.css" rel="stylesheet">
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
  <div
    style="background-color: #0B1F54; width: 100%; height: 50px; text-align: center; color: white;">
    <h3>네이버 로그인 성공</h3>
  </div>
  <br>
  <div id="naverInfo">
  <h2 id="name"></h2>
  <h5 id="email"></h5><br><br>
  <p> 메인화면으로 이동합니다 </p>
  </div>
  <script>
    $(function () {
      $("body").hide();
      $("body").fadeIn(1000);  // 1초 뒤에 사라 지자 
     
      setTimeout(function(){$("body").fadeOut(2000);},2000);
      setTimeout(function(){location.href= "/am"},3000);
// 2초 뒤에 메인 화면 으로 가자  
    })
  </script>

</body>
</html>