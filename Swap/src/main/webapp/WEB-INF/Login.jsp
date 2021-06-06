<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Log in</title>
	</head>
	<body>
	<h1>Login</h1>
	<jsp:include page="./includes/idForm.jsp">
		<jsp:param value="false" name="delete"/>
	</jsp:include>
</body>
</html>