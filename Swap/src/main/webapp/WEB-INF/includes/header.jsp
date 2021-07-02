<%@ include file="base.jsp" %>
<header>
	<a href="/Swap/home"><img  src="/Swap/img/logo.svg" alt="Logo" /></a>
	<div id="app-name">Swap</div>
	
	<input type="checkbox" id="menu">
   	<label id="open" class="menu-ic" for="menu"><span></span><span></span><span></span></label>
	<nav>
		<label id="close" class="menu-ic" for="menu"><img src="/Swap/img/close.png" alt="X"></label>
		<h2>Menu</h2>
		<a href="/Swap/home"><li>Home</li></a>
		<c:choose>
			<c:when test="${user != null && user.userId > 0}">
				<a href="/Swap/my-auctions"><li>My auctions</li></a>
				<a href="/Swap/auction"><li>Create an auction</li></a>
				<a href="/Swap/account/notifications"><li>Notifications</li></a>
				<a href="/Swap/account"><li>My profile</li></a>
				<c:if test="${user.isAdmin()}">
					<a href="/Swap/admin/all-users"><li>Manage all users</li></a>
					<a href="/Swap/admin/notify"><li>Notify all users</li></a>
					<a href="/Swap/admin/categories"><li>Manage categories</li></a>
				</c:if>
				<a href="/Swap/about"><li>About us</li></a>
				<jsp:include page="logoutBtn.jsp"/>
			</c:when>
			<c:otherwise>
				<a href="/Swap/about"><li>About us</li></a>
				<jsp:include page="loginForm.jsp"/>
			</c:otherwise>
		</c:choose>
	</nav>
</header>