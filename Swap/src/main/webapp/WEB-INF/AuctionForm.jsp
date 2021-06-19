<%@ include file="./includes/base.jsp" %>
<c:set var="user" scope="request" value="${sessionScope.user}"/>
<!DOCTYPE html>
<html>
	<jsp:include page="./includes/basicLinks.html"/>
	<head>
		<meta charset="UTF-8">
	    <title>Swap</title>
	</head>
	<body>
	 	<jsp:include page="./includes/header.jsp"/>
	    <main id="auction-form-main">
	        <h1>${title}</h1>
	        <form id="auction-form" method="post" action="auction" enctype="multipart/form-data">
	        	<fieldset>
	        		<legend>Item informations</legend>
	        		<div class="form-field">
			            <label for="name">Item:</label>
			            <input type="text" name="name" value="${auction.name}" required>
		            </div>
		            <div class="form-field">
			            <label for="description">Description:</label>
			            <textarea class="auction-form-input" name="description" required>${auction.description}</textarea>
		            </div>
		            <div class="form-field">
			            <label for="category">Category:</label>
			            <select name="category" required>
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
		            </div>
		            <c:choose>
		            	<c:when test="${auction.id > 0}">
		            		<jsp:include page="./includes/imageSlider.jsp"/>
		            	</c:when>
		            	<c:otherwise>
		            		<div class="form-field">
			            		<label for="picture">Picture(s):</label>
			           	 		<input type="file" name="picture" multiple>
		            		</div>
		            	</c:otherwise>		           
		            </c:choose>
		            
		            <div class="form-field">
			            <label for="initial-price">Initial price:</label>
			            <input type="text" name="initial-price" value="${auction.initialPrice}" required>
		            </div>
			            <div class="form-field">
			            <label for="start-date">Start date:</label>
			            <input type="date" name="start-date" value="${auction.startDate}" min="${auction.startDate != null ? auction.startDate : minDate}" max="${maxDate}" required>
		            </div>
		            <div class="form-field">
			            <label for="end-date">End date:</label>
			            <input type="date" name="end-date" value="${auction.endDate}" min="${auction.startDate != null ? auction.startDate : minDate}" max="${maxDate}" required>
		            </div>
		            </fieldset>
	 	            <fieldset class="pup">
		            	<legend>Pick Up Point</legend>
		            	<div class="form-field">
				            <label for="street">Street:</label>
				            <input type="text" name="street" value="${pickUpPoint.street}">
			            </div>
			            <div class="form-field">
				            <label for="postcode">Postcode:</label>
				            <input type="text" name="postcode" value="${pickUpPoint.postcode}">
			            </div>
			            <div class="form-field">
				            <label for="city">City:</label>
				            <input type="text" name="city" value="${pickUpPoint.city}">
			            </div>
		            </fieldset>
		            </form>
		            <div class="btn-group">
		            	<input type="hidden" form="auction-form" name="auctionId" value="${auction.getId()}">
		            	<input type="submit" form="auction-form" class="btn submit1" value="Save">
		            	<a class="btn submit2" href="/Swap">Cancel</a>
						<c:if test="${!empty auction && auction.status.equals('CREATED')}">
							<form method="post" action="/Swap/auction/cancel">
								<input type="hidden" name="auctionId" value="${auction.getId()}">
		        				<input type="submit" class="btn cta" name="cancel-auction" value="Cancel auction">
		        			</form>
	       	 			</c:if> 
	       	 		</div>
			
	    </main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>