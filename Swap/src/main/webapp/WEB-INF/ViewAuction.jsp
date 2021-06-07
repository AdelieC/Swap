<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Swap</title>
</head>
<body>
	<!-- Include header -->
	<main>
		<h1>Auction details</h1>
		<fieldset>
			<h2>${auction.name}</h2>
			<p>Description: ${auction.description}</p>
			<p>Category: ${category.label}</p>
			<c:choose>
				<c:when test="${!empty bid}">
					<p>Highest bid: ${bid.bidPrice} by ${bidder}</p>
				</c:when>
				<c:otherwise>
					<p>Highest bid: There is no bid yet.</p>
				</c:otherwise>
			</c:choose>
			<p>Initial price: ${auction.initialPrice}</p>
			<p>End date: ${auction.endDate}</p>
			<p>Pick up point: ${pickUpPoint}</p>
			<p>Seller: <a href="">${seller.username}</a></p>
		</fieldset>
		<!-- TODO -->
		<c:if test="${!empty user}">
			<c:choose>
				<c:when test="${user.userId != seller.userId}">
					<form method="post" action="bid">
						<label for="offer">My offer: </label>
						<input type="number" name="offer" min="${auction.salePrice + 1}" placeholder="${auction.salePrice + 1}" required>
						<input type="submit" value="Bid">
					</form>
				</c:when>
				<c:otherwise>
					<a href="auction?id=${auction.id}">Update auction</a>
				</c:otherwise>
			</c:choose>
		</c:if>
	</main>

</body>
</html>