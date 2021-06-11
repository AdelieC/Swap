<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>Log in</title>
	</head>
	<body>
		<jsp:include page="./includes/header.jsp"/>
		<main>
			<h1>Login</h1>
			<jsp:include page="./includes/loginForm.jsp"/>
			<a class="btn cancel-btn" href="/Swap">Cancel</a>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>