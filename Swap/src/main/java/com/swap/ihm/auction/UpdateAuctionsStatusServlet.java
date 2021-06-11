package com.swap.ihm.auction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.UserManager;
import com.swap.bo.Auction;
import com.swap.bo.BOException;
import com.swap.bo.User;
import com.swap.ihm.AuctionStatus;
import com.swap.ihm.MotherServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateAuctionsStatusServlet
 */
@WebServlet("/update-auctions")
public class UpdateAuctionsStatusServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String UP_AUC_JSP = "/WEB-INF/UpdateAuctionsStatus.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO
		sendToJSP(UP_AUC_JSP, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Deal with picked_up status
		List<Auction> auctions = getAllAuctions();
		for (Auction auction : auctions) {
			String status = auction.getStatus();
			LocalDate startDate = auction.getStartDate();
			LocalDate endDate = auction.getStartDate();
			if (status.equals(AuctionStatus.CREATED.getStatus())
					&& (startDate.isBefore(LocalDate.now()) || startDate.isEqual(LocalDate.now()))) {
				auction.setStatus(AuctionStatus.ONGOING.getStatus());
				updateAuction(auction);
				creditSeller(auction);
			} else if (status.equals(AuctionStatus.ONGOING.getStatus())
					&& (endDate.isBefore(LocalDate.now()) || endDate.isEqual(LocalDate.now()))) {
				auction.setStatus(AuctionStatus.OVER.getStatus());
				updateAuction(auction);
			}
		}
	}

	private List<Auction> getAllAuctions() {
		List<Auction> auctions = null;
		AuctionManager aucmng = new AuctionManager();
		try {
			auctions = aucmng.getAll();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return auctions;
	}

	private void updateAuction(Auction auction) {
		AuctionManager aucmng = new AuctionManager();
		try {
			aucmng.update(auction);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	private void creditSeller(Auction auction) {
		UserManager usmng = new UserManager();
		try {
			User user = usmng.getById(auction.getUserId());
			user.setBalance(user.getBalance() + auction.getSalePrice());
			usmng.update(user);
		} catch (BLLException | BOException e) {
			e.printStackTrace();
		}
	}

}
