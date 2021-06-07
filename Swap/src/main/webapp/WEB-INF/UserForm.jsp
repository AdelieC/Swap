<%@ include file="./includes/base.jsp" %>
<c:set var="user" scope="request" value="${sessionScope.user == null ? tempUser : sessionScope.user}"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${user.getUserId() > 0 ? 'Register' : 'Edit Profile'}</title>
	</head>
	<body>
	<jsp:include page="./includes/header.jsp"/>
		<main>
			<h1>${user.getUserId() > 0 ? 'Edit Profile' : 'Register'}</h1>
			<form action="/Swap/register" method="post">
				<fieldset>
					<legend>Identity</legend>
					
					<label for="username">UserName</label>
					<input type="text" name="username" value="${user.getUsername()}"/>
					<c:if test="${errors.containsKey('username')}" >
		   				<div class="form-error">Invalid field : ${errors.get('username')}</div>
					</c:if>
					<label for="password1">Password</label>
					<input type="password" name="password1" value="${user.getPassword()}"/>
					<label for="password2">Confirm password</label>
					<input type="password" name="password2" value="${user.getPassword()}"/>
					<c:if test="${errors.containsKey('password')}" >
		   				<div class="form-error">Invalid field : ${errors.get('password')}</div>
					</c:if>
				</fieldset>
				<fieldset>
					<legend>Informations</legend>
					<label for="lastName">Last name</label>
					<input type="text" name="lastName" value="${user.getLastName()}"/>
					<c:if test="${errors.containsKey('lastName')}" >
		   				<div class="form-error">Invalid field : ${errors.get('lastName')}</div>
					</c:if>
					<label for="firstName">First name</label>
					<input type="text" name="firstName" value="${user.getFirstName()}"/>
					<c:if test="${errors.containsKey('firstName')}" >
		   				<div class="form-error">Invalid field : ${errors.get('firstName')}</div>
					</c:if>
					<label for="email">Email</label>
					<input type="email" name="email" value="${user.getEmail()}"/>
					<c:if test="${errors.containsKey('email')}" >
		   				<div class="form-error">Invalid field : ${errors.get('email')}</div>
					</c:if>
					<label for="telephone">Telephone</label>
					<input type="tel" name="telephone" value="${user.getTelephone()}"/>
					<c:if test="${errors.containsKey('telephone')}" >
		   				<div class="form-error">Invalid field : ${errors.get('telephone')}</div>
					</c:if>
				</fieldset>
				<jsp:include page="./includes/addressFieldset.jsp"/>
				<c:if test="${user.getUserId() > 0}" >
		   			<div>Current balance : ${user.getBalance()}</div>
				</c:if>
				<input type="submit" value="${user.getUserId() > 0 ? 'Update' : 'Register'}" />
		        <a href="/Swap">Cancel</a>
			</form>
			<c:if test="${user.getUserId() > 0}" >
				<jsp:include page="./includes/deleteAccount.jsp"/>
		    </c:if>
		    <jsp:include page="./includes/footer.jsp"/>
		</main>
	</body>
</html>