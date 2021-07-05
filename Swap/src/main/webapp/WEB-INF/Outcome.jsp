<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>${title}</title>
	</head>
	<body>
	<jsp:include page="./includes/header.jsp"/>
		<main>
			<h1>${title}</h1>
			<p>${message}</p>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>