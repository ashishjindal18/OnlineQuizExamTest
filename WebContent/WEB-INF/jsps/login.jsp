<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<title>Login Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
<style type="text/css">
body {
	background:
		url("${pageContext.request.contextPath}/images/background.jpg");
}

.user-icon {
	top: 153px;
	/* Positioning fix for slide-in, got lazy to think up of simpler method. */
	background: rgba(65, 72, 72, 0.75)
		url('${pageContext.request.contextPath}/images/user-icon.png')
		no-repeat center;
}

.pass-icon {
	top: 201px;
	background: rgba(65, 72, 72, 0.75)
		url('${pageContext.request.contextPath}/images/pass-icon.png')
		no-repeat center;
}
</style>
</head>
<body>
	<div id='cssmenu'>
		<ul>
			<li class=''><a href='${pageContext.request.contextPath}'><span>Home</span></a></li>
			<li><a href='${pageContext.request.contextPath}/login'><span>Login</span></a></li>
			<li><a href='${pageContext.request.contextPath}/register'><span>Register</span></a></li>
			<li><a href='${pageContext.request.contextPath}/feedback'><span>Feedback</span></a></li>
			<li><a href='${pageContext.request.contextPath}/contact'><span>Contact
						us</span></a></li>
		</ul>
	</div>



	<div id="wrapper">

		<form name="login-form" class="login-form" action="checkLogin"
			method="post">

			<div class="header">
				<%
					Object s = request.getAttribute("errorMessage");
					if (s != null) {
				%>
				<font size="2" color="red">*<%=(String) s%></font>
				<%
					}
				%>
				<h1>Login</h1>
				<span></span>
			</div>

			<div class="content">
				<input name="username" type="text" class="input username"
					placeholder="Username" />
				<div class="user-icon"></div>
				<input name="password" type="password" class="input password"
					placeholder="Password" />
				<div class="pass-icon"></div>
			</div>

			<div class="footer">
				<input type="submit" name="submit" value="Login" class="button" />
				<div style="position: absolute; left: 250px; top: 340px">
					Don`t have an account, <a
						href='${pageContext.request.contextPath}/register'>Register</a>
				</div>
			</div>

		</form>

	</div>
	<div class="gradient"></div>


</body>
</html>