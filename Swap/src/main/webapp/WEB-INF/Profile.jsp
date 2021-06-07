<%@ include file="./includes/base.jsp" %>
<c:set var="isMyProfile" scope="request" value="${sessionScope.user != null && sessionScope.user == targetUser}"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>My profile</title>
	</head>
	<body>
	<jsp:include page="./includes/header.jsp"/>
		<main>
			<h1>${isMyProfile ? 'My Profile' : targetUser.username.concat("'s profile")}</h1>
			<section>
				<h2>Credentials</h2>
				
				<div>UserName</div>
				<div>${targetUser.username}</div>
				<c:if test="${isMyProfile}">
					<div>My balance</div>
					<div>${targetUser.balance}</div>
				</c:if>
			</section>
			<c:if test="${isMyProfile}">
				<section>
					<h2>Informations</h2>
					
					<div>Last name</div>
					<div>${targetUser.lastName}</div>
				
					<div>First name</div>
					<div>${targetUser.firstName}</div>
				
					<div>Email</div>
					<div>${targetUser.email}</div>
				
					<div>Telephone</div>
					<div>${targetUser.telephone}</div>
				</section>
				<jsp:include page="./includes/addressSection.jsp"/>
			<a class="btn" href="/Swap/account/edit">Edit my profile</a>
			</c:if>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>