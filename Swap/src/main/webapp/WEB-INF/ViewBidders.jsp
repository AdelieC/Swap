<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>Swap</title>
	</head>
	<body>
		<jsp:include page="./includes/header.jsp"/>
		<main>
			<h1>View bidders:</h1>
			<c:forEach var="bidder" items="${biddersList}">
			<article class="bidder-thumbnail">
				<div class="display-field">
					<p class="bidder-lbl">Username:</p>
					<p class="bidder-val">${bidder.username}</p>
					<p class="bidder-bid">Bid:</p>
					<p class="bidder-bid">${bidder.bid}</p>
				</div>
			</article>
			</c:forEach>
			<a class="btn submit2" href="/Swap/home">Back to homepage</a>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>