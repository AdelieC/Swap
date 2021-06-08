package com.swap.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.BidManager;
import com.swap.bll.CategoryManager;
import com.swap.bll.UserManager;
import com.swap.bo.Auction;
import com.swap.bo.Bid;
import com.swap.bo.Category;
import com.swap.bo.User;

/**
 * Servlet handling index page or homepage (when logged in) display user
 */
@WebServlet(description = "Handles index page or homepage (when logged in)", urlPatterns = { "/home" })
public class HomeServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String HOME_JSP = "/WEB-INF/Home.jsp";
	private static final String HOME_PATH = "/Swap";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			setCategoriesList(request);
			if (request.getQueryString().length() > 0) {
				System.out.println("content : " + request.getQueryString()); // TODO : remove after debug
				setThumbnailsWithBasicFilters(request);
			} else {
				setThumbnails(request);
			}
			sendToJSP(HOME_JSP, request, response);
		} catch (BLLException e1) {
			// TODO send to error page 500
			e1.printStackTrace();
		}
	}

	private void setThumbnails(HttpServletRequest request) throws BLLException {
		AuctionManager aucmng = new AuctionManager();
		List<Auction> auctionsList = new ArrayList<>();
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		auctionsList = aucmng.getAllNotOver();
		thumbnails = getThumbnails(auctionsList);
		request.setAttribute("thumbnails", thumbnails);
	}

	private void setThumbnailsWithBasicFilters(HttpServletRequest request) throws BLLException {
		AuctionManager aucmng = new AuctionManager();
		List<Auction> auctionsList = new ArrayList<>();
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		int categoryId = Integer.valueOf(request.getParameter("category"));
		String q = request.getParameter("search");// TODO clean with formcleaner
		aucmng.getAllNotOver().forEach(x -> System.out.println(x.getName())); // TODO remove after debug
		auctionsList = applyBasicFilters(aucmng.getAllNotOver(), categoryId, q);
		thumbnails = getThumbnails(auctionsList);
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("search", q);
		request.setAttribute("thumbnails", thumbnails);
	}

	public List<Auction> applyBasicFilters(List<Auction> auctionsList, int categoryId, String q) {
		List<Auction> tempList = new ArrayList<>();

		if (categoryId > 0) {
			for (Auction auction : auctionsList) {
				if (auction.getCategoryId() == categoryId)
					tempList.add(auction);
			}
			auctionsList.retainAll(tempList);
			tempList.clear();
		}
		if (q != null) {
			for (Auction auction : auctionsList) {
				if (auction.getName().contains(q))
					tempList.add(auction);
			}
			auctionsList.retainAll(tempList);
			tempList.clear();
		}
		return auctionsList;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!userIsLoggedIn(request))
			doGet(request, response);
		try {
			setCategoriesList(request);
			if (request.getContentLength() > 0) {
				setThumbnailsWithUserFilters(request);
			} else {
				setThumbnails(request);
			}
			sendToJSP(HOME_JSP, request, response);
		} catch (BLLException e1) {
			// TODO send to error page 500
			e1.printStackTrace();
		}
	}

	private void setThumbnailsWithUserFilters(HttpServletRequest request) throws BLLException {
		HttpSession session = request.getSession();
		int userId = ((User) session.getAttribute("user")).getUserId();
		AuctionManager aucmng = new AuctionManager();
		List<Auction> auctionsList = new ArrayList<>();
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		List<String> filters = new ArrayList<>();
		int categoryId = Integer.valueOf(request.getParameter("category"));
		String q = request.getParameter("search");// TODO clean with formcleaner
		filters = getFiltersValues(request);
		auctionsList = applyUserFilters(aucmng.getAll(), filters, userId);
		auctionsList = applyBasicFilters(auctionsList, categoryId, q);
		thumbnails = getThumbnails(auctionsList);
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("search", q);
		request.setAttribute("thumbnails", thumbnails);
	}

	private List<Auction> applyUserFilters(List<Auction> auctionsList, List<String> filters, int userId)
			throws BLLException {
		if (filters.contains("bidsFilters")) {
			auctionsList = applyBidsFilters(auctionsList, filters, userId);
		} else if (filters.contains("myAuctionsFilters")) {
			auctionsList = applyMyAuctionsFilters(auctionsList, filters, userId);
		}
		return auctionsList;
	}

	private List<Auction> applyMyAuctionsFilters(List<Auction> auctionsList, List<String> filters, int userId) {
		List<Auction> tempList = new ArrayList<>();
		if (filters.contains("my-ongoing-aucts")) {
			tempList = getMyOngoingAuctions(auctionsList, userId);
			if (filters.contains("my-future-aucts")) {
				tempList.addAll(getMyFutureAuctions(auctionsList, userId));
			}
		} else if (filters.contains("my-future-aucts")) {
			tempList = getMyFutureAuctions(auctionsList, userId);
			if (filters.contains("my-past-aucts")) {
				tempList.addAll(getMyPastAuctions(auctionsList, userId));
			}
		} else if (filters.contains("my-past-aucts")) {
			tempList = getMyPastAuctions(auctionsList, userId);
		} else {
			tempList = getMyAuctions(auctionsList, userId);
		}
		return tempList;
	}

	private List<Auction> getMyAuctions(List<Auction> auctionsList, int userId) {
		List<Auction> tempList = new ArrayList<>();
		for (Auction auction : auctionsList) {
			if (auction.getUserId() == userId)
				tempList.add(auction);
		}
		return tempList;
	}

	private List<Auction> getMyPastAuctions(List<Auction> auctionsList, int userId) {
		List<Auction> tempList = new ArrayList<>();
		for (Auction auction : auctionsList) {
			if (auction.getUserId() == userId
					&& (auction.getStatus().equals("OVER") || auction.getStatus().equals("PICKED_UP")))
				tempList.add(auction);
		}
		return tempList;
	}

	private List<Auction> getMyFutureAuctions(List<Auction> auctionsList, int userId) {
		List<Auction> tempList = new ArrayList<>();
		for (Auction auction : auctionsList) {
			if (auction.getUserId() == userId && auction.getStatus().equals("CREATED"))
				tempList.add(auction);
		}
		return tempList;
	}

	private List<Auction> getMyOngoingAuctions(List<Auction> auctionsList, int userId) {
		List<Auction> tempList = new ArrayList<>();
		for (Auction auction : auctionsList) {
			if (auction.getUserId() == userId && auction.getStatus().equals("ONGOING"))
				tempList.add(auction);
		}
		return tempList;
	}

	private List<Auction> applyBidsFilters(List<Auction> auctionsList, List<String> filters, int userId)
			throws BLLException {
		List<Auction> tempList = new ArrayList<>();
		if (filters.contains("all-bids-on")) {
			tempList = getAllBidsOn(auctionsList);
			if (filters.contains("my-bids-won")) {
				tempList.addAll(getMyBidsWon(auctionsList, userId));
			}
		} else if (filters.contains("my-bids-on")) {
			tempList = getMyBids(getAllBidsOn(auctionsList), userId);
			if (filters.contains("my-bids-won")) {
				tempList.addAll(getMyBidsWon(auctionsList, userId));
			}
		} else if (filters.contains("my-bids-won")) {
			tempList = getMyBidsWon(auctionsList, userId);
		}
		return tempList;
	}

	private List<Auction> getMyBidsWon(List<Auction> auctionsList, int userId) throws BLLException {
		List<Bid> bids = new ArrayList<>();
		List<Auction> tempList = new ArrayList<>();
		BidManager bidmng = new BidManager();
		bids = bidmng.getByUserId(userId);
		for (Auction auction : auctionsList) {
			if (auction.getStatus().equals("OVER") || auction.getStatus().equals("PICKED-UP")) {
				bids.forEach(bid -> {
					if (bid.getAuctionId() == auction.getId())
						tempList.add(auction);
				});
			}
		}
		return tempList;
	}

	private List<Auction> getMyBids(List<Auction> auctionsList, int userId) throws BLLException {
		List<Bid> bids = new ArrayList<>();
		List<Auction> tempList = new ArrayList<>();
		BidManager bidmng = new BidManager();
		bids = bidmng.getByUserId(userId);
		for (Auction auction : auctionsList) {
			bids.forEach(bid -> {
				if (bid.getAuctionId() == auction.getId())
					tempList.add(auction);
			});
		}
		return tempList;
	}

	private List<Auction> getAllBidsOn(List<Auction> auctionsList) {
		List<Auction> tempList = new ArrayList<>();
		for (Auction auction : auctionsList) {
			if (!auction.getStatus().equals("OVER"))
				tempList.add(auction);
		}
		return tempList;
	}

	private List<String> getFiltersValues(HttpServletRequest request) {
		Enumeration<String> paramNames = request.getParameterNames();
		List<String> filters = new ArrayList<>();
		while (paramNames.hasMoreElements()) {
			if (!isBasicFilter(paramNames.nextElement())) {
				String[] values = request.getParameterValues(paramNames.nextElement());
				for (String value : values)
					filters.add(value);
			}
		}
		return filters;
	}

	private boolean isBasicFilter(String paramName) {
		return paramName.equals("category") || paramName.equals("q");
	}

	private void setCategoriesList(HttpServletRequest request) throws BLLException {
		CategoryManager catmng = new CategoryManager();
		List<Category> categorieslist = catmng.getAll();
		request.setAttribute("categoriesList", categorieslist);
	}

	private List<AuctionThumbnail> getThumbnails(List<Auction> auctionsList) {
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		auctionsList.forEach(auction -> thumbnails.add(getThumbnailByUserId(auction)));
		return thumbnails;
	}

	private AuctionThumbnail getThumbnailByUserId(Auction auction) {
		UserManager usmng = new UserManager();
		User user = new User();
		try {
			user = usmng.getById(auction.getUserId());
		} catch (BLLException e) {
			// TODO : send to error page 500
			e.printStackTrace();
		}
		return new AuctionThumbnail(auction.getId(), auction.getName(), auction.getSalePrice(), auction.getEndDate(),
				user.getUsername());
	}

}
