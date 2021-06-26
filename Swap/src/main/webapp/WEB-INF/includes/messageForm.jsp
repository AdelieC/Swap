<%@ include file="base.jsp" %>
<input type="checkbox" id="message-box">
<label id="show" class="btn cta" for="message-box">Write a message</label>
	
<form action="/Swap/auction/message" method="post">
<label id="hide" class="btn cta" for="message-box">Cancel</label>
	<fieldset>
		<legend>Your message</legend>
		<label for="content">Content :</label>
		<textarea rows="5" name="content"></textarea>
		<input type="hidden" name="recipientId" value="${recipientId}" />
		<c:if test="${auction != null}">
			<input type="hidden" name="auctionId" value="${auction.id}" />
			<input type="hidden" name="auctionName" value="${auction.name}" />
		</c:if>
		<input type="submit" class="btn submit1" value="Send" />
	</fieldset>
</form>