<%@ include file="/WEB-INF/includes/base.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.user == null}"><p>User is not logged in</p></c:if>
<c:if test="${sessionScope.user != null}"><p>User ${sessionScope.user.getUsername()} is logged in</p></c:if>
<a href="/Swap/login">Login</a>
<a href="/Swap/register">Register</a>
<a href="/Swap/account/my-profile">My profile</a>
<a href="/Swap/account/my-profile/edit">Edit my profile</a>
</body>
</html>