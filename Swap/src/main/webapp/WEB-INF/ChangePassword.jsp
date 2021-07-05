<%@ include file="./includes/base.jsp" %>
<c:set var="targetUser" scope="request" value="${user}"/>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>Change password</title>
	</head>
	<body>
		<jsp:include page="./includes/header.jsp"/>
		<main>
			<h1>Change your password</h1>
			<form action="/Swap/account/change-password" method="post">
				<fieldset>
					<legend>Identity check</legend>
					<div class="form-field">
						<label for="oldPassword">Old password :</label>
						<input type="text" placeholder="Your old password" name="oldPassword"/>
					</div>
				</fieldset>
				<fieldset>
					<legend>New password</legend>
					<div class="form-field">
						<label for="password1">New password :</label>
						<input type="password" type="text" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,20}$" placeholder="ex : Pa$$w0rd" name="password1"/>
					</div>
					<div class="form-field">
						<label for="password2">Confirm new password :</label>
						<input type="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,20}$" placeholder="ex : Pa$$w0rd" name="password2"/>
					</div>
					<div class="btn-panel">
						<input type="submit" class="btn submit1" value="Change password" />
						<a class="btn submit2" href="/Swap">Cancel</a>
	    			</div>
		    	</fieldset>
			</form>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>