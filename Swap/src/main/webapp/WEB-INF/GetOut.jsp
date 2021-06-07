<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Swap</title>
</head>
<body>
	<jsp:include page="./includes/header.jsp"/>
	<main>
		<p>Action completed : ${actionCompleted}</p>
		<a href="/Swap">Back to Homepage</a>
	</main>
	<jsp:include page="./includes/footer.jsp"/>
</body>
</html>