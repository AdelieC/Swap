<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Log in</title>
	</head>
	<body>
	<jsp:include page="./includes/headerLight.jsp"/>
	<main>
	<h1>Login</h1>
	<jsp:include page="./includes/loginForm.jsp"/>
	<a class="btn" href="/Swap">Cancel</a>
	<a class="btn" href="/Swap/register">Register</a>
	</main>
</body>
</html>