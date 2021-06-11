<%@ include file="./includes/base.jsp" %>
<c:set var="isMyProfile" scope="request" value="${sessionScope.user != null && sessionScope.user == targetUser}"/>
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
					<p class="display-field-label">UserName:</p>
					<p class="display-field-info">${targetUser.username}</p>
				</div>
				<div class="display-field">
					<c:if test="${isMyProfile}">
						<p class="display-field-label">My balance:</p>
						<p class="display-field-info">${targetUser.balance}</p>
					</c:if>
				</div>
			</section>
			<c:choose>
			<c:when test="${isMyProfile}">
				<section class="display-section">
					<h3>Informations</h3>
					<div class="display-field">
						<p class="display-field-label">Last name:</p>
						<p class="display-field-info">${targetUser.lastName}</p>
					</div>
					<div class="display-field">
						<p class="display-field-label">First name:</p>
						<p class="display-field-info">${targetUser.firstName}</p>
					</div>
					<div class="display-field">
						<p class="display-field-label">Email:</p>
						<p class="display-field-info">${targetUser.email}</p>
					</div>
					<div class="display-field">
						<p class="display-field-label">Telephone:</p>
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
				<a class="btn submit2" href="/Swap">Back to homepage</a>
			</c:otherwise>
			</c:choose>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>