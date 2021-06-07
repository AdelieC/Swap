package com.swap.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.CategoryManager;
import com.swap.bll.UserManager;
import com.swap.bo.Auction;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			setCategoriesList(request);
			if (request.getContentLength() > 0) {
				setThumbnailsWithFilters(request);
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
		auctionsList = aucmng.getAll();
		thumbnails = getThumbnails(auctionsList);
		request.setAttribute("thumbnails", thumbnails);
	}

	private void setThumbnailsWithFilters(HttpServletRequest request) throws BLLException {
		HttpSession session = request.getSession();
		AuctionManager aucmng = new AuctionManager();
		List<Auction> auctionsList = new ArrayList<>();
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		int categoryId = (int) request.getAttribute("category");
		String searched = (String) request.getAttribute("search");// TODO clean with formcleaner
		if (userIsLoggedIn(request)) {
			Enumeration<String> paramNames = request.getParameterNames();
			List<String> filters = new ArrayList<>();
			while (paramNames.hasMoreElements()) {
				if (isFilter(paramNames.nextElement())) {
					String[] values = request.getParameterValues(paramNames.nextElement());
					for (String value : values)
						filters.add(value);
				}
			}
			// TODO : yay! I gotta a list of all filters' values!
			// -> now test them one by one? Get all auctions, then narrow it down?
			if (filters.contains("bids")) {
				auctionsList = aucmng.getAll();// TODO : new f° inside dal?
				if (filters.contains("my-bids-on")) {

				} else if (filters.contains("my-bids-won")) {

				}

			} else {

			}
		}
		// TODO : following method is crap (mine, of course)
		auctionsList = filterListByCatAndSearch(auctionsList, categoryId, searched);

		// TODO : finish!
	}

	private List<Auction> filterListByCatAndSearch(List<Auction> auctionsList, int categoryId, String searched) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isFilter(String inputName) {
		// TODO change double negative...
		return !inputName.equals("search") && !inputName.equals("category");
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
