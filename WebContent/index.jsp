<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login V7</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<c:if test="${not empty account }">
			<div style="text-align: center; width: 100%">
				<h2>Welcome to NM-CNPM-GROUP7's website</h2> <br>
				<h3>Your email: ${account.email}</h3>
			</div>
			</c:if>
			<div class="wrap-login100 p-t-90 p-b-30">
				<form class="login100-form validate-form">
					<c:if test="${empty account }">
					<span class="login100-form-title p-b-40">
						Login
					</span>
					<div>
							<a href="loginEmailPassword.jsp" class="btn-login-with bg3 m-b-10">
									 <i class="fa fa-user" aria-hidden="true"></i>
									Login with Email and Password
								</a>
						<a href="#" class="btn-login-with bg1 m-b-10">
							<i class="fa fa-facebook-official"></i>
							Login with Facebook
						</a>

						<a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/CNPM-Group7/login-google&response_type=code
    							&client_id=559738915667-1fg4su2eif5r7ml48e5jbrkc222mmqa0.apps.googleusercontent.com&approval_prompt=force" class="btn-login-with bg2">
							 <i class="fa fa-google" aria-hidden="true"></i>
							Login with Google
						</a>
					</div>
					</c:if>
				</div>

				<div class="wrap-input100 validate-input m-b-16" data-validate="Please enter email: ex@abc.xyz">
					
				</div>

				<div class="wrap-input100 validate-input m-b-20" data-validate = "Please enter password">
				
				
				</div>

				<div class="container-login100-form-btn">
					
				</div>
				
				<div class="flex-col-c p-t-224">
				
				</div>
				
					
				</form>
			</div>
		</div>
	</div>
	
	
<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>