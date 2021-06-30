package com.swap.ihm.auction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.BidManager;
import com.swap.bll.NotificationManager;
import com.swap.bll.PickUpPointManager;
import com.swap.bll.UserManager;
import com.swap.bo.Auction;
import com.swap.bo.BOException;
import com.swap.bo.Bid;
import com.swap.bo.Notification;
import com.swap.bo.User;
import com.swap.ihm.MotherServlet;
import com.swap.ihm.notification.NotificationType;

/**
 * Servlet implementation class CancelAuctionServlet
 */
@WebServlet("/auction/cancel")
public class CancelAuctionServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String OUTCOME_JSP = "/WEB-INF/Outcome.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			AuctionManager aucmng = new AuctionManager();
			Auction auction = aucmng.getById(Integer.valueOf(request.getParameter("auctionId")));
			if (!auction.isOver() && userCanDeleteAuction(auction, user)) {
				deleteAuction(auction.getId());
				deletePickUpPoint(auction.getId());
				if (auction.hasReceivedBids())
					manageAllRelatedBids(auction);
				if (user.isAdmin())
					notifySeller(auction, user);
				setSuccess(request, auction);
			} else {
				setFailure(request);
			}
			sendToJSP(OUTCOME_JSP, request, response);
		} catch (BLLException e) {
			// TODO :send to error page 500
			e.printStackTrace();
		} catch (BOException e) {
			// TODO : error 40X
			e.printStackTrace();
		}
	}

	private void setFailure(HttpServletRequest request) {
		String message = "Auction couldn't be cancelled. Either it's already closed or you don't have permission to cancel it.";
		request.setAttribute("title", "Auction couldn't be canceled");
		request.setAttribute("message", message);
	}

	private void setSuccess(HttpServletRequest request, Auction auction) {
		String message = "Auction named " + auction.getName()
				+ " was successfully cancelled. Bidders were notified and got a refund.";
		request.setAttribute("title", "Auction was successfully canceled");
		request.setAttribute("message", message);
	}

	private void notifySeller(Auction auction, User user) throws BLLException, BOException {
		NotificationManager notificationM = new NotificationManager();
		String content = "We are very sorry to inform you that your auction " + auction.getName()
				+ " was cancelled by an administrator because it did not respect our terms of use.";
		notificationM.create(new Notification(auction.getUserId(), user.getUserId(), NotificationType.ADMIN, content));
	}

	private boolean userCanDeleteAuction(Auction auction, User user) {
		return auction.getUserId() == user.getUserId() || user.isAdmin();
	}

	private void deleteAuction(int auctionId) throws BLLException {
		AuctionManager aucmng = new AuctionManager();
		Auction auction = null;
		auction = aucmng.getById(auctionId);
		aucmng.delete(auction);
	}

	private void deletePickUpPoint(int auctionId) throws BLLException {
		PickUpPointManager pupmng = new PickUpPointManager();
		pupmng.deleteByAuctionId(auctionId);
	}

	private void manageAllRelatedBids(Auction auction) throws BLLException, BOException {
		BidManager bidM = new BidManager();
		List<Bid> bids = bidM.getByAuctionId(auction.getId());
		for (Bid bid : bids) {
			if (bid.getBidPrice() == auction.getSalePrice())
				refundLastBidder(bid);
			notifyBidder(bid, auction);
			bidM.delete(bid);
		}
	}

	private void refundLastBidder(Bid bid) throws BLLException {
		UserManager userM = new UserManager();
		userM.credit(bid.getUserId(), bid.getBidPrice());
	}

	private void notifyBidder(Bid bid, Auction auction) throws BLLException, BOException {
		NotificationManager notificationM = new NotificationManager();
		String content = "We are very sorry to inform you that the bid you made on " + auction.getName() + " on "
				+ bid.getDate() + " was annuled due to the cancellation of the auction. The sum of " + bid.getBidPrice()
				+ " points will be credited back to you immediately.";
		notificationM.create(new Notification(bid.getUserId(), auction.getUserId(), NotificationType.ADMIN, content));
	}
}
