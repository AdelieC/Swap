<%@ include file="/WEB-INF/includes/base.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${sessionScope.user == null ? 'Register' : 'Edit Profile'}</title>
</head>
<body>
	<h1>${sessionScope.user == null ? 'Register' : 'Edit Profile'}</h1>
	<form action="userForm" method="post">
		<fieldset>
			<legend>Identity</legend>
			
			<label for="username">UserName</label>
			<input type="text" name="username" value="${username}"/>
			
			<label for="password1">Password</label>
			<input type="password" name="password1" />
			
			<label for="password2">Confirm password</label>
			<input type="password" name="password2" />
		</fieldset>
		<fieldset>
			<legend>Informations</legend>
			<label for="lastname">Last name</label>
			<input type="text" name="lastName" value="${lastname}"/>
		
			<label for="firstname">First name</label>
			<input type="text" name="firstname" value="${firstname}"/>
		
			<label for="email">Email</label>
			<input type="email" name="email" value="${email}"/>
		
			<label for="telephone">Telephone</label>
			<input type="tel" name="telephone" value="${telephone}"/>
		</fieldset>
		<fieldset>
			<legend>Address</legend>
		
			<label for="street">Street (number and name)</label>
			<input type="text" name="street" value="${street}"/>

			<label for="postcode">Postcode</label>
			<input type="text" name="postcode" value="${postcode}"/>
		
			<label for="city">City</label>
			<input type="text" name="city" value="${city}"/>
		</fieldset>
		<input type="submit" value="${true ? 'Update' : 'Register'}" />
		<c:choose>
        	<c:when test = "${salary <= 0}">
           		<a href="/Swap">Cancel</a>
         	</c:when>
         	<c:otherwise>
             	<a href="/account/delete">Cancel</a>
         	</c:otherwise>
     	</c:choose>
	</form>
	<c:if test="" >
   
</c:if>
<c:choose>
	<p>${'test'}</p>
   
</c:choose>
</body>
</html>
</body>
</html>