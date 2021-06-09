package com.swap.ihm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.BidManager;
import com.swap.bo.Auction;
import com.swap.bo.Bid;

public class AuctionsList {
	private List<Auction> auctions = new ArrayList<>();
	private AuctionManager aucmng = new AuctionManager();
	private List<Bid> bids = new ArrayList<>();
	private BidManager bidmng = new BidManager();
	private int userId = 0;

	public AuctionsList() {
		try {
			this.setAuctions();
		} catch (BLLException e) {
			// TODO Show error to user
			e.printStackTrace();
		}
	}

	public AuctionsList(int userId) {
		this.userId = userId;
		try {
			this.setAuctions();
			this.setBids();
		} catch (BLLException e) {
			// TODO Show error to user
			e.printStackTrace();
		}
	}

	private void setBids() throws BLLException {
		this.bids = bidmng.getAll();
	}

	private void setAuctions() throws BLLException {
		auctions = userId == 0 ? aucmng.getAllOnGoing() : aucmng.getAll();
	}

	public List<Auction> getAllPastAuctions() {
		List<Auction> tempList = new ArrayList<>();
		for (Auction a : auctions) {
			if (a.getStatus().equals("OVER"))
				tempList.add(a);
		}
		return tempList;
	}

	public List<Auction> getAllAuctionsNotOver() {
		List<Auction> tempList = new ArrayList<>();
		for (Auction a : auctions) {
			if (!(a.getStatus().equals("OVER")))
				tempList.add(a);
		}
		return tempList;
	}

	public List<Auction> getAllOngoingAuctions() {
		List<Auction> tempList = new ArrayList<>();
		for (Auction a : auctions) {
			if (a.getStatus().equals("ONGOING"))
				tempList.add(a);
		}
		return tempList;
	}

	public List<Auction> getMyOngoingAuctions() {
		List<Auction> tempList = new ArrayList<>();
		for (Auction a : auctions) {
			if (a.getUserId() == userId && a.getStatus().equals("ONGOING"))
				tempList.add(a);
		}
		return tempList;
	}

	public List<Auction> getMyAuctions() {
		List<Auction> tempList = new ArrayList<>();
		for (Auction a : auctions) {
			if (a.getUserId() == userId)
				tempList.add(a);
		}
		return tempList;
	}

	public List<Auction> getMyPastAuctions() {
		List<Auction> tempList = new ArrayList<>();
		for (Auction a : auctions) {
			if (a.getUserId() == userId && a.getStatus().equals("OVER"))
				tempList.add(a);
		}
		return tempList;
	}

	public List<Auction> getMyFutureAuctions() {
		List<Auction> tempList = new ArrayList<>();
		for (Auction a : auctions) {
			if (a.getUserId() == userId && a.getStatus().equals("CREATED"))
				tempList.add(a);
		}
		return tempList;
	}

	public List<Auction> getMyBids() {
		List<Auction> tempList = new ArrayList<>();
		for (Auction auction : auctions) {
			bids.forEach(bid -> {
				if (bid.getAuctionId() == auction.getId() && bid.getUserId() == userId)
					tempList.add(auction);
			});
		}
		return tempList;
	}

	public List<Auction> getMyOngoingBids() {
		List<Auction> tempList = new ArrayList<>();
		for (Auction a : getMyBids()) {
			if (a.getStatus().equals("ONGOING"))
				tempList.add(a);
		}
		return tempList;
	}

	public List<Auction> getMyBidsWon() {
		List<Auction> tempList = new ArrayList<>();
		for (Auction auction : getMyBids()) {
			bids.forEach(bid -> {
				if (bid.getBidPrice() == auction.getSalePrice())
					tempList.add(auction);
			});
		}
		return tempList;
	}

	public List<Auction> getFilteredList(int categoryId, String q) {
		List<Auction> tempList = new ArrayList<>();

		if (categoryId > 0) {
			for (Auction a : auctions) {
				if (a.getCategoryId() == categoryId)
					tempList.add(a);
			}
			auctions.retainAll(tempList);
			tempList.clear();
		}
		if (q != null) {
			for (Auction a : auctions) {
				if (a.getName().contains(q))
					tempList.add(a);
			}
			auctions.retainAll(tempList);
		}
		return auctions;
	}

	public List<Auction> getFilteredList(List<Auction> auctions, Map<String, String[]> filters) {
		if (filters.get("auctFilters")[0].equals("bidsFilters")) {
			auctions = applyBidsFilters(auctions, filters);
		} else if (filters.get("auctFilters")[0].equals("myAuctionsFilters")) {
			auctions = applyMyAuctionsFilters(auctions, filters);
		}
		return auctions;
	}

	private List<Auction> applyBidsFilters(List<Auction> auctions, Map<String, String[]> filters) {
		List<Auction> tempList = auctions;
		if (filters.containsKey("all-bids-on")) {
			tempList = getAllOngoingAuctions();
			if (filters.containsKey("my-bids-won")) {
				tempList.addAll(getMyBidsWon());
			}
		} else if (filters.containsKey("my-bids-on")) {
			tempList = getMyOngoingBids();
			if (filters.containsKey("my-bids-won")) {
				tempList.addAll(getMyBidsWon());
			}
		} else if (filters.containsKey("my-bids-won")) {
			tempList = getMyBidsWon();
		}
		return tempList;
	}

	private List<Auction> applyMyAuctionsFilters(List<Auction> auctions, Map<String, String[]> filters) {
		List<Auction> tempList = auctions;
		if (filters.containsKey("my-aucts-on")) {
			tempList = getMyOngoingAuctions();
			if (filters.containsKey("my-future-aucts")) {
				tempList.addAll(getMyFutureAuctions());
			}
		} else if (filters.containsKey("my-future-aucts")) {
			tempList = getMyFutureAuctions();
			if (filters.containsKey("my-past-aucts")) {
				tempList.addAll(getMyPastAuctions());
			}
		} else if (filters.containsKey("my-past-aucts")) {
			tempList = getMyPastAuctions();
		} else {
			tempList = getMyAuctions();
		}
		return tempList;
	}

}
