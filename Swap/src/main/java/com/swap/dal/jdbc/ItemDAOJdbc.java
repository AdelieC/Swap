package com.swap.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.Item;
import com.swap.dal.DALException;
import com.swap.dal.ItemDAO;

public class ItemDAOJdbc implements ItemDAO {
	private static String[] columns = { "item_id", "item_name", "description", "start_date", "end_date",
			"initial_price", "sale_price", "user_id", "category_id" };
	private static String tableName = "ITEMS";

	@Override
	public void create(Item s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.insert(tableName, columns);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, s.getId());
			stmt.setString(2, s.getName());
			stmt.setString(3, s.getDescription());
			stmt.setDate(4, Date.valueOf(s.getStartDate()));
			stmt.setDate(5, Date.valueOf(s.getEndDate()));
			stmt.setInt(6, s.getInitialPrice());
			stmt.setInt(7, s.getSalePrice());
			stmt.setInt(8, s.getUserId());
			stmt.setInt(9, s.getCategoryId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("item --" + s.getName() + "-- insertion failed", e);
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
	public List<Item> read() throws DALException {
		List<Item> list = new ArrayList<Item>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectAll(tableName);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("item_id");
				String name = result.getString("item_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int userId = result.getInt("user_id");
				int categoryId = result.getInt("category_id");
				Item item = new Item(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice,
						userId);
				list.add(item);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Items List failed ");
		}
		return list;
	}

	@Override
	public void update(Item s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.updateWhere(tableName, "item_id", columns);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, s.getId());
			stmt.setString(2, s.getName());
			stmt.setString(3, s.getDescription());
			stmt.setDate(4, Date.valueOf(s.getStartDate()));
			stmt.setDate(5, Date.valueOf(s.getEndDate()));
			stmt.setInt(6, s.getInitialPrice());
			stmt.setInt(7, s.getSalePrice());
			stmt.setInt(8, s.getUserId());
			stmt.setInt(9, s.getCategoryId());
			stmt.setInt(10, s.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("item --" + s.getName() + "-- update failed", e);
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
	public void delete(Item s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.deleteWhere(tableName, "item_id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, s.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("item --" + s.getName() + "-- deletion failed", e);
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
	public Item selectById(int id) throws DALException {
		Item item = null;
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "item_id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			String name = result.getString("item_name");
			String description = result.getString("description");
			LocalDate startDate = result.getDate("start_date").toLocalDate();
			LocalDate endDate = result.getDate("end_date").toLocalDate();
			int initialPrice = result.getInt("initial_price");
			int salePrice = result.getInt("sale_price");
			int userId = result.getInt("user_id");
			int categoryId = result.getInt("category_id");
			item = new Item(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice, userId);
		} catch (SQLException e) {
			throw new DALException("READ - Item by ID failed ");
		}
		return item;
	}

	@Override
	public List<Item> selectByName(String name) throws DALException {
		List<Item> list = new ArrayList<Item>();
		Item item = null;
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "item_name");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, name);
			result = stmt.executeQuery();
			int id = result.getInt("item_id");
			String description = result.getString("description");
			LocalDate startDate = result.getDate("start_date").toLocalDate();
			LocalDate endDate = result.getDate("end_date").toLocalDate();
			int initialPrice = result.getInt("initial_price");
			int salePrice = result.getInt("sale_price");
			int userId = result.getInt("user_id");
			int categoryId = result.getInt("category_id");
			item = new Item(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice, userId);
			list.add(item);
		} catch (SQLException e) {
			throw new DALException("READ - Items by NAME failed ");
		}
		return list;
	}

	@Override
	public List<Item> selectByKeyword(String keyword) throws DALException {
		List<Item> list = new ArrayList<Item>();
		Item item = null;
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
				int id = result.getInt("item_id");
				String name = result.getString("name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int userId = result.getInt("user_id");
				int categoryId = result.getInt("category_id");
				item = new Item(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice, userId);
				list.add(item);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Items by KEYWORD failed ");
		}
		return list;
	}

	@Override
	public List<Item> selectByCategory(int categoryId) throws DALException {
		List<Item> list = new ArrayList<Item>();
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
				int id = result.getInt("item_id");
				String name = result.getString("item_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int userId = result.getInt("user_id");
				Item item = new Item(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice,
						userId);
				list.add(item);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Items by CATEGORY failed ");
		}
		return list;
	}

	@Override
	public List<Item> selectByUser(int userId) throws DALException {
		List<Item> list = new ArrayList<Item>();
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
				int id = result.getInt("item_id");
				String name = result.getString("item_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int categoryId = result.getInt("category_id");
				Item item = new Item(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice,
						userId);
				list.add(item);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Items by USER failed ");
		}
		return list;
	}

	@Override
	public List<Item> selectByPrice(int price) throws DALException {
		List<Item> list = new ArrayList<Item>();
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
				int id = result.getInt("item_id");
				String name = result.getString("item_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int categoryId = result.getInt("category_id");
				int userId = result.getInt("user_id");
				Item item = new Item(id, name, description, startDate, endDate, categoryId, initialPrice, price,
						userId);
				list.add(item);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Items by PRICE failed ");
		}
		return list;
	}

	@Override
	public List<Item> selectByDate(LocalDate date) throws DALException {
		List<Item> list = new ArrayList<Item>();
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
				int id = result.getInt("item_id");
				String name = result.getString("item_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int categoryId = result.getInt("category_id");
				int userId = result.getInt("user_id");
				Item item = new Item(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice,
						userId);
				list.add(item);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Items by PRICE failed ");
		}
		return list;
	}

	@Override
	public List<Item> selectByUserAndDate(int userId, LocalDate date) throws DALException {
		List<Item> list = new ArrayList<Item>();
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
				int id = result.getInt("item_id");
				String name = result.getString("item_name");
				String description = result.getString("description");
				LocalDate startDate = result.getDate("start_date").toLocalDate();
				LocalDate endDate = result.getDate("end_date").toLocalDate();
				int initialPrice = result.getInt("initial_price");
				int salePrice = result.getInt("sale_price");
				int categoryId = result.getInt("category_id");
				Item item = new Item(id, name, description, startDate, endDate, categoryId, initialPrice, salePrice,
						userId);
				list.add(item);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Items by PRICE failed ");
		}
		return list;
	}
}
