<%@ include file="base.jsp" %>
<header>
	<a href=""><img  src="./img/logo.png" alt="Logo" /></a>
	<c:choose>
		<c:when test="${sessionScope.user == null}">
			<p>SWAP</p>
			<jsp:include page="loginForm.jsp"/>
		</c:when>
		<c:otherwise>
			<p>WELCOME ${sessionScope.user.getUsername()}</p>
			<jsp:include page="logoutForm.jsp"/>
		</c:otherwise>
	</c:choose>
</header>