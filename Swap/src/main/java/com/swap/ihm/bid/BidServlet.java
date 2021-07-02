package com.swap.ihm.bid;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.BidManager;
import com.swap.bll.NotificationManager;
import com.swap.bll.UserManager;
import com.swap.bo.Auction;
import com.swap.bo.BOException;
import com.swap.bo.Bid;
import com.swap.bo.Notification;
import com.swap.bo.User;
import com.swap.ihm.AuctionStatus;
import com.swap.ihm.MotherServlet;
import com.swap.ihm.notification.NotificationType;

/**
 * Servlet implementation class BidServlet
 */
@WebServlet("/auction/bid")
public class BidServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String OUTCOME_JSP = "/WEB-INF/Outcome.jsp";
	private static final Object SUCCESS_TITLE = "Successful bid!";
	private static final Object FAILURE_TITLE = "Bid couldn't be placed...";
	private static final AuctionManager aucmng = new AuctionManager();
	private static final NotificationManager notificationM = new NotificationManager();
	private static final BidManager bidmng = new BidManager();
	private static final UserManager usmng = new UserManager();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			int offer = Integer.valueOf(request.getParameter("offer"));
			int auctionId = Integer.valueOf(request.getParameter("id"));
			Auction auction = aucmng.getById(auctionId);
			User user = ((User) session.getAttribute("user"));
			if (bidIsAuthorized(user, offer, auction)) {
				Bid bid = createBid(auction, offer, user);
				updateAuction(auction, offer);
				notifySeller(bid, auction);
				setSuccess(bid, auction, request);
			} else {
				setFailure(auction, offer, request);
			}
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendToJSP(OUTCOME_JSP, request, response);
	}

	private void notifySeller(Bid bid, Auction auction) throws BLLException, BOException {
		String content = "A new bid of " + bid.getBidPrice() + " points was placed on " + auction.getName();
		notificationM.create(
				new Notification(auction.getUserId(), bid.getUserId(), NotificationType.BID, content, auction.getId()));
	}

	private void setFailure(Auction auction, int offer, HttpServletRequest request) {
		String message = "Sorry, you're not authorized to bid " + offer + " on " + auction.getName()
				+ ". Click this link to try again : <a href='/Swap/auction/view?id=" + auction.getId()
				+ "'>Try again</a>";
		request.setAttribute("message", message);
		request.setAttribute("title", FAILURE_TITLE);
	}

	private void setSuccess(Bid bid, Auction auction, HttpServletRequest request) {
		String message = "Congratulations! You just bid " + bid.getBidPrice() + " on " + auction.getName()
				+ ". You will know if you won this auction on " + auction.getEndDate();
		request.setAttribute("message", message);
		request.setAttribute("title", SUCCESS_TITLE);
	}

	private boolean bidIsAuthorized(User user, int offer, Auction auction) throws BLLException {
		boolean auctionIsOpened = auction.getStatus().equals(AuctionStatus.ONGOING.name());
		boolean offerIsEnough = offer > auction.getSalePrice();
		boolean userHasEnough = user.getBalance() >= offer;
		return auctionIsOpened && offerIsEnough && userHasEnough;
	}

	private Bid createBid(Auction auction, int offer, User user) throws BLLException, BOException {
		Bid bid = null, previousBid;
		previousBid = bidmng.getMaxBid(auction.getId());
		bid = new Bid(user.getUserId(), auction.getId(), offer, LocalDate.now());
		bidmng.create(bid);
		System.out.println("Offer :" + offer);
		usmng.debit(user.getUserId(), offer);
		if (previousBid != null) {
			notifyPreviousBuyer(previousBid, auction);
			usmng.credit(previousBid.getUserId(), previousBid.getBidPrice());
		}
		return bid;
	}

	private void notifyPreviousBuyer(Bid previousBid, Auction auction) throws BLLException, BOException {
		String content = "Your bid of " + previousBid.getBidPrice() + " points placed on " + auction.getName()
				+ " was outbid. Want to place another one? It's not too late! <a href='/Swap/auction/view?id="
				+ auction.getId() + "'>Click here!</a>";
		notificationM.create(new Notification(previousBid.getUserId(), auction.getUserId(), NotificationType.BID,
				content, auction.getId()));
	}

	private void updateAuction(Auction auction, int offer) throws BLLException {
		auction.setSalePrice(offer);
		aucmng.update(auction);
	}
}
