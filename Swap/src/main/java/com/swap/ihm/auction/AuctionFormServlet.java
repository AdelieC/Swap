package com.swap.ihm.auction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.CategoryManager;
import com.swap.bll.PictureManager;
import com.swap.bll.PickUpPointManager;
import com.swap.bo.Auction;
import com.swap.bo.BOException;
import com.swap.bo.Category;
import com.swap.bo.PickUpPoint;
import com.swap.bo.Picture;
import com.swap.bo.User;
import com.swap.ihm.AuctionStatus;
import com.swap.ihm.IHMException;
import com.swap.ihm.MotherServlet;

/**
 * Servlet implementation class auctionConfigServlet
 */
@WebServlet(description = "Handles creation of a single auction", urlPatterns = { "/auction" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
public class AuctionFormServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String AUCT_CONF_JSP = "/WEB-INF/AuctionForm.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PickUpPoint pup = null;
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			String title = "Create auction";
			setCategories(request);
			setPeriod(request);
			if (request.getParameter("id") != null) {
				title = "Update auction";
				int id = Integer.valueOf(request.getParameter("id"));
				Auction auction = getAuctionById(id);
				if (auction != null && auction.getStatus().equals(AuctionStatus.CREATED.name())) {
					pup = getPUPByAuctionId(id);
					request.setAttribute("auction", auction);
				} else {
					throw new IHMException("Cannot edit auction");
				}
			} else {
				pup = new PickUpPoint(0, user.getStreet(), user.getPostcode(), user.getCity());
			}
			request.setAttribute("pickUpPoint", pup);
			request.setAttribute("title", title);
			sendToJSP(AUCT_CONF_JSP, request, response);
		} catch (BLLException e) {
			// TODO Error 500
			e.printStackTrace();
		} catch (IHMException e) {
			// TODO Error 404
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {
			setAuction(request, user);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getServletContext().getContextPath());
	}

	private void setAuction(HttpServletRequest request, User user)
			throws IOException, ServletException, BLLException, BOException {
		List<FileItem> items = getItems(request);
		Map<String, String> fields = getFields(items);
		Auction auction = buildAuction(fields, user);
		PickUpPoint pup = buildPickUpPoint(fields, user);
		if (fields.get("auctionId").isBlank()) {
			createAuction(auction, pup);
			setImages(items, request, auction);
		} else {
			auction.setId(Integer.valueOf(fields.get("auctionId")));
			updateAuction(auction, pup);
		}
	}

	private List<FileItem> getItems(HttpServletRequest request) throws FileUploadException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		return upload.parseRequest(new ServletRequestContext(request));
	}

	private Map<String, String> getFields(List<FileItem> items) {
		Map<String, String> fields = new HashMap<>();
		for (FileItem item : items) {
			if (item.isFormField()) {
				fields.put(item.getFieldName(), item.getString());
			}
		}
		return fields;
	}

	private void setImages(List<FileItem> items, HttpServletRequest request, Auction auction)
			throws IOException, ServletException, BOException, BLLException {
		int index = 0;
		for (FileItem item : items) {
			if (isUploadableImage(item)) {
				index++;
				createImage(item, auction, index);
			}
		}
	}

	private boolean isUploadableImage(FileItem item) {
		return !item.isFormField() && !item.isInMemory() && item.getContentType().contains("image");
	}

	private void createImage(FileItem item, Auction auction, int index) throws IOException, BOException, BLLException {
		Picture image = null;
		PictureManager imageM = new PictureManager();
		image = new Picture(auction.getId(), auction.getName(), item, index);
		if (image != null)
			imageM.create(image);
	}

	private Auction buildAuction(Map<String, String> fields, User user) {
		// TODO Use FormCleaner
		String name = fields.get("name");
		String description = fields.get("description");
		int categoryId = Integer.valueOf(fields.get("category"));
		LocalDate startDate = LocalDate.parse(fields.get("start-date"));
		LocalDate endDate = LocalDate.parse(fields.get("end-date"));
		int initialPrice = Integer.valueOf(fields.get("initial-price"));
		return new Auction(name, description, startDate, endDate, categoryId, initialPrice, user.getUserId());
	}

	private PickUpPoint buildPickUpPoint(Map<String, String> fields, User user) {
		String street = fields.get("street").isBlank() ? user.getStreet() : fields.get("street");
		String postcode = fields.get("postcode").isBlank() ? user.getPostcode() : fields.get("postcode");
		String city = fields.get("city").isBlank() ? user.getCity() : fields.get("city");
		return new PickUpPoint(0, street, postcode, city);
	}

	private void createAuction(Auction auction, PickUpPoint pup) {
		AuctionManager aucmng = new AuctionManager();
		PickUpPointManager pupmng = new PickUpPointManager();
		try {
			aucmng.create(auction);
			pup.setAuctionId(auction.getId());
			pupmng.create(pup);

		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	private void updateAuction(Auction auction, PickUpPoint pup) {
		AuctionManager aucmng = new AuctionManager();
		try {
			aucmng.update(auction);
			updatePickUpPoint(pup, auction.getId());
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	private void updatePickUpPoint(PickUpPoint pup, int auctionId) {
		PickUpPointManager pupmng = new PickUpPointManager();
		pup.setAuctionId(auctionId);
		pup.setId(getPupIdWithAuctionId(auctionId));
		try {
			pupmng.update(pup);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	private Auction getAuctionById(int auctionId) {
		AuctionManager aucmng = new AuctionManager();
		Auction auction = null;
		try {
			auction = aucmng.getById(auctionId);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return auction;
	}

	private PickUpPoint getPUPByAuctionId(int id) {
		PickUpPointManager pupmng = new PickUpPointManager();
		PickUpPoint pup = null;
		try {
			pup = pupmng.getById(id);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return pup;
	}

	private int getPupIdWithAuctionId(int auctionId) {
		PickUpPoint pup = null;
		int id = 0;
		pup = getPUPByAuctionId(id);
		id = pup.getId();
		return id;
	}

	private void setCategories(HttpServletRequest request) throws BLLException {
		List<Category> categorieslist = new ArrayList<Category>();
		CategoryManager catmng = new CategoryManager();
		categorieslist = catmng.getAll();
		request.setAttribute("categoriesList", categorieslist);
	}

	private void setPeriod(HttpServletRequest request) {
		LocalDate minDate = LocalDate.now();
		LocalDate maxDate = minDate.plusMonths(2);
		request.setAttribute("minDate", minDate);
		request.setAttribute("maxDate", maxDate);
	}
}