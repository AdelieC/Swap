package com.swap.ihm.auction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.PickUpPointManager;
import com.swap.bo.Auction;
import com.swap.bo.PickUpPoint;
import com.swap.ihm.AuctionStatus;
import com.swap.ihm.MotherServlet;

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
		int auctionId = Integer.valueOf(request.getParameter("auctionId"));
		if (canDelete(auctionId)) {
			deleteAuction(auctionId);
		}
		response.sendRedirect(request.getServletContext().getContextPath());
	}

	private boolean canDelete(int auctionId) {
		AuctionManager aucmng = new AuctionManager();
		Auction auction = null;
		try {
			auction = aucmng.getById(auctionId);
			String status = auction.getStatus();
			if (status.equals(AuctionStatus.CREATED.getStatus()) || status.equals(AuctionStatus.ONGOING.getStatus())) {
				return true;
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void deleteAuction(int auctionId) {
		AuctionManager aucmng = new AuctionManager();
		Auction auction = null;
		try {
			deletePickUpPoint(auctionId);
			auction = aucmng.getById(auctionId);
			aucmng.delete(auction);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	private void deletePickUpPoint(int auctionId) {
		PickUpPointManager pupmng = new PickUpPointManager();
		try {
			PickUpPoint pup = pupmng.getByAuctionId(auctionId);
			pupmng.delete(pup);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
}
