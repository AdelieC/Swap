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
			<h1>Welcome back ${user.username}!</h1>
			<c:if test="${user.isAdmin()}">
				<form action="/Swap/admin/update" method="post">
					<input class="btn submit2" type="submit" value="Launch global update">
				</form>
			</c:if>
			<section>
				<c:choose>
					<c:when test="${notifications.size() > 0}">
						<c:forEach var="notification" items="${notifications}">
							<c:choose>
								<c:when test="notification.type.equals('BID')"><li>New bid on ${notification.auctionName} - ${notification.dateAndTime}</li></c:when>
								<c:when test="notification.type.equals('ADMIN')"><li>New admin notification - ${notification.dateAndTime}</li></c:when>
								<c:when test="notification.type.equals('SALE')"><li>Good news! ${notification.name} was just sold!</li></c:when>
								<c:when test="notification.type.equals('MESSAGE')"><li>New message from ${notification.senderName} - ${notification.dateAndTime}</li></c:when>
								<c:otherwise><li>Congrats!! You won the item ${notification.auctionName}!</li></c:otherwise>
							</c:choose>
						</c:forEach>
						<a href="/Swap/account/notifications" class="btn submit2">View more</a>
					</c:when>
					<c:otherwise>
						<p>You have no new notifications and messages.</p>
						<a href="/Swap/account/notifications" class="btn submit2">Read the old ones</a>
					</c:otherwise>
				</c:choose>
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
			<a href="/Swap/auction" class="btn cta" id="action"><img src="/Swap/img/create.svg" alt="Create a new auction"/></a>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>