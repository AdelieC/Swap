<%@ include file="base.jsp" %>
<header>
	<a href="/Swap/home"><img  src="/Swap/img/logo.png" alt="Logo" /></a>
	<div id="app-name">Swap</div>
	<c:choose>
		<c:when test="${user != null && user.userId > 0}">
			<p>Welcome back ${user.username}!</p>
			<jsp:include page="logoutForm.jsp"/>
			
		</c:when>
		<c:otherwise>
			<jsp:include page="loginForm.jsp"/>
		</c:otherwise>
	</c:choose>
	<nav>
		<a href="/Swap/home">Home</a>
		<c:choose>
			<c:when test="${user != null && user.userId > 0}">
				<a href="/Swap/account">My profile</a>
				<a href="/Swap/auction">Create auction</a>
			</c:when>
			<c:otherwise>
				<a href="/Swap/register">Register</a>
			</c:otherwise>
		</c:choose>
		<a href="/Swap/about">About us</a>
	</nav>
</header>