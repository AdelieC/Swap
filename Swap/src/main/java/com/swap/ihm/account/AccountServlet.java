package com.swap.ihm.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.UserManager;
import com.swap.bo.Auction;
import com.swap.bo.User;
import com.swap.ihm.MotherServlet;
import com.swap.ihm.auction.AuctionThumbnail;

/**
 * Servlet implementation class Account
 */
@WebServlet(urlPatterns = { "/account", "/user" })
public class AccountServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String PROFILE_JSP = "/WEB-INF/Profile.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User user = null;
			if (request.getRequestURI().contains("user") && request.getParameter("id") != null) {
				int userId = Integer.parseInt(request.getParameter("id"));
				UserManager userM = new UserManager();
				user = userM.getById(userId);
				setThumbnails(request, userId);
			} else {
				user = (User) session.getAttribute("user");
			}
			request.setAttribute("targetUser", user);
			sendToJSP(PROFILE_JSP, request, response);
		} catch (NumberFormatException | BLLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void setThumbnails(HttpServletRequest request, int userId) throws BLLException {
		AuctionManager auctionM = new AuctionManager();
		List<Auction> auctions = auctionM.getOngoingByUserId(userId);
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		thumbnails = getThumbnailList(auctions);
		request.setAttribute("thumbnails", thumbnails);
	}

	private List<AuctionThumbnail> getThumbnailList(List<Auction> auctions) {
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		auctions.forEach(auction -> thumbnails.add(getThumbnail(auction)));
		return thumbnails;
	}

	private AuctionThumbnail getThumbnail(Auction auction) {
		UserManager userM = new UserManager();
		User user = null;
		AuctionThumbnail auctionThumbnail = null;
		try {
			user = userM.getById(auction.getUserId());
			if (auction.getPictures().isEmpty()) {
				auctionThumbnail = new AuctionThumbnail(auction.getId(), auction.getName(), auction.getSalePrice(),
						auction.getEndDate(), user.getUsername());
			} else {
				auctionThumbnail = new AuctionThumbnail(auction.getId(), auction.getName(), auction.getSalePrice(),
						auction.getEndDate(), user.getUsername(), auction.getPictures().get(0));
			}
		} catch (BLLException e) {
			// TODO Handle error 500
			e.printStackTrace();
		}
		return auctionThumbnail;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
