<%@ include file="../includes/base.jsp" %>
<fieldset>
	<legend>Address</legend>
	<label for="street">Street (number and name)</label>
	<input type="text" pattern="^[\w\s]*$" placeholder="ex : 666 Moving Castle Street" name="street" value="${user.street}"/>
	<c:if test="${errors.containsKey('street')}" >
	   	<div class="form-error">Invalid field : ${errors.get('street')}</div>
	</c:if>
	<label for="postcode">Postcode</label>
	<input type="text" pattern="^\d{5}[-\s]?(?:\d{4})?$" placeholder="ex : 68000" name="postcode" value="${user.postcode}"/>
	<c:if test="${errors.containsKey('postcode')}" >
	   	<div class="form-error">Invalid field : ${errors.get('postcode')}</div>
	</c:if>

	<label for="city">City</label>
	<input type="text" pattern="^[\w'-,.][^0-9_!¡?÷?¿/\+=@#$%ˆ&*(){}|~<>;:\[\]]{2,30}$" placeholder="ex : Colmar" name="city" value="${user.city}"/>
	<c:if test="${errors.containsKey('city')}" >
	   	<div class="form-error">Invalid field : ${errors.get('city')}</div>
	</c:if>
</fieldset>