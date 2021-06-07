<%@ include file="base.jsp" %>
<header>
	<a href="/Swap/home"><img  src="./img/logo.png" alt="Logo" /></a>
	<div id="app-name">Swap</div>
	<c:choose>
		<c:when test="${sessionScope.user == null}">
			<jsp:include page="loginForm.jsp"/>
		</c:when>
		<c:otherwise>
			<p>WELCOME ${sessionScope.user.getUsername()}</p>
			<jsp:include page="logoutForm.jsp"/>
		</c:otherwise>
	</c:choose>
</header>