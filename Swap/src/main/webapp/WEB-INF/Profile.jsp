<%@ include file="./includes/base.jsp" %>
<c:set var="isMyProfile" scope="request" value="${sessionScope.user != null && sessionScope.user == targetUser}"/>
<!DOCTYPE html>
<html>
	<style><%@include file="/css/layout.css"%></style>
	<head>
		<meta charset="UTF-8">
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
			<c:if test="${isMyProfile}">
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
				<a class="btn" href="/Swap/account/edit">Edit my profile</a>
			</c:if>
			<a class="btn cancel-btn" href="/Swap">Back to homepage</a>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>