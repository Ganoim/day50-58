<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>joinSuccess.jsp</h1>
	
</body>
<script>
	alert('회원가입에 성공했습니다.')
    location.href='${pageContext.request.contextPath}/mainPage';
	
</script>
</html>