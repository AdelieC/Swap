<%@ include file="base.jsp" %>
<fieldset>
	<legend>Filters</legend>
	<label for="bids">All Auctions</label>
	<input type="radio" name="auctionsFilters"  id="allAuctions" value="allAuctionsFilters" ${auctionsFilters == null || auctionsFilters.equals("allAuctionsFilters") ? 'checked' : ''}>
	<fieldset>
		<input type="checkbox" name="allAuctsOn"  id="allAuctsOn" value="allAuctsOn" ${auctionsFilters == null || allAuctsOn != null ? 'checked' : ''}>
		<label for="allBidsOn">All ongoing auctions</label>
		<input type="checkbox" name="myBidsOn"  id="myBidsOn" value="myBidsOn" ${myBidsOn != null && auctionsFilters.equals("allAuctionsFilters") ? 'checked' : ''}>
		<label for="myBidsOn">My ongoing bids</label>
		<input type="checkbox" name="myBidsWon"  id="myBidsWon" value="myBidsWon" ${myBidsWon != null && auctionsFilters.equals("allAuctionsFilters")? 'checked' : ''}>
		<label for="myBidsWon">My successful bids</label>
	</fieldset>
	<label for="all">My Auctions</label>
	<input type="radio" name="auctionsFilters"  id="myAuctions" value="myAuctionsFilters" ${auctionsFilters != null && auctionsFilters.equals("myAuctionsFilters") ? "checked" : ""}>
	<fieldset>
		<input type="checkbox" name="myAuctsOn"  id="myAuctsOn" value="myAuctsOn" ${myAuctsOn != null && auctionsFilters.equals("myAuctionsFilters") ? 'checked' : ''}>
		<label for="my-aucts-on">My ongoing auctions</label>
		<input type="checkbox" name="myFutureAucts"  id="myFutureAucts" value="myFutureAucts" ${myFutureAucts != null && auctionsFilters.equals("myAuctionsFilters") ? 'checked' : ''}>
		<label for="my-future-aucts">My auctions to come</label>
		<input type="checkbox" name="myPastAucts"  id="myPastAucts" value="myPastAucts" ${myPastAucts != null && auctionsFilters.equals("myAuctionsFilters") ? 'checked' : ''}>
		<label for="my-past-aucts">My past auctions</label>
	</fieldset>
</fieldset>