<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>Swap</title>
	</head>
	<body>
		<jsp:include page="./includes/header.jsp"/>
		<main id="visitor-home-main">
			<section id="hero">
				<h4>Tired of your old things?</h4>
				<h5>Sign up to swap them for points and get new ones!</h5>
				<a href="/Swap/register" class="btn cta">Register</a>
			</section>
			<form id="filters" method="GET" action="/Swap/home">
				<fieldset id="basic-filters">
					<input type="text" name="keyword" placeholder="Item name contains..." value="${keyword}">
					<div id="category">
						<label>Category: </label>
			            <select name="categoryId" required>
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
		            <label for="startDate">Starts after :</label>
		            <input type="date" name="startDate" value="${startDate}">
		            <label for="startDate">Ends before :</label>
		            <input type="date" name="endDate" value="${endDate}">
		            <input type="submit" class="btn submit1" value="Filter">
	        	</fieldset>
			</form>
			<section>
				<c:forEach var="auction" items="${auctionsList}">	
					<a href="/Swap/auction/view?id=${auction.id}">
						<article>
							<h4>${auction.item}</h3>
							<div class="placeholder" style="background-image: url('/Swap/resources/auctions-thumbnails/${auction.pictureName}');background-size: cover;"></div>
							<p>Price: ${auction.price}</p>
							<p>Started : ${auction.startDate}</p>
							<p>Ends : ${auction.endDate}</p>
							<p>Seller: <i>${auction.seller}</i></p>
						</article>
					</a>
				</c:forEach>
			</section>
			<a href="/Swap/register" class="btn cta" id="action"><img src="/Swap/img/register.svg" alt="Register"/></a>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>