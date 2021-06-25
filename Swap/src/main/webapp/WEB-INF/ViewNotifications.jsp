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
		<c:if test="${WINList != null && !WINList.isEmpty()}">
			<section>
				<c:forEach var="notif" items="${SALEList}">	
					<article class="${notif.isRead()? 'read' : 'unread'}">
						<h4>${notif.auctionName} won!</h3>
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
		<c:if test="${conversations != null && !conversations.isEmpty()}">
			<section>
				<c:forEach var="conversation" items="${conversations}">
					<article class="${conversation.hasUnread()? 'unread' : 'read'}">
						<h4>Conversation with <a href="/Swap/user?id=${conversation.correspondantId}">${conversation.correspondantName}</a></h3>
						<p>Last message : ${conversation.lastDate}</p>
						<a href="/Swap/account/messages#display-${conversation.correspondantName}">View messages</a>
					</article>
					<div id="display-${conversation.correspondantName}">
						<c:forEach var="message" items="${conversation}">
							<article class="${message.idRead()? 'read' : 'unread'}">
								<h4>From ${message.senderName}</h3>
								<p>${message.content}</p>
								<p>${message.dateAndTime}</p>
							</article>
						</c:forEach>
					</div>
				</c:forEach>
			</section>
				
		</c:if>				
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>