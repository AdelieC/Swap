<%@ include file="./includes/base.jsp" %>
<c:set var="isLoggedIn" scope="request" value="${user != null && user.userId > 0}"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="/Swap/css/index.css" rel="stylesheet">
		<title>Swap</title>
	</head>
	<body>
		<jsp:include page="./includes/header.jsp"/>
		<main>
			<form method="${isLoggedIn ? 'post' : 'get'}" action="/Swap/home">
				<c:if test="${isLoggedIn}">
					<jsp:include page="./includes/loggedInPanel.jsp"/>
				</c:if>
				<fieldset>
				<legend>Search</legend>
					<input type="text" name="search" placeholder="Item name contains..." value="${search}">
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
		            <input type="submit" value="Filter">
	        	</fieldset>
			</form>
			<section>
				<c:forEach var="auction" items="${thumbnails}">	
					<article class="auction-thumbnail">
						<a href="/Swap/auction/view?id=${auction.id}">${auction.item}</a>
						<a href="/Swap/auction/view?id=${auction.id}"><div class="placeholder" style="background: center url('/Swap/img/dummy.jpg');"></div></a>
						<p>Price: ${auction.price}</p>
						<p>End date: ${auction.date}</p>
						<p>Seller: ${auction.seller}</p>
					</article>
			</c:forEach>
			</section>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>