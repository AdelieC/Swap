<%@ include file="base.jsp" %>
<fieldset>
	<legend>Filters</legend>
	<label for="bids">Bids</label>
	<input type="radio" name="auctFilters"  id="bids" value="bidsFilters">
	<fieldset>
		<input type="checkbox" name="all-bids-on"  id="all-bids-on" value="all-bids-on">
		<label for="all-bids-on">All ongoing bids</label>
		<input type="checkbox" name="my-bids-on"  id="my-bids-on" value="my-bids-on">
		<label for="my-bids-on">My ongoing bids</label>
		<input type="checkbox" name="my-bids-won"  id="my-bids-won" value="my-bids-won">
		<label for="my-bids-won">My successful bids</label>
	</fieldset>
	<label for="all">My Auctions</label>
	<input type="radio" name="auctFilters"  id="myAuctions" value="myAuctionsFilters">
	<fieldset>
		<input type="checkbox" name="my-aucts-on"  id="my-aucts-on" value="my-aucts-on">
		<label for="my-aucts-on">My ongoing auctions</label>
		<input type="checkbox" name="my-future-aucts"  id="my-future-aucts" value="my-future-aucts">
		<label for="my-future-aucts">My auctions to come</label>
		<input type="checkbox" name="my-past-aucts"  id="my-past-aucts" value="my-past-aucts">
		<label for="my-past-aucts">My past auctions</label>
	</fieldset>
</fieldset>