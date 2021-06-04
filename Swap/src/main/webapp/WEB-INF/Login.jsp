<%@ include file="/WEB-INF/includes/base.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Log in</title>
	</head>
	<body>
	<h1>${true ? 'Login' : 'Login failed'}</h1>
		<form action="login" method="post">
			<fieldset>
				<label for="username">UserName</label>
				<input type="text" name="username" value="${username}"/>
				
				<label for="password">Password</label>
				<input type="password" name="password" />
				<input type="hidden" name="previousPath" value="${previousPath}" />
				<input type="submit" value="Login" />
			</fieldset>
		</form>
</body>
</html>