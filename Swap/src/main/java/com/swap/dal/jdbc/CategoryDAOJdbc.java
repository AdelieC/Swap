package com.swap.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.Category;
import com.swap.dal.CategoryDAO;
import com.swap.dal.DALException;

public class CategoryDAOJdbc implements CategoryDAO {
	private static String[] columns = { "category_id", "label" };
	private static String tableName = "CATEGORIES";

	@Override
	public void create(Category s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.insert(tableName, columns);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, s.getLabel());
			int nbRows = stmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = stmt.getGeneratedKeys();
				while (rs.next()) {
					s.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Category --" + s.getLabel() + "-- insertion failed", e);
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
	public List<Category> read() throws DALException {
		List<Category> list = new ArrayList<Category>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectAll(tableName);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("category_id");
				String label = result.getString("label");
				Category category = new Category(id, label);
				list.add(category);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Categories List failed ");
		}
		return list;
	}

	@Override
	public void update(Category s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.updateWhere(tableName, "category_id", columns);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, s.getLabel());
			stmt.setInt(2, s.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Category --" + s.getLabel() + "-- update failed", e);
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
	public void delete(Category s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.deleteWhere(tableName, "category_id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, s.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Category --" + s.getLabel() + "-- deletion failed", e);
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
	public Category selectById(int id) throws DALException {
		Category category = null;
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "category_id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			while (result.next()) {
				String label = result.getString("label");
				category = new Category(id, label);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Category by ID failed ");
		}
		return category;
	}

	@Override
	public Category selectByLabel(String label) throws DALException {
		Category category = null;
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "label");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, label);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("category_id");
				category = new Category(id, label);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Category by LABEL failed ");
		}
		return category;
	}

}
