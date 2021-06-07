<%@ include file="base.jsp" %>
<form action="/Swap/login" method="post">
	<fieldset>
		<label for="username">UserName</label>
		<input type="text" pattern="^(?=[a-zA-Z0-9._]{6,30}$)(?!.*[_.]{2})[^_.].*[^_.]$" placeholder="" name="username" value="${username}"/>
		<label for="password">Password</label>
		<input type="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,20}$" placeholder="Pa$$w0rd" name="password" />
		<input type="hidden" name="previousPath" value="${previousPath}" />
		<input type="submit" value="Login" />
		<a href="/Swap/register">Register</a>
	</fieldset>
</form>