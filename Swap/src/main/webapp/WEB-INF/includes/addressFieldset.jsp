<%@ include file="../includes/base.jsp" %>
<fieldset>
	<legend>Address</legend>
	<label for="street">Street (number and name)</label>
	<input type="text" name="street" value="${user.getStreet()}"/>
	<c:if test="${errors.containsKey('street')}" >
	   	<div class="form-error">Invalid field : ${errors.get('street')}</div>
	</c:if>
	<label for="postcode">Postcode</label>
	<input type="text" name="postcode" value="${user.getPostcode()}"/>
	<c:if test="${errors.containsKey('postcode')}" >
	   	<div class="form-error">Invalid field : ${errors.get('postcode')}</div>
	</c:if>

	<label for="city">City</label>
	<input type="text" name="city" value="${user.getCity()}"/>
	<c:if test="${errors.containsKey('city')}" >
	   	<div class="form-error">Invalid field : ${errors.get('city')}</div>
	</c:if>
</fieldset>