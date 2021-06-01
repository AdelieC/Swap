package com.swap.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.Auction;
import com.swap.dal.AuctionDAO;
import com.swap.dal.DALException;

public class AuctionDAOJdbc implements AuctionDAO {
	private static final String[] columns = { "auction_id", "auction_name", "description", "start_date", "end_date",
			"initial_price", "sale_price", "user_id", "category_id" };
	private static final String tableName = "AUCTIONS";

	@Override
	public void create(Auction s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.insert(tableName, columns);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, s.getName());
			stmt.setString(2, s.getDescription());
			stmt.setDate(3, Date.valueOf(s.getStartDate()));
			stmt.setDate(4, Date.valueOf(s.getEndDate()));
			stmt.setInt(5, s.getInitialPrice());
			stmt.setInt(6, s.getSalePrice());
			stmt.setInt(7, s.getUserId());
			stmt.setInt(8, s.getCategoryId());
			System.out.println(s.getEndDate());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("auction --" + s.getName() + "-- insertion failed", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException e) {
				e.getStackTrace();
			}
		}
	}

	@Override
	public List<Auction> read() throws DALException {
		List<Auction> list = new ArrayList<Auction>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectAll(tableName);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("auction_id");
				String name = result.getString("auction_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int userId = result.getInt("user_id");
				int categoryId = result.getInt("category_id");
				Auction auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice,
						salePrice, userId);
				list.add(auction);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Auctions List failed ");
		}
		return list;
	}

	@Override
	public void update(Auction s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.updateWhere(tableName, "auction_id", columns);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, s.getName());
			stmt.setString(2, s.getDescription());
			stmt.setDate(3, Date.valueOf(s.getStartDate()));
			stmt.setDate(4, Date.valueOf(s.getEndDate()));
			stmt.setInt(5, s.getInitialPrice());
			stmt.setInt(6, s.getSalePrice());
			stmt.setInt(7, s.getUserId());
			stmt.setInt(8, s.getCategoryId());
			stmt.setInt(9, s.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("auction --" + s.getName() + "-- update failed", e);
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
	public void delete(Auction s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.deleteWhere(tableName, "auction_id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, s.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("auction --" + s.getName() + "-- deletion failed", e);
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
	public Auction selectById(int id) throws DALException {
		Auction auction = null;
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "auction_id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			String name = result.getString("auction_name");
			String description = result.getString("description");
			LocalDate startDate = result.getDate("start_date").toLocalDate();
			LocalDate endDate = result.getDate("end_date").toLocalDate();
			int initialPrice = result.getInt("initial_price");
			int salePrice = result.getInt("sale_price");
			int userId = result.getInt("user_id");
			int categoryId = result.getInt("category_id");
			auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice,
					userId);
		} catch (SQLException e) {
			throw new DALException("READ - Auction by ID failed ");
		}
		return auction;
	}

	@Override
	public List<Auction> selectByName(String name) throws DALException {
		List<Auction> list = new ArrayList<Auction>();
		Auction auction = null;
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "auction_name");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, name);
			result = stmt.executeQuery();
			int id = result.getInt("auction_id");
			String description = result.getString("description");
			LocalDate startDate = result.getDate("start_date").toLocalDate();
			LocalDate endDate = result.getDate("end_date").toLocalDate();
			int initialPrice = result.getInt("initial_price");
			int salePrice = result.getInt("sale_price");
			int userId = result.getInt("user_id");
			int categoryId = result.getInt("category_id");
			auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice,
					userId);
			list.add(auction);
		} catch (SQLException e) {
			throw new DALException("READ - Auctions by NAME failed ");
		}
		return list;
	}

	@Override
	public List<Auction> selectByKeyword(String keyword) throws DALException {
		List<Auction> list = new ArrayList<Auction>();
		Auction auction = null;
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.searchBy(tableName, "description");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, keyword);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("auction_id");
				String name = result.getString("name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int userId = result.getInt("user_id");
				int categoryId = result.getInt("category_id");
				auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice,
						userId);
				list.add(auction);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Auctions by KEYWORD failed ");
		}
		return list;
	}

	@Override
	public List<Auction> selectByCategory(int categoryId) throws DALException {
		List<Auction> list = new ArrayList<Auction>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "category_id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, categoryId);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("auction_id");
				String name = result.getString("auction_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int userId = result.getInt("user_id");
				Auction auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice,
						salePrice, userId);
				list.add(auction);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Auctions by CATEGORY failed ");
		}
		return list;
	}

	@Override
	public List<Auction> selectByUser(int userId) throws DALException {
		List<Auction> list = new ArrayList<Auction>();
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
				int id = result.getInt("auction_id");
				String name = result.getString("auction_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int categoryId = result.getInt("category_id");
				Auction auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice,
						salePrice, userId);
				list.add(auction);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Auctions by USER failed ");
		}
		return list;
	}

	@Override
	public List<Auction> selectByPrice(int price) throws DALException {
		List<Auction> list = new ArrayList<Auction>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "sale_price");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, price);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("auction_id");
				String name = result.getString("auction_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int categoryId = result.getInt("category_id");
				int userId = result.getInt("user_id");
				Auction auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice,
						price, userId);
				list.add(auction);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Auctions by PRICE failed ");
		}
		return list;
	}

	@Override
	public List<Auction> selectByDate(LocalDate date) throws DALException {
		List<Auction> list = new ArrayList<Auction>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectWhereBetween(tableName, "end_date");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setDate(1, Date.valueOf(LocalDate.now()));
			stmt.setDate(2, Date.valueOf(date));
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("auction_id");
				String name = result.getString("auction_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int categoryId = result.getInt("category_id");
				int userId = result.getInt("user_id");
				Auction auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice,
						salePrice, userId);
				list.add(auction);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Auctions by PRICE failed ");
		}
		return list;
	}

	@Override
	public List<Auction> selectByUserAndDate(int userId, LocalDate date) throws DALException {
		List<Auction> list = new ArrayList<Auction>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectWhereBetween(tableName, "end_date") + " AND user_id=?";
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setDate(1, Date.valueOf(LocalDate.now()));
			stmt.setDate(2, Date.valueOf(date));
			stmt.setInt(3, userId);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("auction_id");
				String name = result.getString("auction_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int categoryId = result.getInt("category_id");
				Auction auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice,
						salePrice, userId);
				list.add(auction);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Auctions by PRICE failed ");
		}
		return list;
	}
}
