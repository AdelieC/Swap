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
	<!-- Include header -->
	<!-- TO DELETE --><h1>HOMEPAGE</h1>
	<main>
	
		<form method="post" action="<% request.getServletContext().getContextPath(); %>">
			<fieldset>
			<legend>Filters</legend>
				<input type="text" name="filter" placeholder="Item name contains..." value="${filter}">
				<label>Category: </label>
	            <select name="category" required>
	            	<c:choose>
	            		<c:when test="${empty categoryId}">
	            			<option value="0" selected>All</option>
	            		</c:when>
	            		<c:otherwise>
							<option value="0">All</option>
						</c:otherwise>
	            	</c:choose>
	                <c:forEach var="category" items="${categoriesList}">
	                	<c:choose>
		                	<c:when test="${!empty categoryId && categoryId == category.id}">
								<option value="${category.id}" selected>${category.label}</option>
							</c:when>
							<c:otherwise>
								<option value="${category.id}">${category.label}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
	            </select>
	            <input type="submit" value="Search">
        	</fieldset>
		</form>
		<c:forEach var="auction" items="${thumbnails}">
			<fieldset class="auction-thumbnail">
				<a href="">${auction.item}</a>
				<p>Price: ${auction.price}</p>
				<p>End date: ${auction.date}</p>
				<p>Seller: ${auction.seller}</p>
			</fieldset>
		</c:forEach>
		
	
	</main>
	
</body>
</html>