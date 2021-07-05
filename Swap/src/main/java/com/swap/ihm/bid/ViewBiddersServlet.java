package com.swap.ihm.bid;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.swap.bll.BLLException;
import com.swap.bll.BidManager;
import com.swap.bll.UserManager;
import com.swap.bo.Bid;
import com.swap.bo.User;
import com.swap.ihm.MotherServlet;

/**
 * Servlet implementation class ViewBiddersServlet
 */
@WebServlet("/auction/view/bidders")
public class ViewBiddersServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_BIDDERS_JSP = "/WEB-INF/ViewBidders.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO
		int auctionId = Integer.valueOf(request.getParameter("id"));
		List<BidderThumbnail> bidders = getBiddersForAuction(auctionId);
		request.setAttribute("biddersList", bidders);
		sendToJSP(VIEW_BIDDERS_JSP, request, response);
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

	private List<BidderThumbnail> getBiddersForAuction(int auctionId) {
		List<BidderThumbnail> bidders = new ArrayList<BidderThumbnail>();
		List<Bid> bids = getByAuctionId(auctionId);
		for (Bid bid : bids) {
			BidderThumbnail thumbnail = new BidderThumbnail();
			thumbnail.setBid(bid.getBidPrice());
			thumbnail.setUsername(getUsername(bid.getUserId()));
			bidders.add(thumbnail);
		}
		return bidders;
	}

	private List<Bid> getByAuctionId(int auctionId) {
		List<Bid> bids = null;
		BidManager bidmng = new BidManager();
		try {
			bids = bidmng.getByAuctionId(auctionId);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return bids;
	}

	private String getUsername(int id) {
		String username = null;
		UserManager usmng = new UserManager();
		User user = null;
		try {
			user = usmng.getById(id);
			username = user.getUsername();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return username;
	}

}
