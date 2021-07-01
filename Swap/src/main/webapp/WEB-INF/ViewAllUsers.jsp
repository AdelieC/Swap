<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>Swap</title>
	</head>
	<body>
		<jsp:include page="./includes/header.jsp"/>
		<main id="viewUsers-main">
			<section>
				<c:forEach var="targetUser" items="${thumbnails}">	
					<a href="/Swap/user?id=${targetUser.userId}">
						<article class="${targetUser.isAdmin() ? 'admin' : '' }">
							<h4>${targetUser.username}</h3>
							<p>Email : ${targetUser.email}</p>
						</article>
					</a>
				</c:forEach>
			</section>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>