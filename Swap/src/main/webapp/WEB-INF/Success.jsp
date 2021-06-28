<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>Success</title>
	</head>
	<body>
	<jsp:include page="./includes/header.jsp"/>
		<main>
			<h1>Successful update!</h1>
			<p>Started today : ${nbOfStarted} auctions</p>
			<p>Closed today : ${nbOfEnded} auctions</p>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>