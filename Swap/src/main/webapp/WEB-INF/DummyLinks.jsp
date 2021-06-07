<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="./includes/header.jsp"/>
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
<!-- <a href="/Swap/user?${user.userId}">View seller details</a>-->
</body>
</html>