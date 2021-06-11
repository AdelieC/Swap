<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="./includes/basicLinks.html"/>
	<title>Swap</title>
</head>
<body>
	<jsp:include page="./includes/header.jsp"/>
	<main class="center-v">
		<h3>Action completed : ${actionCompleted}</h3>
		<a class="btn submit2" href="/Swap">Back to Homepage</a>
	</main>
	<jsp:include page="./includes/footer.jsp"/>
</body>
</html>