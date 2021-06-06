<%@ include file="base.jsp" %>
<form action="${delete ? '/Swap/account/delete' : 'login'}" method="post">
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