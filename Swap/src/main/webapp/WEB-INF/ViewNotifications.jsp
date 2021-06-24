<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>All notifications</title>
	</head>
	<body>
		<jsp:include page="./includes/header.jsp"/>
		<main id="viewUsers-main">
		<c:if test="${ADMINList != null && !ADMINList.isEmpty()}">
			<section>
				<c:forEach var="notif" items="${ADMINList}">	
						<article class="${notif.isRead()? 'read' : 'unread'}">
							<h4>From ${notif.senderName}</h3>
							<p>${notif.content}</p>
							<p>${notif.dateAndTime}</p>
						</article>
				</c:forEach>
			</section>
		</c:if>
		<c:if test="${SALEList != null && !SALEList.isEmpty()}">
			<section>
				<c:forEach var="notif" items="${SALEList}">	
						<article class="${notif.isRead()? 'read' : 'unread'}">
							<h4>${notif.auctionName} sold!</h3>
							<p>${notif.content}</p>
							<p>${notif.dateAndTime}</p>
						</article>
				</c:forEach>
			</section>
		</c:if>
		<c:if test="${BIDList != null && !BIDList.isEmpty()}">
			<section>
				<c:forEach var="notif" items="${BIDList}">	
						<article class="${notif.isRead()? 'read' : 'unread'}">
							<h4>New Bid on ${notif.auctionName}!</h3>
							<p>made by ${notif.senderName}</p>
							<p>${notif.content}</p>
							<p>${notif.dateAndTime}</p>
						</article>
				</c:forEach>
			</section>
		</c:if>			
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>