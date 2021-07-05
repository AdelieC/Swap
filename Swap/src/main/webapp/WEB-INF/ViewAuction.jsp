<%@ include file="./includes/base.jsp" %>
<c:set var="recipientId" scope="request" value="${seller.userId}"/>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>Swap</title>
	</head>
	<body>
		<jsp:include page="./includes/header.jsp"/>
		<main id="auction-main">
			<c:choose>
				<c:when test="${empty bid}">
					<h1>Auction details</h1>
				</c:when>
				<c:otherwise>
					<c:if test="${auction.status.equals('OVER') || auction.status.equals('PICKED_UP')}">
						<c:choose>
							<c:when test="${user.getUserId() != bid.userId}">
								<h1>${bidder} won this auction</h1>
							</c:when>
							<c:otherwise>
								<h1>You won this auction !</h1>
							</c:otherwise>
						</c:choose>
					</c:if>	
				</c:otherwise>
			</c:choose>
			<h2>${auction.name}</h2>
			<section class="display-section">
				<div class="display-field">
					<p class="display-field-label">Description:</p>
					<p class="display-field-info">${auction.description}</p>
				</div>
				<jsp:include page="./includes/imageSlider.jsp"/>
				<div class="display-field">
					<p class="display-field-label">Category:</p>
					<p class="display-field-info">${category.label}</p>
				</div>
				<div class="display-field">
				<c:choose>
					<c:when test="${!empty bid}">
						<p class="display-field-label">Highest bid:</p>
						<div class="display-field-info">
							<p class="best-bidder">${bid.bidPrice}</p>
							<c:if test="${auction.status.equals('CREATED') || auction.status.equals('ONGOING')}">
								<p class="best-bidder">by ${bidder}</p>
							</c:if>
						</div>
					</c:when>
					<c:otherwise>
						<p class="display-field-label">Highest bid:</p>
						<p class="display-field-info">There is no bid yet.</p>
					</c:otherwise>
				</c:choose>
				</div>
				<div class="display-field">
					<p class="display-field-label">Initial price:</p>
					<p class="display-field-info">${auction.initialPrice}</p>
				</div>
				<div class="display-field">
					<p class="display-field-label">End date:</p>
					<p class="display-field-info">${auction.endDate}</p>
				</div>
				<div class="display-field">
					<p class="display-field-label">Pick up point:</p>
					<p class="display-field-info">${pickUpPoint}</p>
				</div>
				<div class="display-field">
					<p class="display-field-label">Seller:</p>
					<p class="display-field-info"><a href="/Swap/user?id=${seller.userId}">${seller.username}</a></p>
				</div>
				<c:if test="${!empty user && !user.wasDisabled()}">
					<c:choose>
						<c:when test="${user.getUserId() != seller.userId && auction.status.equals('ONGOING')}">
							<form method="post" action="/Swap/auction/bid?id=${auction.id}">
								<div class="display-field">
									<label class="display-field-label" for="offer">My offer:</label>
									<input class="display-field-input" type="number" name="offer" min="${auction.salePrice + 1}" placeholder="${auction.salePrice + 1}" required>
									<input type="submit" class="btn cta" value="Bid">
								</div>
							</form>
						</c:when>
						<c:otherwise>
							<c:if test="${user.getUserId() == seller.userId}">
								<c:if test="${auction.status.equals('CREATED')}">
									<a class="btn submit1" href="/Swap/auction?id=${auction.id}">Update auction</a>
								</c:if>
								<a class="btn submit2" href="/Swap/auction/view/bidders?id=${auction.id}">View bidders</a>
							</c:if>
						</c:otherwise>
					</c:choose>
					<c:if test="${user.getUserId() != seller.userId && user.isAdmin()}">
						<jsp:include page="./includes/cancelAuctionBtn.jsp"/>
					</c:if>
				</c:if>
			</section>
			<c:choose>
			<c:when test="${user.getUserId() != seller.userId && user.isAdmin()}">
				<jsp:include page="./includes/messageForm.jsp"/>
			</c:when>
			<c:otherwise>
				<a href="/Swap/register" class="btn cta">Register to bid</a>
			</c:otherwise>
			</c:choose>
			<a class="btn submit2" href="/Swap">Back to Homepage</a>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>