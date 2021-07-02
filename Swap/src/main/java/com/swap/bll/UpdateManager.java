package com.swap.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.swap.bo.Auction;
import com.swap.bo.BOException;
import com.swap.bo.Notification;
import com.swap.ihm.auction.AuctionStatus;
import com.swap.ihm.notification.NotificationType;

public class UpdateManager {
	private BidManager bidM = new BidManager();
	private UserManager userM = new UserManager();
	private AuctionManager auctionM = new AuctionManager();
	private NotificationManager notificationM = new NotificationManager();
	private List<Auction> openableAuctions = new ArrayList<>();
	private Map<Auction, Integer[]> closableAuctionsData = new HashMap<>();
	private int nbOfStarted = 0, nbOfEnded = 0;

	public UpdateManager() {
	}

	public void doGlobalUpdate() throws BLLException, BOException {
		setOpenableAuctionsList();
		setNbOfStarted();
		setClosableAuctionsMap();
		setNbOfEnded();
		if (nbOfStarted > 0) {
			openOpenableAuctions();
		}
		if (nbOfEnded > 0) {
			closeClosableAuctions();
			creditSellers();
			notifyWinners();
			notifySellers();
		}
	}

	private void notifySellers() throws BLLException, BOException {
		for (Auction auction : closableAuctionsData.keySet()) {
			if (auction.hasReceivedBids()) {
				String content = "Congratulations! You just sold " + auction.getName() + " for "
						+ auction.getSalePrice() + " points! The buyer will contact you shortly to retrive the item.";
				int sellerId = closableAuctionsData.get(auction)[0];
				int winnerId = closableAuctionsData.get(auction)[1];
				notificationM
						.create(new Notification(winnerId, sellerId, NotificationType.SALE, content, auction.getId()));
			}
		}
	}

	private void notifyWinners() throws BLLException, BOException {
		for (Auction auction : closableAuctionsData.keySet()) {
			if (auction.hasReceivedBids()) {
				String content = "Congratulations!! You won " + auction.getName() + " for " + auction.getSalePrice()
						+ " points! Please contact the vendor to set a date and time to pick-up your item.";
				int sellerId = closableAuctionsData.get(auction)[0];
				int winnerId = closableAuctionsData.get(auction)[1];
				notificationM
						.create(new Notification(sellerId, winnerId, NotificationType.WIN, content, auction.getId()));
			}
		}
	}

	private void creditSellers() throws BLLException {
		for (Auction auction : closableAuctionsData.keySet())
			if (auction.hasReceivedBids())
				userM.credit(closableAuctionsData.get(auction)[0], auction.getSalePrice());
	}

	private void closeClosableAuctions() throws BLLException {
		for (Auction auction : closableAuctionsData.keySet())
			auctionM.updateStatus(auction.getId(), AuctionStatus.OVER.name());
	}

	private void openOpenableAuctions() throws BLLException {
		for (Auction auction : openableAuctions)
			auctionM.updateStatus(auction.getId(), AuctionStatus.ONGOING.name());
	}

	private void setNbOfEnded() {
		nbOfEnded = closableAuctionsData.size();
	}

	private void setClosableAuctionsMap() throws BLLException {
		List<Auction> tempList = auctionM.getByStatus(AuctionStatus.ONGOING.name());
		for (Auction auction : tempList) {
			if (endDateHasCome(auction))
				closableAuctionsData.put(auction, new Integer[] { getSellerId(auction), getLastBidderId(auction) });
		}
	}

	private Integer getLastBidderId(Auction auction) throws BLLException {
		return auction.hasReceivedBids() ? bidM.getMaxBid(auction.getId()).getUserId() : 0;
	}

	private Integer getSellerId(Auction auction) {
		return auction.getUserId();
	}

	private boolean endDateHasCome(Auction auction) {
		return auction.getEndDate().isBefore(LocalDate.now()) || auction.getEndDate().isEqual(LocalDate.now());
	}

	private void setNbOfStarted() {
		nbOfStarted = openableAuctions.size();
	}

	private void setOpenableAuctionsList() throws BLLException {
		List<Auction> tempList = auctionM.getByStatus(AuctionStatus.CREATED.name());
		for (Auction auction : tempList) {
			if (startDateHasCome(auction))
				openableAuctions.add(auction);
		}
	}

	private boolean startDateHasCome(Auction auction) {
		return auction.getStartDate().isBefore(LocalDate.now()) || auction.getStartDate().isEqual(LocalDate.now());
	}

	public int getNbOfStarted() {
		return nbOfStarted;
	}

	public int getNbOfEnded() {
		return nbOfEnded;
	}

}
