<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>All notifications</title>
		<style type="text/css">
			section.container-notifs {
				display: none;
			}
			section.container-notifs:target {
				display: block;
			}
		</style>
	</head>
	<body>
		<jsp:include page="./includes/header.jsp"/>
		<main id="viewUsers-main">
			<nav>
				<ul>
					<h4>Notifications</h4>
					<li><a id="ADMIN" class="${ADMINUnread > 0 ? 'unread' : ''}" href="#container-notifs-admin" onclick="markAsRead(this)">Notifications : <i><span>${ADMINUnread > 0 ? ADMINUnread : '0'}</span> unread</i></a></li>
					<li><a id="WIN" class="${WINUnread > 0 ? 'unread' : ''}" href="#container-notifs-wins" onclick="markAsRead(this)">Auctions won : <i><span>${WINUnread > 0 ? WINUnread : '0'}</span> unread</i></a></li>
					<li><a id="SALE" class="${SALEUnread > 0 ? 'unread' : ''}"href="#container-notifs-sales" onclick="markAsRead(this)">Items sold : <i><span>${SALEUnread > 0 ? SALEUnread : '0'}</span> unread</i></a></li>
					<li><a id="BID" class="${BIDUnread > 0 ? 'unread' : ''}"href="#container-notifs-bids" onclick="markAsRead(this)">Bids on your auctions : <i><span>${BIDUnread > 0 ? BIDUnread : '0'}</span> unread</i></a></li>
				</ul>
				<c:if test="${conversations != null && !conversations.isEmpty()}">
					<ul>
						<h4>Conversations</h4>
						<c:forEach var="conversation" items="${conversations}">
							<li><a class="MESSAGE ${conversation.numberOfUnreadMessages > 0 ? 'unread' : ''}" id="${conversation.correspondantId}" href="#container-notifs-${conversation.correspondantName}" onclick="markAsRead(this)">${conversation.correspondantName} : <i><span>${conversation.numberOfUnreadMessages}</span> unread</i></a></li>
						</c:forEach>
					</ul>
				</c:if>
			</nav>
			<c:if test="${ADMINList != null && !ADMINList.isEmpty()}">
				<section id="container-notifs-admin" class="container-notifs">
					<c:forEach var="notif" items="${ADMINList}">	
						<article class="${notif.isRead()? 'read' : 'unread'}">
							<h4>From ${notif.senderName}</h4>
							<p>${notif.content}</p>
							<p>${notif.dateAndTime}</p>
							<button class="btn cta" id="${notif.id}" onclick="deleteNotification(this)">Delete</button>
						</article>
					</c:forEach>
				</section>
			</c:if>
			<c:if test="${WINList != null && !WINList.isEmpty()}">
				<section id="container-notifs-wins" class="container-notifs">
					<c:forEach var="notif" items="${SALEList}">	
						<article class="${notif.isRead()? 'read' : 'unread'}">
							<h4>${notif.auctionName} won!</h4>
							<p>${notif.content}</p>
							<p>${notif.dateAndTime}</p>
							<button class="btn cta" id="${notif.id}" onclick="deleteNotification(this)">Delete</button>
						</article>
					</c:forEach>
				</section>
			</c:if>
			<c:if test="${SALEList != null && !SALEList.isEmpty()}">
				<section id="container-notifs-sales" class="container-notifs">
					<c:forEach var="notif" items="${SALEList}">	
						<article class="${notif.isRead()? 'read' : 'unread'}">
							<h4>${notif.auctionName} sold!</h4>
							<p>${notif.content}</p>
							<p>${notif.dateAndTime}</p>
							<button class="btn cta" id="${notif.id}" onclick="deleteNotification(this)">Delete</button>
						</article>
					</c:forEach>
				</section>
			</c:if>
			<c:if test="${BIDList != null && !BIDList.isEmpty()}">
				<section id="container-notifs-bids" class="container-notifs">
					<c:forEach var="notif" items="${BIDList}">	
						<article class="${notif.isRead()? 'read' : 'unread'}">
							<h4>New Bid on ${notif.auctionName}!</h4>
							<p>made by ${notif.senderName}</p>
							<p>${notif.content}</p>
							<p>${notif.dateAndTime}</p>
							<button class="btn cta" id="${notif.id}" onclick="deleteNotification(this)">Delete</button>
						</article>
					</c:forEach>
				</section>
			</c:if>
			<c:if test="${conversations != null && !conversations.isEmpty()}">
				<c:forEach var="conversation" items="${conversations}">
					<section id="container-notifs-${conversation.correspondantName}" class="container-notifs">
						<c:forEach var="message" items="${conversation.messages}">
							<article class="${message.isRead()? 'read' : 'unread'}">
								<h4>From ${message.senderName}</h4>
								<p>${message.content}</p>
								<p>${message.dateAndTime}</p>
								<c:if test="${message.senderName.equals(user.getUsername())}">
									<button class="btn cta" id="${message.id}" onclick="deleteNotification(this)">Delete</button>
								</c:if>
							</article>
						</c:forEach>
						<form action="/Swap/auction/message" method="post">
							<fieldset>
								<legend>Reply :</legend>
								<textarea rows="5" name="content"></textarea>
								<input type="hidden" name="recipientId" value="${conversation.correspondantId}" />
								<input type="submit" class="btn submit1" value="Send" />
							</fieldset>
						</form>
					</section>
				</c:forEach>
			</c:if>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
		<script>
  			function markAsRead(el) {
  				const request = new XMLHttpRequest();
  				let typeStr = '';
  				let idStr = '';
  				if(el.classList.contains('MESSAGE')) {
  					typeStr = 'type=MESSAGE';
  	  			    idStr = '&id=' + el.id;
  				} else {
  					typeStr = 'type=' + el.id;
  				}
  			    request.open("GET", "/Swap/account/notification/update?" + typeStr + idStr, true);
  			    request.send();
  			    markHtmlAsRead(el);
  			}
  			
  			function markHtmlAsRead(el) {
  			    el.classList.remove('unread');
  			    const nbOfUnreadSpan = el.getElementsByTagName('span')[0];
  			    nbOfUnreadSpan.textContent = '0';
  			}
  			
  			function deleteNotification(el) {
  				const request = new XMLHttpRequest();
  				const data = "id=" + el.id;
  			    request.open("POST", "/Swap/account/notification/delete", true);
  			  	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  			    request.send(data);
  			    el.parentNode.remove();
  			}
  		</script>
	</body>
</html>