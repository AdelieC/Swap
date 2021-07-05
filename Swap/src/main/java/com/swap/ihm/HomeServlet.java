package com.swap.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.CategoryManager;
import com.swap.bll.NotificationManager;
import com.swap.bll.UserManager;
import com.swap.bo.Auction;
import com.swap.bo.Category;
import com.swap.bo.Notification;
import com.swap.bo.User;
import com.swap.ihm.auction.AuctionThumbnail;
import com.swap.ihm.notification.NotificationThumbnail;

/**
 * Servlet handling index page or homepage (when logged in) display user
 */
@WebServlet(urlPatterns = { "/home" })
public class HomeServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_HOME_JSP = "/WEB-INF/UserHome.jsp";
	private static final String VISITOR_HOME_JSP = "/WEB-INF/VisitorHome.jsp";
	private static final AuctionManager auctionM = new AuctionManager();
	private static final NotificationManager notificationM = new NotificationManager();
	private static final CategoryManager catmng = new CategoryManager();
	private static final UserManager userM = new UserManager();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			setCategories(request);
			if (request.getQueryString() == null) {
				setThumbnails(request);
			} else {
				setThumbnailsWithFilters(request);
			}
			if (userIsLoggedIn(request)) {
				setUnreadNotifications(request);
				sendToJSP(USER_HOME_JSP, request, response);
			} else {
				sendToJSP(VISITOR_HOME_JSP, request, response);
			}
		} catch (BLLException e) {
			// TODO send to error page 500
			e.printStackTrace();
		}
	}

	private void setUnreadNotifications(HttpServletRequest request) throws BLLException {
		HttpSession session = request.getSession();
		int userId = ((User) session.getAttribute("user")).getUserId();
		List<Notification> notificationsList = notificationM.getByRecipient(userId);
		List<NotificationThumbnail> notificationsThumbnails = new ArrayList<>();
		notificationsList.removeIf(n -> n.isRead());
		for (Notification notification : notificationsList) {
			notificationsThumbnails.add(new NotificationThumbnail(notification));
		}
		request.setAttribute("notifications", notificationsThumbnails);
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
			request.setAttribute("auctionsList", thumbnails);
		}
	}

	private List<Auction> filter(List<Auction> auctionsList, Map<String, String[]> filters) {
		if (filters.containsKey("categoryId") && !filters.get("categoryId")[0].isBlank())
			filterByCategory(auctionsList, Integer.parseInt(filters.get("categoryId")[0]));
		if (filters.containsKey("keyword") && !filters.get("keyword")[0].isBlank())
			filterByKeyword(auctionsList, filters.get("keyword")[0].toLowerCase());
		if (filters.containsKey("startDate") && !filters.get("startDate")[0].isBlank())
			filterByStartDate(auctionsList, filters.get("startDate")[0]);
		if (filters.containsKey("endDate") && !filters.get("endDate")[0].isBlank())
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
		if (categoryId > 0) {
			List<Auction> tempList = new ArrayList<>();
			for (Auction auction : auctionsList) {
				if (auction.getCategoryId() == categoryId)
					tempList.add(auction);
			}
			auctionsList.retainAll(tempList);
		}
	}

	private void setFilterAttributes(HttpServletRequest request, Map<String, String[]> filters) {
		filters.forEach((key, arrValues) -> {
			request.setAttribute(key, arrValues[0]);
		});
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

	private AuctionThumbnail getThumbnail(Auction auction) {
		User user = null;
		AuctionThumbnail auctionThumbnail = null;
		try {
			user = userM.getById(auction.getUserId());
			if (auction.getPictures().isEmpty()) {
				auctionThumbnail = new AuctionThumbnail(auction.getId(), auction.getName(), auction.getSalePrice(),
						auction.getStartDate(), auction.getEndDate(), user.getUsername());
			} else {
				auctionThumbnail = new AuctionThumbnail(auction.getId(), auction.getName(), auction.getSalePrice(),
						auction.getStartDate(), auction.getEndDate(), user.getUsername(), auction.getPictures().get(0));
			}
		} catch (BLLException e) {
			// TODO Handle error 500
			e.printStackTrace();
		}
		return auctionThumbnail;
	}
}
