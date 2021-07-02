package com.swap.ihm.auction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.CategoryManager;
import com.swap.bo.Auction;
import com.swap.bo.Category;
import com.swap.bo.User;
import com.swap.ihm.AuctionStatus;
import com.swap.ihm.MotherServlet;

@WebServlet(urlPatterns = { "/my-auctions" })
public class ViewMyAuctionsServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String MY_AUCTIONS_JSP = "/WEB-INF/ViewMyAuctions.jsp";
	private static final AuctionManager auctionM = new AuctionManager();
	private static final CategoryManager catmng = new CategoryManager();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			setCategories(request);
			List<Auction> auctionsList = getMyAuctionsList(request);
			setFutureAuctionsThumbnails(request, auctionsList);
			setOngoingAuctionsThumbnails(request, auctionsList);
			setPastAuctionsThumbnails(request, auctionsList);
			sendToJSP(MY_AUCTIONS_JSP, request, response);
		} catch (BLLException e) {
			// TODO send to error page 500
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void setFutureAuctionsThumbnails(HttpServletRequest request, List<Auction> auctionsList)
			throws BLLException {
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		List<Auction> futureAuctionsList = new ArrayList<>();
		for (Auction auction : auctionsList) {
			if (auction.getStatus().equals(AuctionStatus.CREATED.name()))
				futureAuctionsList.add(auction);
		}
		thumbnails = getThumbnailsList(futureAuctionsList);
		request.setAttribute("futureAuctions", thumbnails);
	}

	private void setOngoingAuctionsThumbnails(HttpServletRequest request, List<Auction> auctionsList)
			throws BLLException {
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		List<Auction> ongoingAuctionsList = new ArrayList<>();
		for (Auction auction : auctionsList) {
			if (auction.getStatus().equals(AuctionStatus.ONGOING.name()))
				ongoingAuctionsList.add(auction);
		}
		thumbnails = getThumbnailsList(ongoingAuctionsList);
		request.setAttribute("ongoingAuctions", thumbnails);
	}

	private void setPastAuctionsThumbnails(HttpServletRequest request, List<Auction> auctionsList) throws BLLException {
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		List<Auction> pastAuctionsList = new ArrayList<>();
		for (Auction auction : auctionsList) {
			if (auction.getStatus().equals(AuctionStatus.OVER.name()))
				pastAuctionsList.add(auction);
		}
		thumbnails = getThumbnailsList(pastAuctionsList);
		request.setAttribute("pastAuctions", thumbnails);
	}

	private List<Auction> getMyAuctionsList(HttpServletRequest request) throws BLLException {
		HttpSession session = request.getSession();
		int userId = ((User) session.getAttribute("user")).getUserId();
		return auctionM.getByUserId(userId);
	}

	private void setCategories(HttpServletRequest request) throws BLLException {
		List<Category> categorieslist = catmng.getAll();
		request.setAttribute("categoriesList", categorieslist);
	}

	private List<AuctionThumbnail> getThumbnailsList(List<Auction> auctions) throws BLLException {
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		for (Auction auction : auctions) {
			thumbnails.add(getThumbnail(auction));
		}
		return thumbnails;
	}

	private AuctionThumbnail getThumbnail(Auction auction) throws BLLException {
		if (auction.getPictures().isEmpty()) {
			return new AuctionThumbnail(auction.getId(), auction.getName(), auction.getSalePrice(),
					auction.getStartDate(), auction.getEndDate());
		} else {
			return new AuctionThumbnail(auction.getId(), auction.getName(), auction.getSalePrice(),
					auction.getStartDate(), auction.getEndDate(), auction.getPictures().get(0));
		}
	}
}
