<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Swap</title>
</head>
<body>

	<main>
		<h1>View bidders:</h1>
		<c:forEach var="bidder" items="${biddersList}">
		<fieldset>
			<p>Username: ${bidder.username}      Bid: ${bidder.bid}</p>
		</fieldset>
		</c:forEach>
	</main>

</body>
</html>