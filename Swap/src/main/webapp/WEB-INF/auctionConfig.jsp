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
        <!-- Include header -->
        <h1>${title}</h1>
        <form method="post" action="auction">
        	<fieldset>
	            <label class="auction-form-label" for="name">Item:</label>
	            <input class="auction-form-input" type="text" name="name" value="${auction.name}" required>
	            <label class="auction-form-label" for="description">Description:</label>
	            <textarea class="auction-form-input" name="description" required>${auction.description}</textarea>
	            <label class="auction-form-label" for="category">Category:</label>
	            <select name="category" required>
	                <c:forEach var="category" items="${categoriesList}">
	                	<c:if test="${!empty categoryId && categoryId == category.id}">
							<option value="${category.id}" selected>${category.label}</option>
						</c:if>
						<c:otherwise>
							<option value="${category.id}">${category.label}</option>
						</c:otherwise>
					</c:forEach>
	            </select>
	            <label class="auction-form-label" for="picture">Item picture</label>
	            <input class="auction-form-input" type="file" name="picture">
	            <label class="auction-form-label" for="initial-price">Initial price:</label>
	            <input class="auction-form-input" type="text" name="initial-price" value="${auction.initialPrice}" required>
	            <label class="auction-form-label" for="start-date">Start date:</label>
	            <input class="auction-form-input" type="date" name="start-date" value="${auction.startDate}" required>
	            <label class="auction-form-label" for="end-date">End date:</label>
	            <input class="auction-form-input" type="date" name="end-date" value="${auction.endDate}" required>
	            <fieldset>
	            	<legend>Pick Up Point</legend>
		            <label class="auction-form-label" for="street">Street:</label>
		            <input class="auction-form-input" type="text" name="street" value="${pickUpPoint.street}">
		            <label class="auction-form-label" for="postcode">Postcode:</label>
		            <input class="auction-form-input" type="text" name="postcode" value="${pickUpPoint.postcode}">
		            <label class="auction-form-label" for="city">City:</label>
		            <input class="auction-form-input" type="text" name="city" value="${pickUpPoint.postcode}">
	            </fieldset>         
	            <input type="submit" value="Save">
            </fieldset>
        </form>
        <!-- TODO --><a href="">Back</a>
<!--        <form>
        	<input type="submit" name="cancel-auction" value="Cancel auction">
        </form> -->
    </main>

</body>
</html>