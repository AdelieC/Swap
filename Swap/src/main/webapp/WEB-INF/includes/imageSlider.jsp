<%@ include file="base.jsp" %>
<div id="image-slider">
	<c:forEach var="picture" items="${auction.pictures}">
		<img src="/Swap/resources/auctions-img/${picture.name}.${picture.extension}" alt="${auction.name}">
	</c:forEach>
</div>