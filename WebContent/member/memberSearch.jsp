<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
	function decoTest() {
		console.log;
	}
</script>
<title>memberSearch.jsp</title>
</head>
<body>	<%-- http://localhost//memberSearch.do --%>
<h3>회원검색</h3>
<a href = "${pageContext.request.contextPath}/memberList.do">전체검색</a>
<form action = "${pageContext.request.contextPath}/memberSearch.do">
<input type="hidden" name="job" value="search"> 
	${error}
	id : <input name="id">
</form>
</body>
</html>