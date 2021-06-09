<%@ include file="base.jsp" %>
<header>
	<a href="/Swap/home"><img  src="/Swap/img/logo.png" alt="Logo" /></a>
	<div id="app-name">Swap</div>
	<c:choose>
		<c:when test="${user == null || !(user.userId > 0)}">
			<jsp:include page="loginForm.jsp"/>
		</c:when>
		<c:otherwise>
			<p>Welcome back ${user.username}!</p>
			<jsp:include page="logoutForm.jsp"/>
		</c:otherwise>
	</c:choose>
	<jsp:include page="nav.jsp"/>
</header>