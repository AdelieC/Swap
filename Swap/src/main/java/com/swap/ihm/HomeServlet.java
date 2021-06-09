package com.swap.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.swap.bll.BLLException;
import com.swap.bll.CategoryManager;
import com.swap.bll.UserManager;
import com.swap.bo.Auction;
import com.swap.bo.Category;
import com.swap.bo.User;
import com.swap.ihm.auction.AuctionThumbnail;

/**
 * Servlet handling index page or homepage (when logged in) display user
 */
@WebServlet(description = "Handles index page or homepage (when logged in)", urlPatterns = { "/home" })
public class HomeServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String HOME_JSP = "/WEB-INF/Home.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			setCategoriesList(request);
			if (request.getQueryString() != null && request.getQueryString().length() > 0) {
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!userIsLoggedIn(request))
			doGet(request, response);
		try {
			setThumbnailsWithUserFilters(request);
			sendToJSP(HOME_JSP, request, response);
		} catch (BLLException e1) {
			// TODO send to error page 500
			e1.printStackTrace();
		}
	}

	private void setThumbnails(HttpServletRequest request) throws BLLException {
		AuctionsList auctions = new AuctionsList();
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		thumbnails = getThumbnails(auctions.getAllOngoingAuctions());
		request.setAttribute("thumbnails", thumbnails);
	}

	private void setThumbnailsWithBasicFilters(HttpServletRequest request) throws BLLException {
		AuctionsList auctions = new AuctionsList();
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		int categoryId = Integer.valueOf(request.getParameter("category"));
		String q = request.getParameter("search");// TODO clean with formcleaner
		thumbnails = getThumbnails(auctions.getFilteredList(categoryId, q));
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("search", q);
		request.setAttribute("thumbnails", thumbnails);
	}

	private void setThumbnailsWithUserFilters(HttpServletRequest request) throws BLLException {
		HttpSession session = request.getSession();
		int userId = ((User) session.getAttribute("user")).getUserId();
		AuctionsList auctions = new AuctionsList(userId);
		List<Auction> auctionsList = new ArrayList<>();
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		Map<String, String[]> filters = request.getParameterMap();
		int categoryId = Integer.valueOf(request.getParameter("category"));
		String q = request.getParameter("search");// TODO clean with formcleaner
		if (categoryId > 0 || q != null)
			auctionsList = auctions.getFilteredList(categoryId, q);
		auctionsList = auctions.getFilteredList(auctionsList, filters);
		thumbnails = getThumbnails(auctionsList);
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("search", q);
		request.setAttribute("thumbnails", thumbnails);
	}

	private void setCategoriesList(HttpServletRequest request) throws BLLException {
		CategoryManager catmng = new CategoryManager();
		List<Category> categorieslist = catmng.getAll();
		request.setAttribute("categoriesList", categorieslist);
	}

	private List<AuctionThumbnail> getThumbnails(List<Auction> auctions) {
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		auctions.forEach(auction -> thumbnails.add(getThumbnailByUserId(auction)));
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
