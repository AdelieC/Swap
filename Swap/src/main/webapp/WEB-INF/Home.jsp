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
		<form method="get" action="/Swap">
			<fieldset>
			<legend>Filters</legend>
				<input type="text" name="filter" placeholder="Item name contains..." value="${filter}">
				<label>Category: </label>
	            <select name="category" required>
	            	<c:choose>
	            		<c:when test="${categoryId == 0}">
	            			<option value="0" selected>All</option>
	            		</c:when>
	            		<c:otherwise>
							<option value="0">All</option>
						</c:otherwise>
	            	</c:choose>
	                <c:forEach var="category" items="${categoriesList}">
	                	<c:choose>
		                	<c:when test="${categoryId > 0 && categoryId == category.id}">
								<option value="${category.id}" selected>${category.label}</option>
							</c:when>
							<c:otherwise>
								<option value="${category.id}">${category.label}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
	            </select>
	            <input type="submit" value="Search">
        	</fieldset>
		</form>
		<c:forEach var="auction" items="${thumbnails}">
			<div class="auction-thumbnail">
				<a href="view-auction?id=${auction.id}">${auction.item}</a>
				<p>Price: ${auction.price}</p>
				<p>End date: ${auction.date}</p>
				<p>Seller: ${auction.seller}</p>
			</div>
		</c:forEach>
	</main>
	<jsp:include page="./includes/footer.jsp"/>
</body>
</html>