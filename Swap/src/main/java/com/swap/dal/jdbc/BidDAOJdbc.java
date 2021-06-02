package com.swap.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.Bid;
import com.swap.dal.BidDAO;
import com.swap.dal.DALException;

public class BidDAOJdbc implements BidDAO {
	private static String[] columns = { "bid_id", "user_id", "auction_id", "bid_date", "bid_price" };
	private static String tableName = "BIDS";

	@Override
	public void create(Bid s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.insert(tableName, columns);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, s.getUserId());
			stmt.setInt(2, s.getAuctionId());
			stmt.setDate(3, Date.valueOf(s.getDate()));
			stmt.setInt(4, s.getBidPrice());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Bid --" + s.getId() + "-- insertion failed", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				cn.close();
			} catch (SQLException e) {
				e.getStackTrace();
			}
		}
	}

	@Override
	public List<Bid> read() throws DALException {
		List<Bid> list = new ArrayList<Bid>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectAll(tableName);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("bid_id");
				int bidPrice = result.getInt("bid_price");
				int userId = result.getInt("user_id");
				int auctionId = result.getInt("auction_id");
				LocalDate date = result.getDate("date").toLocalDate();
				Bid bid = new Bid(id, userId, auctionId, bidPrice, date);
				list.add(bid);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Bids List failed ");
		}
		return list;
	}

	@Override
	public void update(Bid s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.updateWhere(tableName, "bid_id", columns);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, s.getId());
			stmt.setInt(2, s.getUserId());
			stmt.setInt(3, s.getAuctionId());
			stmt.setDate(4, Date.valueOf(s.getDate()));
			stmt.setInt(5, s.getBidPrice());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Bid --" + s.getId() + "-- update failed", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				cn.close();
			} catch (SQLException e) {
				e.getStackTrace();
			}
		}
	}

	@Override
	public void delete(Bid s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.deleteWhere(tableName, "bid_id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, s.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Bid --" + s.getId() + "-- deletion failed", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				cn.close();
			} catch (SQLException e) {
				e.getStackTrace();
			}
		}
	}

	@Override
	public Bid selectById(int id) throws DALException {
		Bid bid = null;
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "bid_id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			while (result.next()) {
				int bidPrice = result.getInt("bid_price");
				int userId = result.getInt("user_id");
				int auctionId = result.getInt("auction_id");
				LocalDate date = result.getDate("date").toLocalDate();
				bid = new Bid(id, userId, auctionId, bidPrice, date);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Bid by ID failed ");
		}
		return bid;
	}

	@Override
	public List<Bid> selectByUser(int userId) throws DALException {
		List<Bid> list = new ArrayList<Bid>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "user_id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, userId);
			result = stmt.executeQuery();
			while (result.next()) {
				int bidPrice = result.getInt("bid_price");
				int id = result.getInt("bid_id");
				int auctionId = result.getInt("auction_id");
				LocalDate date = result.getDate("date").toLocalDate();
				Bid bid = new Bid(id, userId, auctionId, bidPrice, date);
				list.add(bid);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Bids by USER failed ");
		}
		return list;
	}

	@Override
	public List<Bid> selectByAuctionId(int auctionId) throws DALException {
		List<Bid> list = new ArrayList<Bid>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "auction_id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, auctionId);
			result = stmt.executeQuery();
			while (result.next()) {
				int bidPrice = result.getInt("bid_price");
				int id = result.getInt("bid_id");
				int userId = result.getInt("user_id");
				LocalDate date = result.getDate("date").toLocalDate();
				Bid bid = new Bid(id, userId, auctionId, bidPrice, date);
				list.add(bid);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Bids by AUCTION ID failed ");
		}
		return list;
	}

	@Override
	public List<Bid> selectByPrice(int price) throws DALException {
		List<Bid> list = new ArrayList<Bid>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "bid_price");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, price);
			result = stmt.executeQuery();
			while (result.next()) {
				int auctionId = result.getInt("auction_id");
				int id = result.getInt("bid_id");
				int userId = result.getInt("user_id");
				LocalDate date = result.getDate("date").toLocalDate();
				Bid bid = new Bid(id, userId, auctionId, price, date);
				list.add(bid);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Bids by PRICE failed ");
		}
		return list;
	}

	@Override
	public List<Bid> selectByDate(LocalDate date) throws DALException {
		List<Bid> list = new ArrayList<Bid>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "date");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setDate(1, Date.valueOf(date));
			result = stmt.executeQuery();
			while (result.next()) {
				int auctionId = result.getInt("auction_id");
				int id = result.getInt("bid_id");
				int userId = result.getInt("user_id");
				int bidPrice = result.getInt("bid_price");
				Bid bid = new Bid(id, userId, auctionId, bidPrice, date);
				list.add(bid);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Bids by DATE failed ");
		}
		return list;
	}

}
