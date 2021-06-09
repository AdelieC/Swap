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
			"initial_price", "sale_price", "user_id", "category_id", "status" };
	private static final String tableName = "AUCTIONS";

	@Override
	public void create(Auction s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.insert(tableName, columns);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, s.getName());
			stmt.setString(2, s.getDescription());
			stmt.setDate(3, Date.valueOf(s.getStartDate()));
			stmt.setDate(4, Date.valueOf(s.getEndDate()));
			stmt.setInt(5, s.getInitialPrice());
			stmt.setInt(6, s.getSalePrice());
			stmt.setInt(7, s.getUserId());
			stmt.setInt(8, s.getCategoryId());
			stmt.setString(9, s.getStatus());
			int nbRows = stmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = stmt.getGeneratedKeys();
				while (rs.next()) {
					s.setId(rs.getInt(1));
				}
			}
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
				String status = result.getString("status");
				Auction auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice,
						salePrice, userId, status);
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
			stmt.setString(9, s.getStatus());
			stmt.setInt(10, s.getId());
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
			while (result.next()) {
				String name = result.getString("auction_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int userId = result.getInt("user_id");
				int categoryId = result.getInt("category_id");
				String status = result.getString("status");
				auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice,
						userId, status);
			}
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
		String query = DBUtils.searchBy(tableName, "auction_name");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, "%" + name + "%");
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("auction_id");
				name = result.getString("auction_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int userId = result.getInt("user_id");
				int categoryId = result.getInt("category_id");
				String status = result.getString("status");
				auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice,
						userId, status);
				list.add(auction);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Auctions by NAME failed ");
		}
		return list;
	}

	@Override
	public List<Auction> searchByNameAndCategory(String name, int categoryId) throws DALException {
		List<Auction> list = new ArrayList<Auction>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.twoCriteriaSearch(tableName, "auction_name", "category_id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, "%" + name + "%");
			stmt.setInt(2, categoryId);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("auction_id");
				name = result.getString("auction_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int userId = result.getInt("user_id");
				String status = result.getString("status");
				Auction auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice,
						salePrice, userId, status);
				list.add(auction);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Auctions by PRICE failed ");
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
				String status = result.getString("status");
				auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice,
						userId, status);
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
				String status = result.getString("status");
				Auction auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice,
						salePrice, userId, status);
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
				String status = result.getString("status");
				Auction auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice,
						salePrice, userId, status);
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
				String status = result.getString("status");
				Auction auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice,
						price, userId, status);
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
				String status = result.getString("status");
				Auction auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice,
						salePrice, userId, status);
				list.add(auction);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Auctions by PRICE failed ");
		}
		return list;
	}

	@Override
	public List<Auction> selectAllByStatus(String status) throws DALException {
		List<Auction> list = new ArrayList<Auction>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "status");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, status);
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
						salePrice, userId, status);
				list.add(auction);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Auctions by PRICE failed ");
		}
		return list;
	}

	@Override
	public List<Auction> selectAllNotOver() throws DALException {
		List<Auction> list = new ArrayList<Auction>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectWhereDifferent(tableName, "status");
		try {
			System.out.println(query);
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, "OVER");
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
				String status = result.getString("status");
				Auction auction = new Auction(id, name, description, startDate, endDate, categoryId, initialPrice,
						salePrice, userId, status);
				list.add(auction);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Auctions by PRICE failed ");
		}
		return list;
	}
}
