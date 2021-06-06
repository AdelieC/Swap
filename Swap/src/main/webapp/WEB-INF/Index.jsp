<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.user == null}"><p>User is not logged in</p></c:if>
<c:if test="${sessionScope.user != null}"><p>User ${sessionScope.user.getUsername()} is logged in</p></c:if>
<a href="/Swap/">Home</a>
<a href="/Swap/home">Home (when logged in)</a>
<a href="/Swap/login">Login</a>
<a href="/Swap/register">Register</a>
<a href="/Swap/account">My profile</a>
<a href="/Swap/account/edit">Edit my profile</a>
<a href="/Swap/account/delete">Delete my profile</a>
<a href="/Swap/account/logout">Logout</a>
<!-- <a href="/Swap/auction?${auction.id}">View auction details</a>-->
<a href="/Swap/auction/create">Create auction</a>
<a href="/Swap/auction/edit">Edit auction</a>
<a href="/Swap/auction/delete">Delete auction</a>
<!-- <a href="/Swap/seller?${user.userId}">View seller details</a>-->
</body>
</html>