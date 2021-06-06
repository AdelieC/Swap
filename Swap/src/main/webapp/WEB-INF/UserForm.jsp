<%@ include file="./includes/base.jsp" %>
<c:set var="user" value="${sessionScope.user}"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${user != null ? 'Register' : 'Edit Profile'}</title>
	</head>
	<body>
		<h1>${user == null && errors == null ? 'Register' : 'Edit Profile'}</h1>
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
			<jsp:include page="./includes/addressFieldset.jsp">
    			<jsp:param name="errors" value="${errors}"/>
			</jsp:include>	
			<c:if test="${user != null}" >
	   			<div>Current balance : ${user.getBalance()}</div>
			</c:if>
			<input type="submit" value="${user != null ? 'Update' : 'Register'}" />
			<c:if test="${user != null}" >
	        	<a href="/account/delete?confirm=true">Delete</a>
	        </c:if>
	        <a href="/Swap">Cancel</a>
	     	
		</form>
	</body>
</html>