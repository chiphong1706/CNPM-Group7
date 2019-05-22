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

<!-- 4.7.3 Trang login hien thi form Login-->
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
		<!-- 4.7.4 Nguoi dung chon Forget Password tren form Login-->
		<a href="#" onclick="document.getElementById('id01').style.display='block'">Forget Password?</a> <br>
		<a href="registry">Sign up!</a>
	<div class="login-social">
		<p>Or</p>
		<a href="https://www.facebook.com/dialog/oauth?client_id=1520573608076236&redirect_uri=https://cnpm7.azurewebsites.net/login-facebook"><img src="facebook-icon.png" width="40px" height="40px" /></a>
		<a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=https://cnpm7.azurewebsites.net/login-google&response_type=code
    &client_id=198443431068-vpe09k5a22ertojde09eh8rj7rheuact.apps.googleusercontent.com&approval_prompt=force"><img src="google-icon.png" width="40px" height="40px"/></a>
	</div>
	</div>
	

	<!-- 4.7.5 Hien form Recovery Password-->
	<div id="id01" class="modal">
		<form class="modal-content animate" method="post" action="recovery-password">
		<span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
		<h2>Recovery Password</h2>
			<!-- 4.7.6 Nguoi dung nhap email da dang ky-->
			<input type="email" name="email" placeholder="Enter your email registed" required>
			<!-- 4.7.7 Nguoi dung chon nut Get password-->
			<button type="submit">Get password</button>
		</form>
	</div>


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