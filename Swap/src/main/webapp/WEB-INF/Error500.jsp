<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<jsp:include page="./includes/basicLinks.html"/>
	<head>
		<meta charset="UTF-8">
	    <title>Error 500</title>
	</head>
	<body>
	 	<jsp:include page="./includes/header.jsp"/>
		<main class="center-v">
		<h3>Oops... Something has gone wrong...</h3>
		<a class="btn submit2" href="/Swap">Back to Homepage</a>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>