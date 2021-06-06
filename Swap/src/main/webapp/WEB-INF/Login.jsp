<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<c:set var="delete" value="${delete != null}"/>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Log in</title>
	</head>
	<body>
	<h1>${delete ? 'Confirm your identity' : 'Login'}</h1>
		<form action="${delete ? '/account/delete' : 'login'}" method="post">
			<fieldset>
				<label for="username">UserName</label>
				<input type="text" name="username" value="${username}"/>
				
				<label for="password">Password</label>
				<input type="password" name="password" />
				<c:if test="${!delete}" >
					<input type="hidden" name="previousPath" value="${previousPath}" />
				</c:if>
				<input type="submit" value="${delete ? 'Delete account' : 'Login'}" />
			</fieldset>
		</form>
</body>
</html>