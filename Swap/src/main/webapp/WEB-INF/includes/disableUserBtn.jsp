<form method="post" action="/Swap/admin/disable-user">
	<input type="hidden" name="id" value="${targetUser.getUserId()}">
	<input type="submit" class="btn cta" name="submit" value="${targetUser.wasDisabled()? 'Enable' : 'Disable'}">
</form>