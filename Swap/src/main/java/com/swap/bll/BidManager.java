package com.swap.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.Bid;
import com.swap.dal.BidDAO;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;

public class BidManager {
	private BidDAO bidDAO;

	public BidManager() {
		this.bidDAO = DAOFactory.getBidDAO();
	}

	public void create(Bid b) throws BLLException {
		if (isValid(b)) {
			try {
				bidDAO.create(b);
			} catch (DALException e) {
				throw new BLLException("BLL - CREATE BID failure");
			}
		}
	}

	public List<Bid> getAll() throws BLLException {
		List<Bid> list = new ArrayList<Bid>();
		try {
			list = bidDAO.read();
		} catch (DALException e) {
			throw new BLLException("BLL - READ BIDS failure");
		}
		return list;
	}

	public void update(Bid b) throws BLLException {
		if (isValid(b)) {
			try {
				this.bidDAO.update(b);
			} catch (DALException e) {
				throw new BLLException("BLL - UPDATE BID failure");
			}
		}
	}

	public void delete(Bid b) throws BLLException {
		try {
			this.bidDAO.delete(b);
		} catch (DALException e) {
			throw new BLLException("BLL - DELETE BID failure");
		}
	}

	public Bid getById(int id) throws BLLException {
		Bid bid = null;
		try {
			bid = bidDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("BLL - GET BID BY ID failure");
		}
		return bid;
	}

	public List<Bid> getByUserId(int id) throws BLLException {
		List<Bid> list = new ArrayList<Bid>();
		try {
			list = bidDAO.selectByUser(id);
		} catch (DALException e) {
			throw new BLLException("BLL - GET BIDS BY USER ID failure");
		}
		return list;
	}

	public List<Bid> getByItemId(int id) throws BLLException {
		List<Bid> list = new ArrayList<Bid>();
		try {
			list = bidDAO.selectByItemId(id);
		} catch (DALException e) {
			throw new BLLException("BLL - GET BIDS BY ITEM ID failure");
		}
		return list;
	}

	public List<Bid> getByPrice(int price) throws BLLException {
		List<Bid> list = new ArrayList<Bid>();
		try {
			list = bidDAO.selectByPrice(price);
		} catch (DALException e) {
			throw new BLLException("BLL - GET BIDS BY PRICE failure");
		}
		return list;
	}

	public List<Bid> getByDate(LocalDate date) throws BLLException {
		List<Bid> list = new ArrayList<Bid>();
		try {
			list = bidDAO.selectByDate(date);
		} catch (DALException e) {
			throw new BLLException("BLL - GET BIDS BY DATE failure");
		}
		return list;
	}

	private boolean isValid(Bid bid) {
		// TODO
		return true;
	}
}
