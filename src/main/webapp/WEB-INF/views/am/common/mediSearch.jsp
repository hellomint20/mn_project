<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/am/css/bootstrap/bootstrap.css" rel="stylesheet">
<link href="/am/css/common/mediSearch.css" rel="stylesheet">
<link href="/am/css/common/mediSearch_table.css" rel="stylesheet">

</head>
<body>

	<%@ include file="../default/header_mediSearch.jsp" %>
	
	<div>
		<div id="searchWindow">
			
			<input class="search" type="text" placeholder="지역 검색">
			<a href="#"><img src="/am/resources/img/searchIcon.png" width="25px" height="25px"></a>
		</div>
	</div>
	
		<table>
	    	<thead>
		      <tr>
		        <th>병원이름</th> 
		      </tr>
	   		</thead>
	    	<tbody>
		      <tr>
		        <td>예시) 튼튼병원</td>
		      </tr>
		      
	    	</tbody>
	  	</table> 

	
	
	
</body>
</html>