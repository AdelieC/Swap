package com.swap.bll;

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

	public List<Auction> getByNameAndCategory(String name, int categoryId) throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.searchByNameAndCategory(name, categoryId);
		} catch (DALException e) {
			throw new BLLException("GET AUCTIONS BY NAME AND CATEGORY failure");
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

	public List<Auction> getByStatus(String status) throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.selectAllByStatus(status);
		} catch (DALException e) {
			throw new BLLException("GET AUCTIONS BY STATUS failure");
		}
		return list;
	}

	public List<Auction> getByStatusAndUser(String status, int userId) throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.selectByStatusAndUser(status, userId);
		} catch (DALException e) {
			throw new BLLException("GET AUCTIONS BY WINNER failure");
		}
		return list;
	}

	public List<Auction> getAllNotOver() throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.selectAllNotOver();
		} catch (DALException e) {
			throw new BLLException("GET AUCTIONS NOT OVER failure");
		}
		return list;
	}

	public List<Auction> getNotOverByUser(int userId) throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.selectNotOverByUser(userId);
		} catch (DALException e) {
			throw new BLLException("GET AUCTIONS NOT OVER BY USER failure");
		}
		return list;
	}

	public List<Auction> getByWinner(int userId) throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.selectByWinner(userId);
		} catch (DALException e) {
			throw new BLLException("GET AUCTIONS BY WINNER failure");
		}
		return list;
	}

	private boolean isValid(Auction auction) {
		// TODO
		return true;
	}

	public List<Auction> getNotOverByCategory(int categoryId) throws BLLException {
		List<Auction> list = null;
		try {
			list = this.auctionDAO.selectNotOverByCategory(categoryId);
		} catch (DALException e) {
			throw new BLLException("GET AUCTIONS BY WINNER failure");
		}
		return list;
	}

	public List<Auction> getNotOverByNameAndCategory(String q, int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Auction> getNotOverByName(String q) {
		// TODO Auto-generated method stub
		return null;
	}

}
