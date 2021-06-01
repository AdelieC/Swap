package com.swap.bll;

import java.time.LocalDate;
import java.util.List;

import com.swap.bo.Item;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;
import com.swap.dal.ItemDAO;

public class ItemManager {
	private ItemDAO itemDAO;

	public ItemManager() {
		this.itemDAO = DAOFactory.getItemDAO();
	}

	public void create(Item item) throws BLLException {
		if (isValid(item)) {
			try {
				this.itemDAO.create(item);
			} catch (DALException e) {
				throw new BLLException("BLL - CREATE ITEM failure");
			}
		}
	}

	public List<Item> getAll() throws BLLException {
		List<Item> list = null;
		try {
			list = this.itemDAO.read();
		} catch (DALException e) {
			throw new BLLException("BLL - GET ALL ITEMS failure");
		}
		return list;
	}

	public void update(Item item) throws BLLException {
		if (isValid(item)) {
			try {
				this.itemDAO.update(item);
			} catch (DALException e) {
				throw new BLLException("BLL - UPDATE ITEM failure");
			}
		}
	}

	public void delete(Item item) throws BLLException {
		try {
			this.itemDAO.delete(item);
		} catch (DALException e) {
			throw new BLLException("BLL - DELETE ITEM failure");
		}
	}

	public Item getById(int id) throws BLLException {
		Item item = null;
		try {
			item = this.itemDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("BLL - GET ITEM BY ID failure");
		}
		return item;
	}

	public List<Item> getByUserId(int userId) throws BLLException {
		List<Item> list = null;
		try {
			list = this.itemDAO.selectByUser(userId);
		} catch (DALException e) {
			throw new BLLException("BLL - GET ITEM BY USER ID failure");
		}
		return list;
	}

	public List<Item> getByPrice(int price) throws BLLException {
		List<Item> list = null;
		try {
			list = this.itemDAO.selectByPrice(price);
		} catch (DALException e) {
			throw new BLLException("BLL - GET ITEM BY USER ID failure");
		}
		return list;
	}

	public List<Item> getByName(String name) throws BLLException {
		List<Item> list = null;
		try {
			list = this.itemDAO.selectByName(name);
		} catch (DALException e) {
			throw new BLLException("BLL - GET ITEMS BY NAME failure");
		}
		return list;
	}

	public List<Item> getByKeyword(String keyword) throws BLLException {
		List<Item> list = null;
		try {
			list = this.itemDAO.selectByKeyword(keyword);
		} catch (DALException e) {
			throw new BLLException("BLL - GET ITEMS BY KEYWORD failure");
		}
		return list;
	}

	public List<Item> getByCategory(int category) throws BLLException {
		List<Item> list = null;
		try {
			list = this.itemDAO.selectByCategory(category);
		} catch (DALException e) {
			throw new BLLException("BLL - GET ITEMS BY CATEGORY failure");
		}
		return list;
	}

	public List<Item> getCurrentAuctions(LocalDate date) throws BLLException {
		List<Item> list = null;
		try {
			list = this.itemDAO.selectByDate(date);
		} catch (DALException e) {
			throw new BLLException("BLL - GET CURRENT AUCTIONS failure");
		}
		return list;
	}

	public List<Item> getCurrentAuctionsForUser(int userId, LocalDate date) throws BLLException {
		List<Item> list = null;
		try {
			list = this.itemDAO.selectByUserAndDate(userId, date);
		} catch (DALException e) {
			throw new BLLException("BLL - GET CURRENT AUCTIONS FOR USER " + userId + "failure");
		}
		return list;
	}

	private boolean isValid(Item item) {
		// TODO
		return true;
	}
}
