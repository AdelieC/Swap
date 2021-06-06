package com.swap.bll;

import java.util.ArrayList;
import java.util.List;

import com.swap.bo.Category;
import com.swap.dal.CategoryDAO;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;

public class CategoryManager {
	private CategoryDAO categoryDAO;

	public CategoryManager() {
		this.categoryDAO = DAOFactory.getCategoryDAO();
	}

	public void create(Category c) throws BLLException {
		if (isValid(c)) {
			try {
				categoryDAO.create(c);
			} catch (DALException e) {
				throw new BLLException("BLL - CREATE CATEGORY failure");
			}
		}
	}

	public List<Category> getAll() throws BLLException {
		List<Category> list = new ArrayList<Category>();
		try {
			list = categoryDAO.read();
		} catch (DALException e) {
			throw new BLLException("BLL - READ CATEGORIES failure");
		}
		return list;
	}

	public void update(Category c) throws BLLException {
		if (isValid(c)) {
			try {
				this.categoryDAO.update(c);
			} catch (DALException e) {
				throw new BLLException("BLL - UPDATE CATEGORY failure");
			}
		}
	}

	public void delete(Category c) throws BLLException {
		try {
			this.categoryDAO.delete(c);
		} catch (DALException e) {
			throw new BLLException("BLL - DELETE CATEGORY failure");
		}
	}

	public Category getById(int id) throws BLLException {
		Category cat = null;
		try {
			cat = categoryDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("BLL - GET CATEGORY BY ID failure");
		}
		return cat;
	}

	private boolean isValid(Category c) {
		if (isValidLabel(c.getLabel())) {
			return true;
		}
		return false;
	}

	private boolean isValidLabel(String label) {
		// TODO
		if (label.length() > 0) {
			return true;
		}
		return false;
	}
}
