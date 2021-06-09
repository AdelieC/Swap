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
		<c:choose>
			<c:when test="${empty bid}">
				<h1>Auction details</h1>
			</c:when>
			<c:otherwise>
				<c:if test="${auction.status.equals('OVER') || auction.status.equals('PICKED_UP')}">
					<c:choose>
						<c:when test="${sessionScope.user.getUserId() != bid.userId}">
							<h1>${bidder} won this auction</h1>
						</c:when>
						<c:otherwise>
							<h1>You won this auction !</h1>
						</c:otherwise>
					</c:choose>
				</c:if>	
			</c:otherwise>
		</c:choose>
		<fieldset>
			<h2>${auction.name}</h2>
			<p>Description: ${auction.description}</p>
			<p>Category: ${category.label}</p>
			<c:choose>
				<c:when test="${!empty bid}">
					<p>Highest bid: ${bid.bidPrice}</p>
					<c:if test="${auction.status.equals('CREATED') || auction.status.equals('ONGOING')}">
						<p>by ${bidder}</p>
					</c:if>
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
		<c:if test="${!empty sessionScope.user}">
			<c:choose>
				<c:when test="${sessionScope.user.getUserId() != seller.userId && auction.status.equals('ONGOING')}">
					<form method="post" action="/Swap/auction/bid?id=${auction.id}">
						<label for="offer">My offer: </label>
						<input type="number" name="offer" min="${auction.salePrice + 1}" placeholder="${auction.salePrice + 1}" required>
						<input type="submit" value="Bid">
					</form>
				</c:when>
				<c:otherwise>
					<c:if test="${sessionScope.user.getUserId() == seller.userId}">
						<c:if test="${auction.status.equals('CREATED')}">
							<a href="/Swap/auction?id=${auction.id}">Update auction</a>
						</c:if>
						<a href="/Swap/auction/view/bidders?id=${auction.id}">View bidders</a>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:if>
	</main>

</body>
</html>