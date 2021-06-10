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
			<a class="btn cancel-btn" href="/Swap/home">Back to homepage</a>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>