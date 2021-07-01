<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>My auctions</title>
	</head>
	<body>
		<jsp:include page="./includes/header.jsp"/>
		<main id="my-auctions-main">
		<h1>My auctions</h1>
		<c:if test="${futureAuctions != null && futureAuctions.size() > 0}">
			<section>
				<h2>My future auctions</h2>
				<c:forEach var="auction" items="${futureAuctions}">	
					<a href="/Swap/auction/view?id=${auction.id}">
						<article>
							<h4>${auction.item}</h3>
							<div class="placeholder" style="background-image: url('/Swap/resources/auctions-thumbnails/${auction.pictureName}');background-size: cover;"></div>
							<p>Price: ${auction.price}</p>
							<p>Start date: ${auction.startDate}</p>
							<p>End date: ${auction.endDate}</p>
						</article>
					</a>
				</c:forEach>
			</section>
		</c:if>
			<c:if test="${ongoingAuctions != null && ongoingAuctions.size() > 0}">
			<section>
				<c:forEach var="auction" items="${ongoingAuctions}">	
					<a href="/Swap/auction/view?id=${auction.id}">
						<article>
							<h4>${auction.item}</h3>
							<div class="placeholder" style="background-image: url('/Swap/resources/auctions-thumbnails/${auction.pictureName}');background-size: cover;"></div>
							<p>Price: ${auction.price}</p>
							<p>Start date: ${auction.startDate}</p>
							<p>End date: ${auction.endDate}</p>
						</article>
					</a>
				</c:forEach>
			</section>
		</c:if>
		<c:if test="${pastAuctions != null && pastAuctions.size() > 0}">
			<section>
				<c:forEach var="auction" items="${pastAuctions}">	
					<a href="/Swap/auction/view?id=${auction.id}">
						<article>
							<h4>${auction.item}</h3>
							<div class="placeholder" style="background-image: url('/Swap/resources/auctions-thumbnails/${auction.pictureName}');background-size: cover;"></div>
							<p>Price: ${auction.price}</p>
							<p>Start date: ${auction.startDate}</p>
							<p>End date: ${auction.endDate}</p>
						</article>
					</a>
				</c:forEach>
			</section>
		</c:if>
		<a href="/Swap/auction" class="btn cta" id="action"><img src="/Swap/img/create.svg" alt="Create a new auction"/></a>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>