package com.swap.ihm.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
import com.swap.ihm.FormCleaner;
import com.swap.ihm.IHMException;
import com.swap.ihm.MotherServlet;
import com.swap.ihm.notification.NotificationType;

/**
 * Servlet handling logout and delete user
 */
@WebServlet(urlPatterns = { "/account/logout", "/account/delete" })
public class ManageExitServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String OUTCOME_JSP = "/WEB-INF/Outcome.jsp";
	private static BidManager bidM = new BidManager();
	private static AuctionManager auctionM = new AuctionManager();
	private static PickUpPointManager pupM = new PickUpPointManager();
	private static UserManager userM = new UserManager();
	private static NotificationManager notificationM = new NotificationManager();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getRequestURI().contains("logout")) {
			request.setAttribute("message", "You successfully logged out. Hope to see you again soon!");
			request.setAttribute("title", "See you!");
		} else {
			request.setAttribute("message", "User account was successfully deleted.");
			request.setAttribute("title", "Account deleted");
		}
		sendToJSP(OUTCOME_JSP, request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			if (!userIsLoggedIn(request))
				throw new IHMException("Action not permitted");
			if ("Delete".equals(request.getParameter("submit"))) {
				deleteUser(request, session);
			}
			session.setAttribute("user", null);
			session.invalidate();
			doGet(request, response);
		} catch (BLLException e) {
			// TODO send to error page = 500
			e.printStackTrace();
		} catch (IHMException e) {
			// TODO send to error page = action not permitted
			e.printStackTrace();
		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteUser(HttpServletRequest request, HttpSession session) throws BLLException, BOException {
		int userId = FormCleaner.cleanId(request.getParameter("userId"));
		if (((User) session.getAttribute("user")).getUserId() == userId
				|| ((User) session.getAttribute("user")).isAdmin()) {
			deleteAllRelatedToUser(userId);
			userM.delete(userId);
		}
	}

	private void deleteAllRelatedToUser(int userId) throws BLLException, BOException {
		notificationM.deleteByUserId(userId);
		manageAuctionsToDelete(userId);
		manageBidsToDelete(userId);
	}

	private void manageAuctionsToDelete(int userId) throws BLLException {
		List<Auction> auctionsToDelete = auctionM.getByUserId(userId);
		if (auctionsToDelete.size() > 0) {
			for (Auction auction : auctionsToDelete) {
				refundLastBidder(auction, bidM, userM);
				bidM.deleteByAuctionId(auction.getId());
				pupM.deleteByAuctionId(auction.getId());
				auctionM.delete(auction);
			}
		}
	}

	private void manageBidsToDelete(int userId) throws BLLException, BOException {
		List<Bid> bidsToDelete = bidM.getByUserId(userId);
		if (bidsToDelete.size() > 0) {
			for (Bid bid : bidsToDelete) {
				Auction auction = auctionM.getById(bid.getAuctionId());
				bidM.delete(bid);
				if (bid.getBidPrice() == auction.getSalePrice()) {
					Bid previousBid = bidM.getMaxBid(bid.getAuctionId());
					userM.debit(previousBid.getUserId(), previousBid.getBidPrice());
					auction.setSalePrice(previousBid.getBidPrice());
					auctionM.update(auction);
					notifyPreviousBidder(previousBid, auction, userM);
				}
			}
		}
	}

	private void notifyPreviousBidder(Bid previousBid, Auction auction, UserManager userM)
			throws BLLException, BOException {
		NotificationManager notificationM = new NotificationManager();
		String content = "Good news : someone cancelled his bid on " + auction.getName()
				+ "! You are now back in the game! Good luck to you until the " + auction.getEndDate();
		notificationM.create(new Notification(previousBid.getUserId(), auction.getUserId(), NotificationType.ADMIN,
				content, auction.getId()));
	}

	private void refundLastBidder(Auction auction, BidManager bidM, UserManager userM) throws BLLException {
		Bid maxBid = bidM.getMaxBid(auction.getId());
		userM.credit(maxBid.getUserId(), maxBid.getBidPrice());
	}
}
