package com.swap.ihm;

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

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AuctionManager aucmng = new AuctionManager();
		UserManager usmng = new UserManager();
		List<Auction> list = null;
		List<AuctionThumbnail> thumbnails = new ArrayList<AuctionThumbnail>();
		List<Category> categorieslist = new ArrayList<Category>();
		CategoryManager catmng = new CategoryManager();
		try {
			categorieslist = catmng.getAll();
			request.setAttribute("categoriesList", categorieslist);
			list = aucmng.getAll();
			for (Auction auction : list) {
				User user = usmng.getById(auction.getUserId());
				AuctionThumbnail thumbnail = new AuctionThumbnail(auction.getName(), auction.getSalePrice(),
						auction.getEndDate(), user.getUsername());
				thumbnails.add(thumbnail);
				request.setAttribute("thumbnails", thumbnails);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AuctionManager aucmng = new AuctionManager();
		UserManager usmng = new UserManager();
		List<Auction> list = null;
		List<AuctionThumbnail> thumbnails = new ArrayList<AuctionThumbnail>();
		List<Category> categorieslist = new ArrayList<Category>();
		CategoryManager catmng = new CategoryManager();
		try {
			categorieslist = catmng.getAll();
			request.setAttribute("categoriesList", categorieslist);
			int categoryId = Integer.valueOf(request.getParameter("category"));
			// TODO Factorise the following horror
			if (categoryId > 0 && request.getParameter("filter") == null) {
				request.setAttribute("categoryId", categoryId);
				list = aucmng.getByCategory(categoryId);
			} else if (categoryId == 0 && request.getParameter("filter") != null) {
				request.setAttribute("filter", request.getParameter("filter"));
				list = aucmng.getByName(request.getParameter("filter"));
			} else if (categoryId > 0 && request.getParameter("filter") != null) {
				request.setAttribute("categoryId", categoryId);
				request.setAttribute("filter", request.getParameter("filter"));
				list = aucmng.getByNameAndCategory(request.getParameter("filter"), categoryId);
			} else {
				list = aucmng.getAll();
			}
			for (Auction auction : list) {
				User user = usmng.getById(auction.getUserId());
				AuctionThumbnail thumbnail = new AuctionThumbnail(auction.getName(), auction.getSalePrice(),
						auction.getEndDate(), user.getUsername());
				thumbnails.add(thumbnail);
				request.setAttribute("thumbnails", thumbnails);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
		rd.forward(request, response);
	}

}
