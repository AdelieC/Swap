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
			AuctionManager aucmng = new AuctionManager();
			Auction auction = aucmng.getById(Integer.valueOf(request.getParameter("auctionId")));
			if (!auction.isOver() && userCanDeleteAuction(auction, request)) {
				deleteAuction(auction.getId());
				deletePickUpPoint(auction.getId());
				manageAllRelatedBids(auction);
			}
			response.sendRedirect(request.getServletContext().getContextPath());
		} catch (BLLException e) {
			// TODO :send to error page 500
			e.printStackTrace();
		} catch (BOException e) {
			// TODO : error 40X
			e.printStackTrace();
		}
	}

	private boolean userCanDeleteAuction(Auction auction, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
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
			notifyBidder(bid, auction);
			bidM.delete(bid);
		}
	}

	private void notifyBidder(Bid bid, Auction auction) throws BLLException, BOException {
		NotificationManager notificationM = new NotificationManager();
		String content = "We are very sorry to inform you that the bid you made on " + auction.getName() + " on "
				+ bid.getDate() + " was annuled due to the cancellation of the auction. The sum of " + bid.getBidPrice()
				+ " points will be credited back to you immediately.";
		notificationM.create(new Notification(bid.getUserId(), auction.getUserId(), NotificationType.ADMIN, content,
				auction.getId()));
	}
}
