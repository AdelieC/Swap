<%@ include file="base.jsp" %>
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