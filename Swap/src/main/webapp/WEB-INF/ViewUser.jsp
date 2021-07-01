<%@ include file="./includes/base.jsp" %>
<c:set var="isMyProfile" scope="request" value="${sessionScope.user != null && sessionScope.user == targetUser}"/>
<c:set var="recipientId" scope="request" value="${targetUser.userId}"/>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>My profile</title>
	</head>
	<body>
	<jsp:include page="./includes/header.jsp"/>
		<main id="profile-main">
			<h1>${isMyProfile ? 'My Profile' : targetUser.username.concat("'s profile")}</h1>
			<section class="display-section">
				<h3>Credentials</h3>
				<div class="display-field">
					<p class="display-field-label">UserName :</p>
					<p class="display-field-info">${targetUser.username}</p>
				</div>
				<div class="display-field">
					<c:if test="${isMyProfile}">
						<p class="display-field-label">My balance :</p>
						<p class="display-field-info">${targetUser.balance}</p>
					</c:if>
				</div>
			</section>
			<c:choose>
				<c:when test="${isMyProfile}">
					<section class="display-section">
						<h3>Informations</h3>
						<div class="display-field">
							<p class="display-field-label">Last name :</p>
							<p class="display-field-info">${targetUser.lastName}</p>
						</div>
						<div class="display-field">
							<p class="display-field-label">First name:</p>
							<p class="display-field-info">${targetUser.firstName}</p>
						</div>
						<div class="display-field">
							<p class="display-field-label">Email :</p>
							<p class="display-field-info">${targetUser.email}</p>
						</div>
						<div class="display-field">
							<p class="display-field-label">Telephone :</p>
							<p class="display-field-info">${targetUser.telephone}</p>
						</div>
					</section>
					<jsp:include page="./includes/addressSection.jsp"/>
					<div class="center">
						<a class="btn submit1" href="/Swap/account/edit">Edit my profile</a>
						<a class="btn submit2" href="/Swap">Back to homepage</a>
					</div>
				</c:when>
				<c:otherwise>
					<c:if test="${user.isAdmin()}">
						<section class="display-section">
							<h3>Contact informations</h3>
							<div class="display-field">
								<p class="display-field-label">Email :</p>
								<p class="display-field-info">${targetUser.email}</p>
							</div>
							<div class="display-field">
								<p class="display-field-label">Telephone :</p>
								<p class="display-field-info">${targetUser.telephone}</p>
							</div>
						</section>
					</c:if>
					<section>
						<h3>Ongoing auctions</h3>
						<c:forEach var="auction" items="${thumbnails}">	
							<a href="/Swap/auction/view?id=${auction.id}">
								<article>
									<h4>${auction.item}</h3>
									<div class="placeholder" style="background-image: url('/Swap/resources/auctions-thumbnails/${auction.pictureName}');background-size: cover;"></div>
									<p>Price: ${auction.price}</p>
									<p>End date: ${auction.date}</p>
								</article>
							</a>
						</c:forEach>
					</section>
					<c:if test="${user.isAdmin() && !user.wasDisabled()}">
						<div class="center">
							<jsp:include page="./includes/deleteAccountBtn.jsp"/>
							<jsp:include page="./includes/disableUserBtn.jsp"/>
						</div>
					</c:if>
					<a class="btn submit2" href="/Swap">Back to homepage</a>
				</c:otherwise>
			</c:choose>
			<c:if test="${!isMyProfile && !user.wasDisabled()}">
				<jsp:include page="./includes/messageForm.jsp"/>
			</c:if>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>