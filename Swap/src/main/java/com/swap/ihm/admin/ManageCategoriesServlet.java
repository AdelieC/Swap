package com.swap.ihm.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.swap.bll.AuctionManager;
import com.swap.bll.BLLException;
import com.swap.bll.CategoryManager;
import com.swap.bo.Category;
import com.swap.ihm.FormCleaner;
import com.swap.ihm.IHMException;
import com.swap.ihm.MotherServlet;

@WebServlet(urlPatterns = { "/admin/categories" })
public class ManageCategoriesServlet extends MotherServlet {
	private static final long serialVersionUID = 1L;
	private static final String OUTCOME_JSP = "/WEB-INF/Outcome.jsp";
	private static final String CATEGORIES_JSP = "/WEB-INF/ManageCategories.jsp";
	private static final CategoryManager categoryM = new CategoryManager();
	private static final AuctionManager auctionM = new AuctionManager();
	private static final CategoryManager catmng = new CategoryManager();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			setCategories(request);
			sendToJSP(CATEGORIES_JSP, request, response);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("submit") != null && request.getParameter("submit").contains("Add")) {
				createCategory(request);
			} else if (request.getParameter("submit") != null && request.getParameter("submit").contains("Remove")) {
				removeCategory(request);
			}
			doGet(request, response);
		} catch (BLLException e) {
			// TODO error 500
			e.printStackTrace();
		} catch (IHMException e) {
			setFailure(request, e.getMessage());
			sendToJSP(OUTCOME_JSP, request, response);
			e.printStackTrace();
		}
	}

	private void setFailure(HttpServletRequest request, String message) {
		request.setAttribute(message, message);
		request.setAttribute("title", "Failed to remove category");
	}

	private void removeCategory(HttpServletRequest request) throws BLLException, IHMException {
		int categoryId = FormCleaner.cleanId(request.getParameter("category"));
		int substituteId = FormCleaner.cleanId(request.getParameter("substitute"));
		if (categoryId == substituteId)
			throw new IHMException("You cannot replace this category by itself...");
		auctionM.updateCategoryForAll(categoryId, substituteId);
		deleteCategory(categoryId);
	}

	private void deleteCategory(int categoryId) throws BLLException {
		Category categoryToDelete = categoryM.getById(categoryId);
		categoryM.delete(categoryToDelete);
	}

	private void createCategory(HttpServletRequest request) throws BLLException {
		String newCategory = FormCleaner.cleanText(request.getParameter("category"));
		categoryM.create(new Category(newCategory));
	}

	private void setCategories(HttpServletRequest request) throws BLLException {
		List<Category> categorieslist = catmng.getAll();
		request.setAttribute("categoriesList", categorieslist);
	}

}
