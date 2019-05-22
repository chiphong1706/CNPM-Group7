<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Index</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<c:if test="${not empty account }">
<div style="text-align: center; margin: auto">
	<h2>Welcome to NM-CNPM-Group7's website</h2>
	<h3>Your email: ${account.email }</h3>
</div>
</c:if>
<c:if test="${empty account }">
	<!-- 4.7.1 Nguoi dung chon link Login tu trang index -->
	<!-- 4.7.2 Trang index yeu cau truy cap trang login -->
	<a href="LoginServlet" style="margin: 45%; font-size: 40px; text-decoration: none; color: yellow;">Login</a>
</c:if>
</body>
</html>