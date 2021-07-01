<%@ include file="./includes/base.jsp" %>
<c:set var="isLoggedIn" scope="request" value="${user != null && user.userId > 0}"/>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>All ongoing auctions</title>
	</head>
	<body>
		<jsp:include page="./includes/header.jsp"/>
		<main id="ongoing-auctions-main">
			<form id="filters" method="GET" action="/Swap/all-auctions">
				<fieldset id="basic-filters">
					<input type="text" name="keyword" placeholder="Item name contains..." value="${search}">
					<div id="category">
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
		            </div>
		            <input type="date" name="startDate">
		            <input type="date" name="endDate">
	        	</fieldset>
	        	<input type="submit" class="btn submit1" value="Filter">
			</form>
			<section>
				<c:forEach var="auction" items="${auctionsList}">	
					<a href="/Swap/auction/view?id=${auction.id}">
						<article>
							<h4>${auction.item}</h3>
							<div class="placeholder" style="background-image: url('/Swap/resources/auctions-thumbnails/${auction.pictureName}');background-size: cover;"></div>
							<p>Price: ${auction.price}</p>
							<p>End date: ${auction.date}</p>
							<p>Seller: <i>${auction.seller}</i></p>
						</article>
					</a>
				</c:forEach>
			</section>
			<c:choose>
				<c:when test="${isLoggedIn}">
					<a href="/Swap/auction" class="btn cta" id="action"><img src="/Swap/img/create.svg" alt="Create a new auction"/></a>
				</c:when>
				<c:otherwise>
					<a href="/Swap/register" class="btn cta" id="action"><img src="/Swap/img/register.svg" alt="Register"/></a>
				</c:otherwise>
			</c:choose>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>