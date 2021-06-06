<%@ include file="./includes/base.jsp" %>
<c:set var="isMyProfile" scope="request" value="${sessionScope.user != null && sessionScope.user == user}"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>My profile</title>
	</head>
	<body>
		<h1>${isMyProfile ? 'My Profile' : user.getUsername().concat("'s profile")}</h1>
		<section>
			<h2>Credentials</h2>
			
			<div>UserName</div>
			<div>${user.getUsername()}</div>
			<c:if test="${isMyProfile}">
				<div>My balance</div>
				<div>${user.getBalance()}</div>
			</c:if>
		</section>
		<c:if test="${isMyProfile}">
			<section>
				<h2>Informations</h2>
				
				<div>Last name</div>
				<div>${user.getLastName()}</div>
			
				<div>First name</div>
				<div>${user.getFirstName()}</div>
			
				<div>Email</div>
				<div>${user.getEmail()}</div>
			
				<div>Telephone</div>
				<div>${user.getTelephone()}</div>
			</section>
			<jsp:include page="./includes/addressSection.jsp">
	    			<jsp:param name="user" value="${user}"/>
			</jsp:include>
		<a class="btn" href="/Swap/account/edit">Edit my profile</a>
		</c:if>
	</body>
</html>