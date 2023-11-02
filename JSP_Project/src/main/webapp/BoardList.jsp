<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="dto.testDto" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table, th, td{
		border: 1px solid black;
        border-collapse: collapse;
	}
	td, th {
            padding: 10px;
        }
</style>
</head>
<body>
	<% ArrayList<testDto> list = (ArrayList<testDto>)request.getAttribute("tList");  %>


	<h1>BoardList.jsp</h1>
	<!-- 글번호, 글제목, 작성자, 작성일, 조회수 -->
	<table>
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>작성자</th>
		</tr>
		
		<% for(int i=0; i < list.size(); i++){ %>
		<tr>
			<td> <%=list.get(i).getNum() %> </td>
			<td> <%=list.get(i).getTitle() %> </td>
			<td> <%=list.get(i).getWriter() %> </td>
		</tr>
		
		<% } %>
		
	</table>
	
	<hr>
	
	<table>
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>작성자</th>
		</tr>

	<c:forEach items="${tList }" var="tdto"> <!-- 반복 -->

		<tr>
			<td>${tdto.num }</td>
			<td>${tdto.title }</td>
			<td>${tdto.writer }</td>
		</tr>
	</c:forEach>
	</table>
	
	
	
	
	
	
	

</body>
</html>











