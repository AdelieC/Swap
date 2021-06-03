package com.swap.bll;

import java.time.LocalDate;
import java.util.List;

import com.swap.bo.Auction;
import com.swap.dal.AuctionDAO;
import com.swap.dal.DALException;
import com.swap.dal.DAOFactory;

public class AuctionManager {
	private AuctionDAO auctionDAO;

	public AuctionManager() {
		this.auctionDAO = DAOFactory.getAuctionDAO();
	}

	public void create(Auction auction) throws BLLException {
		if (isValid(auction)) {
			try {
				this.auctionDAO.create(auction);
			} catch (DALException e) {
				throw new BLLException("CREATE AUCTION failure");
			}
		}
	}

	public List<Auction> getAll() throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.read();
		} catch (DALException e) {
			throw new BLLException("GET ALL AUCTIONS failure");
		}
		return list;
	}

	public void update(Auction auction) throws BLLException {
		if (isValid(auction)) {
			try {
				this.auctionDAO.update(auction);
			} catch (DALException e) {
				throw new BLLException("UPDATE AUCTION failure");
			}
		}
	}

	public void delete(Auction auction) throws BLLException {
		try {
			this.auctionDAO.delete(auction);
		} catch (DALException e) {
			throw new BLLException("DELETE AUCTION failure");
		}
	}

	public Auction getById(int id) throws BLLException {
		Auction auction = null;
		try {
			auction = this.auctionDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("GET AUCTION BY ID failure");
		}
		return auction;
	}

	public List<Auction> getByUserId(int userId) throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.selectByUser(userId);
		} catch (DALException e) {
			throw new BLLException("GET AUCTION BY USER ID failure");
		}
		return list;
	}

	public List<Auction> getByPrice(int price) throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.selectByPrice(price);
		} catch (DALException e) {
			throw new BLLException("GET AUCTION BY USER ID failure");
		}
		return list;
	}

	public List<Auction> getByName(String name) throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.selectByName(name);
		} catch (DALException e) {
			throw new BLLException("GET AUCTIONS BY NAME failure");
		}
		return list;
	}

	public List<Auction> getByKeyword(String keyword) throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.selectByKeyword(keyword);
		} catch (DALException e) {
			throw new BLLException("GET AUCTIONS BY KEYWORD failure");
		}
		return list;
	}

	public List<Auction> getByCategory(int category) throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.selectByCategory(category);
		} catch (DALException e) {
			throw new BLLException("GET AUCTIONS BY CATEGORY failure");
		}
		return list;
	}

	public List<Auction> getCurrentAuctions(LocalDate date) throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.selectByDate(date);
		} catch (DALException e) {
			throw new BLLException("GET CURRENT AUCTIONS failure");
		}
		return list;
	}

	public List<Auction> getCurrentAuctionsForUser(int userId, LocalDate date) throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.selectByUserAndDate(userId, date);
		} catch (DALException e) {
			throw new BLLException("GET CURRENT AUCTIONS FOR USER " + userId + "failure");
		}
		return list;
	}

	private boolean isValid(Auction auction) {
		// TODO
		return true;
	}
}
