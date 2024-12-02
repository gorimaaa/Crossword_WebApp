<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/home.css">
<script src="<%=request.getContextPath()%>/js/home.js"></script>
</head>
<body>
<p>Nouvelle papdays</p>
<%=request.getAttribute("message") %>

</body>
</html>