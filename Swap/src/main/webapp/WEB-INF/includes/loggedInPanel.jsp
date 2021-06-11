<%@ include file="base.jsp" %>
<input type="checkbox" id="show-filters">
<label for="show-filters" class="btn submit2"><span id="show">Show more</span><span id="hide">Hide</span> filters</label>
<fieldset id="more-filters">
	<div>
		<label for="allAuctions" class="choice">All Auctions</label>
		<input type="radio" form="filters" name="auctionsFilters"  id="allAuctions" value="allAuctionsFilters" ${auctionsFilters == null || auctionsFilters.equals("allAuctionsFilters") ? 'checked' : ''}>
		<fieldset>
			<div>
				<input type="checkbox" form="filters" name="allAuctsOn"  id="allAuctsOn" value="allAuctsOn" ${auctionsFilters == null || allAuctsOn != null ? 'checked' : ''}>
				<label for="allBidsOn">All ongoing auctions</label>
			</div>
			<div>	
				<input type="checkbox" form="filters" name="myBidsOn"  id="myBidsOn" value="myBidsOn" ${myBidsOn != null && auctionsFilters.equals("allAuctionsFilters") ? 'checked' : ''}>
				<label for="myBidsOn">My ongoing bids</label>
			</div>
			<div>	
				<input type="checkbox" form="filters" name="myBidsWon"  id="myBidsWon" value="myBidsWon" ${myBidsWon != null && auctionsFilters.equals("allAuctionsFilters")? 'checked' : ''}>
				<label for="myBidsWon">My successful bids</label>
			</div>
		</fieldset>
	</div>
	<div>
		<label for="myAuctions" class="choice">My Auctions</label>
		<input type="radio" form="filters" name="auctionsFilters"  id="myAuctions" value="myAuctionsFilters" ${auctionsFilters != null && auctionsFilters.equals("myAuctionsFilters") ? "checked" : ""}>
		<fieldset>
			<div>
				<input type="checkbox" form="filters" name="myAuctsOn"  id="myAuctsOn" value="myAuctsOn" ${myAuctsOn != null && auctionsFilters.equals("myAuctionsFilters") ? 'checked' : ''}>
				<label for="my-aucts-on">My ongoing auctions</label>
			</div>
			<div>	
				<input type="checkbox" form="filters" name="myFutureAucts"  id="myFutureAucts" value="myFutureAucts" ${myFutureAucts != null && auctionsFilters.equals("myAuctionsFilters") ? 'checked' : ''}>
				<label for="my-future-aucts">My auctions to come</label>
			</div>
			<div>
				<input type="checkbox" form="filters" name="myPastAucts"  id="myPastAucts" value="myPastAucts" ${myPastAucts != null && auctionsFilters.equals("myAuctionsFilters") ? 'checked' : ''}>
				<label for="my-past-aucts">My past auctions</label>
			</div>
		</fieldset>
	</div>
</fieldset>