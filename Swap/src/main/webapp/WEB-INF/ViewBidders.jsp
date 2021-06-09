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
		<h1>View bidders:</h1>
		<c:forEach var="bidder" items="${biddersList}">
		<fieldset>
			<p>Username: ${bidder.username}      Bid: ${bidder.bid}</p>
		</fieldset>
		</c:forEach>
	</main>
	<jsp:include page="./includes/footer.jsp"/>
</body>
</html>