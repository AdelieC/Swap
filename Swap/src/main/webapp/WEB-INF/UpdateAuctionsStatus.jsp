<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>Swap</title>
	</head>
	<body>
	<jsp:include page="./includes/header.jsp"/>
		<main>
			<form action="update-auctions" method="post">
				<input type="submit" value="Update auctions status">
			</form>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>