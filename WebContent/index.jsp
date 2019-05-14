<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
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
	<div class="login-box">
		<img src="avatar.png" class='avatar'>
		<h2>Login with email</h2>
	<form method="post" action="LoginServlet" >
		<p>Email</p>
		<input type="email" name="email" placeholder="Enter your email" required>
		<p>Password</p>
		<input type="password" name="password" placeholder="Enter your password" required>
		<c:if test="${not empty error_login }"><p style="color: red; font-size: 90%; margin-bottom: 1rem">${error_login }</p></c:if>
		<button type="submit">Login</button>
	</form>
		<!-- 5.7.1 Người dùng chọn “Forget Password?” trên form Login.-->
		<a href="#" onclick="document.getElementById('id01').style.display='block'">Forget Password?</a> <br>
		<a href="registry">Sign up!</a>
	<div class="login-social">
		<p>Or</p>
		<a href="https://www.facebook.com/dialog/oauth?client_id=1520573608076236&redirect_uri=https://localhost:8443/CNPM-Group7/login-facebook"><img src="facebook-icon.png" width="40px" height="40px" /></a>
		<a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/CNPM-Group7/login-google&response_type=code
    &client_id=559738915667-1fg4su2eif5r7ml48e5jbrkc222mmqa0.apps.googleusercontent.com&approval_prompt=force"><img src="google-icon.png" width="40px" height="40px"/></a>
	</div>
	</div>
	

	<!-- 5.7.2	Hệ thống hiện form Recovery Password.-->
	<div id="id01" class="modal">
		<form class="modal-content animate" method="post" action="recovery-password">
		<span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
		<h2>Recovery Password</h2>
			<!-- 5.7.3	Người dùng nhập email đã đăng ký -->
			<input type="email" name="email" placeholder="Enter your email registed">
			<!-- 5.7.4	Người dùng chọn nút “Get Password” -->
			<button type="submit">Get password</button>
		</form>
	</div>
</c:if>

<script>
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>
</body>
</html>