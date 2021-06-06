<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>My profile</title>
	</head>
	<body>
		<h1>My Profile</h1>
		<section>
			<h2>Credentials</h2>
			
			<div>UserName</div>
			<div>${user.getUsername()}</div>
			
			<div>My balance</div>
			<div>${user.getBalance()}</div>
			
			<a>Change password</a>
		</section>
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
	</body>
</html>