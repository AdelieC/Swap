<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
<style><%@include file="/css/layout.css"%></style>
<head>
<meta charset="UTF-8">
<title>Swap</title>
</head>
<body>
	<jsp:include page="./includes/header.jsp"/>
	<main>
		<p>Action completed : ${actionCompleted}</p>
		<a class="btn cancel-btn" href="/Swap">Back to Homepage</a>
	</main>
	<jsp:include page="./includes/footer.jsp"/>
</body>
</html>