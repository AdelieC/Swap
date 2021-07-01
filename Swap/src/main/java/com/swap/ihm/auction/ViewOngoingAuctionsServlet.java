package com.swap.ihm.auction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.CategoryManager;
import com.swap.bo.Auction;
import com.swap.bo.Category;
import com.swap.ihm.MotherServlet;

@WebServlet(urlPatterns = { "/all-auctions" })
public class ViewOngoingAuctionsServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_ONGOING_AUCTIONS_JSP = "/WEB-INF/ViewOngoingAuctions.jsp";
	private static AuctionManager auctionM = new AuctionManager();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			setCatListAttribute(request);
			if (request.getQueryString() == null) {
				setThumbnails(request);
			} else {
				setThumbnailsWithFilters(request);
			}
			sendToJSP(VIEW_ONGOING_AUCTIONS_JSP, request, response);
		} catch (BLLException e) {
			// TODO send to error page 500
			e.printStackTrace();
		}
	}

	private void setThumbnails(HttpServletRequest request) throws BLLException {
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		List<Auction> auctionsList = auctionM.getAllOnGoing();
		thumbnails = getThumbnailsList(auctionsList);
		request.setAttribute("auctionsList", thumbnails);
	}

	private void setThumbnailsWithFilters(HttpServletRequest request) throws BLLException {
		List<Auction> auctionsList = auctionM.getAllOnGoing();
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		Map<String, String[]> filters = request.getParameterMap();
		if (filters != null) {
			thumbnails = getThumbnailsList(filter(auctionsList, filters));
			setFilterAttributes(request, filters);
			request.setAttribute("thumbnails", thumbnails);
		}
	}

	private List<Auction> filter(List<Auction> auctionsList, Map<String, String[]> filters) {
		if (filters.containsKey("category") && filters.get("category") != null)
			filterByCategory(auctionsList, Integer.parseInt(filters.get("category")[0]));
		if (filters.containsKey("keyWord") && filters.get("keyWord") != null)
			filterByKeyword(auctionsList, filters.get("keyword")[0].toLowerCase());
		if (filters.containsKey("startDate") && filters.get("startDate") != null)
			filterByStartDate(auctionsList, filters.get("startDate")[0]);
		if (filters.containsKey("endDate") && filters.get("endDate") != null)
			filterByEndDate(auctionsList, filters.get("endDate")[0]);
		return auctionsList;
	}

	private void filterByEndDate(List<Auction> auctionsList, String stringEndDate) {
		List<Auction> tempList = new ArrayList<>();
		LocalDate endDate = LocalDate.parse(stringEndDate);
		for (Auction auction : auctionsList) {
			if (auction.getStartDate().isBefore(endDate) || auction.getStartDate().equals(endDate))
				tempList.add(auction);
		}
		auctionsList.retainAll(tempList);

	}

	private void filterByStartDate(List<Auction> auctionsList, String stringStartDate) {
		List<Auction> tempList = new ArrayList<>();
		LocalDate startDate = LocalDate.parse(stringStartDate);
		for (Auction auction : auctionsList) {
			if (auction.getStartDate().isAfter(startDate) || auction.getStartDate().equals(startDate))
				tempList.add(auction);
		}
		auctionsList.retainAll(tempList);
	}

	private void filterByKeyword(List<Auction> auctionsList, String lowerCaseKeyword) {
		List<Auction> tempList = new ArrayList<>();
		for (Auction auction : auctionsList) {
			if (auction.getName().toLowerCase().contains(lowerCaseKeyword))
				tempList.add(auction);
		}
		auctionsList.retainAll(tempList);
	}

	private void filterByCategory(List<Auction> auctionsList, int categoryId) {
		List<Auction> tempList = new ArrayList<>();
		for (Auction auction : auctionsList) {
			if (auction.getCategoryId() == categoryId)
				tempList.add(auction);
		}
		auctionsList.retainAll(tempList);
	}

	private void setFilterAttributes(HttpServletRequest request, Map<String, String[]> filters) {
		filters.forEach((key, arrValues) -> {
			request.setAttribute(key, arrValues[0]);
		});
	}

	private void setCatListAttribute(HttpServletRequest request) throws BLLException {
		CategoryManager catmng = new CategoryManager();
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
					auction.getEndDate());
		} else {
			return new AuctionThumbnail(auction.getId(), auction.getName(), auction.getSalePrice(),
					auction.getEndDate(), auction.getPictures().get(0));
		}
	}

}
