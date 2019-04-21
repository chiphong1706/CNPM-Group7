<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Registry</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="login-box">
		<img src="avatar.png" class='avatar'>
		<h2>Sign up</h2>
		<form action="registry" method="post">
		<p>Email</p>
		<input type="email" name="email" placeholder="Enter your email" required>
		<c:if test="${not empty error_email }"><p style="color: red; font-size: 90%; margin-bottom: 1rem">${error_email }</p></c:if>
		<p>Password</p>
		<input type="password" name="password" placeholder="Enter your password" required>
		<button type="submit">Sign up</button>
		</form>
	</div>
</body>
</html>