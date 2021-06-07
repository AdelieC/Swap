<%@ include file="base.jsp" %>
<fieldset>
	<legend>Filters</legend>
	<label for="bids">Bids</label>
	<input type="radio" name="auctFilter"  id="bids" value="bids" ${filterType != null ? (filterType.equals("bids") ? 'checked' : '') : 'checked'}>
	<fieldset>
		<input type="checkbox" name="all-bids-on"  id="all-bids-on" value="all-bids-on" ${filterList != null ? (filterList.contains("all-bids-on") ? 'checked' : '') : 'checked'}>
		<label for="all-bids-on">All ongoing bids</label>
		<input type="checkbox" name="my-bids-on"  id="my-bids-on" value="my-bids-on" ${filterList != null ? (filterList.contains("my-bids-on") ? 'checked' : '') : ''}>
		<label for="my-bids-on">My ongoing bids</label>
		<input type="checkbox" name="my-bids-won"  id="my-bids-won" value="my-bids-won" ${filterList != null ? (filterList.contains("my-bids-won") ? 'checked' : '') : ''}>
		<label for="my-bids-won">My successful bids</label>
	</fieldset>
	<label for="all">My Auctions</label>
	<input type="radio" name="auctFilter"  id="myAuctions" value="myAuctions" ${filterType != null && filterType.equals("myAuctions") ? 'checked' : ''}>
	<fieldset>
		<input type="checkbox" name="my-aucts-on"  id="my-aucts-on" value="my-aucts-on" ${filterList != null && filterList.contains("my-aucts-won") ? 'checked' : ''}>
		<label for="my-aucts-on">My ongoing auctions</label>
		<input type="checkbox" name="my-future-aucts"  id="my-future-aucts" value="my-future-aucts" ${filterList != null && filterList.contains("my-future-aucts") ? 'checked' : ''}>
		<label for="my-future-aucts">My auctions to come</label>
		<input type="checkbox" name="my-past-aucts"  id="my-past-aucts" value="my-past-aucts" ${filterList != null && filterList.contains("my-past-aucts") ? 'checked' : ''}>
		<label for="my-past-aucts">My past auctions</label>
	</fieldset>
</fieldset>