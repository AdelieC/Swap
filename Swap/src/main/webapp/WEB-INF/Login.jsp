<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Log in</title>
	</head>
	<body>
	<h1>Login</h1>
	<jsp:include page="./includes/loginForm.jsp"/>
	<a class="btn" href="/Swap">Cancel</a>
	<a class="btn" href="/Swap/register">Register</a>
</body>
</html>