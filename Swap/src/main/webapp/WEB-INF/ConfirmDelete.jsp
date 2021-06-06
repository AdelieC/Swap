<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Confirm delete</title>
	</head>
	<body>
		<h1>Confirm your identity</h1>
		<jsp:include page="./includes/idForm.jsp">
			<jsp:param value="true" name="delete"/>
		</jsp:include>
	</body>
</html>