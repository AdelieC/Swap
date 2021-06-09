<%@ include file="../includes/base.jsp" %>
<nav>
	<a href="/Swap/home">Home</a>
	<c:choose>
		<c:when test="${user != null && user.userId > 0}">
			<a href="/Swap/account">My profile</a>
			<a href="/Swap/auction/create">Create auction</a>
		</c:when>
		<c:otherwise>
			<a href="/Swap/register">Register</a>
		</c:otherwise>
	</c:choose>
	<a href="/Swap/about">About us</a>
</nav>