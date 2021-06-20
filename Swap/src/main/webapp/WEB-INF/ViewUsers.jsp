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
				<c:forEach var="user" items="${thumbnails}">	
					<a href="/Swap/user?id=${user.userId}">
						<article class="${user.isAdmin() ? 'admin' : '' }">
							<h4>${user.username}</h3>
							<p>Email : ${user.email}</p>
						</article>
					</a>
				</c:forEach>
			</section>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>