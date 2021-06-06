<header>

<c:choose>
	<c:when test = "test="${sessionScope.user == null}">
		<p>SWAP</p>
		<jsp:include page="loginForm.jsp"/>
	</c:when>
	<c:otherwise>
		<p>WELCOME ${sessionScope.user.getUsername()}</p>
		<jsp:include page="logoutForm.jsp"/>
	</c:otherwise>
</c:choose>


</c:if>
<c:if test="${sessionScope.user != null}"><p>User ${sessionScope.user.getUsername()} is logged in</p></c:if>

</header>