<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그아웃 한다? 진짜한다? 진짜 쿠키 삭제한다?</h1>
	<form action="/customLogout" method='post'>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<button>눌러라 좀</button>
	</form>
</body>
</html>