<%@ include file="./includes/base.jsp" %>
<c:set var="targetUser" scope="request" value="${user}"/>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>Global notification</title>
	</head>
	<body>
		<jsp:include page="./includes/header.jsp"/>
		<main>
			<h1>Create a global notification</h1>
			<form action="/Swap/admin/notify" method="post">
				<fieldset>
					<legend>Your message</legend>
					<label for="content">Content :</label>
					<textarea rows="5" name="content"></textarea>
					<input type="submit" class="btn submit1" value="Send to all" />
					<a href="/Swap/" class="btn submit1">Cancel</a>
				</fieldset>
			</form>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>