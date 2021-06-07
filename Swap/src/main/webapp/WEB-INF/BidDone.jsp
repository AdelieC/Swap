<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bid</title>
</head>
<body>
	<jsp:include page="./includes/header.jsp"/>
	<main>
	<c:choose>
		<c:when test="${bid == null}">
			<p>Uhoh... Either you did not bid enough points, or you bid more than you actually have!</p>
		</c:when>
		<c:otherwise>
			<p>Congratulations!! You just bid ${bid.getBidPrice()} on auction number ${bid.getAuctionId()}.</p>
		</c:otherwise>
	</c:choose>
		<a href="/Swap">Back to Homepage</a>
	</main>
	<jsp:include page="./includes/footer.jsp"/>
</body>
