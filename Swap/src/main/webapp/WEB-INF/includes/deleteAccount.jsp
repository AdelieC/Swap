<form action="/Swap/account/delete" method="post">
	<input type="hidden" name="userId" value="${sessionScope.user.getUserId()}" />
	<input type="submit" name="submit" value="Delete" />
</form>