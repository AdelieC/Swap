<%@ include file="base.jsp" %>
<form action="/Swap/login" method="post">
	<fieldset>
		<label for="username">Username</label>
		<input type="text" name="username" value="${username}"/>
		<label for="password">Password</label>
		<input type="password" name="password" />
		<input type="hidden" name="previousPath" value="${previousPath}" />
		<input type="submit" class="btn submit1" value="Login" />
		<a class="btn cta" href="/Swap/register">Register</a>
	</fieldset>
</form>