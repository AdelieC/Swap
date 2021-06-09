package com.swap.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.swap.dal.DALException;

public class DBUtils {
	private DBUtils() {

	}

	public static String selectAll(String tableName) {
		return "SELECT * FROM " + tableName;
	}

	public static String selectBy(String tableName, String col) {
		return "SELECT * FROM " + tableName + " WHERE " + col + " = ?";
	}

	public static String innerJoinTwoCols(String tableName, String col1, String col2) {
		// common column MUST HAVE SAME NAME!
		return " INNER JOIN " + tableName + " WHERE " + col1 + " = " + col1 + " AND " + col2 + " = ?";
	}

	public static String selectWhereDifferent(String tableName, String col) {
		return "SELECT * FROM " + tableName + " WHERE " + col + " != ?";
	}

	public static String selectWhereNotIn(String tableName, String col) {
		return "SELECT * FROM " + tableName + " WHERE " + col + " NOT IN(?, ?)";
	}

	public static String selectWhereAndWhereNot(String tableName, String col, String colNot) {
		return "SELECT * FROM " + tableName + " WHERE " + col + " = ? AND " + colNot + " != ?";
	}

	public static String selectByTwoCols(String tableName, String col1, String col2) {
		return "SELECT * FROM " + tableName + " WHERE " + col1 + " = ? AND " + col2 + " = ?";
	}

	public static String selectWhereBetween(String tableName, String col) {
		return "SELECT * FROM " + tableName + " WHERE " + col + " BETWEEN (? AND ?)";
	}

	public static String searchBy(String tableName, String col) {
		return "SELECT * FROM " + tableName + " WHERE " + col + " LIKE ? ESCAPE '!'";
		// you need to set your string like this in the preparedstatement
		// -> setString(1, "%" + yourVariable + "%")
	}

	public static String findExactMatchIn(String tableName, String[] columns) {
		String matches = columns[0] + " = ?";
		for (int i = 1; i < columns.length; i++)
			matches += (" AND " + columns[i] + " = ?");
		return "SELECT " + columns[0] + " FROM " + tableName + " WHERE " + matches;
	}

	public static String twoCriteriaSearch(String tableName, String col1, String col2) {
		return "SELECT * FROM " + tableName + " WHERE " + col1 + " LIKE ? OR " + col2 + " LIKE ?";
	}

	public static String insert(String tableName, String[] columns) {
		String values = "?", cols = columns[1];
		for (int i = 2; i < columns.length; i++)
			values += ",?";
		for (int i = 2; i < columns.length; i++)
			cols += ("," + columns[i]);
		return "INSERT INTO " + tableName + "(" + cols + ") VALUES(" + values + ")";
	}

	public static String updateWhere(String tableName, String colId, String[] columns) {
		String cols = columns[1] + " = ?";
		for (int i = 2; i < columns.length; i++)
			cols += (", " + columns[i] + " = ?");
		return "UPDATE " + tableName + " SET " + cols + " WHERE " + colId + " = ?";
	}

	public static String updateWhere(String tableName, String colId, String colToUpdate) {
		return "UPDATE " + tableName + " SET " + colToUpdate + "= ? WHERE " + colId + " = ?";
	}

	public static String deleteWhere(String tableName, String col) {
		return "DELETE FROM " + tableName + " WHERE " + col + " = ?";
	}

	public static void closeConnection(Connection conn) throws DALException {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DALException("Failed to close connection - ", e);
			}
			conn = null;
		}
	}

	public static void closePrepStmt(PreparedStatement rqt) throws DALException {
		try {
			if (rqt != null)
				rqt.close();
		} catch (SQLException e) {
			throw new DALException("Failed to close prepared statement - ", e);
		}
	}

}
