package com.swap.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(description = "Handles index page or homepage (when logged in)", urlPatterns = { "/" })
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
		AuctionManager aucmng = new AuctionManager();
		CategoryManager catmng = new CategoryManager();
		List<Auction> auctionsList = new ArrayList<>();
		List<AuctionThumbnail> thumbnails = new ArrayList<>();
		List<Category> categorieslist = new ArrayList<>();
		int categoryId = 0;
		String filter = null;
		try {
			categorieslist = catmng.getAll();
			categoryId = Integer.valueOf(request.getParameter("category"));
			filter = request.getParameter("filter");
			if (categoryId > 0) {
				if (filter == null) {
					auctionsList = aucmng.getByCategory(categoryId);
				} else {
					auctionsList = aucmng.getByNameAndCategory(filter, categoryId);
				}
			} else if (filter != null) {
				auctionsList = aucmng.getByName(filter);
			} else {
				auctionsList = aucmng.getAll();
			}
			thumbnails = getThumbnails(auctionsList);
			request.setAttribute("categoryId", categoryId);
			request.setAttribute("filter", request.getParameter("filter"));
			request.setAttribute("thumbnails", thumbnails);
			request.setAttribute("categoriesList", categorieslist);
			sendToJSP(HOME_JSP, request, response);
		} catch (BLLException e) {
			// TODO send to error page 500
			e.printStackTrace();
		}

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
