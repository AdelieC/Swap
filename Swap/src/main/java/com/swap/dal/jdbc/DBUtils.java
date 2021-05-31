package com.swap.dal.jdbc;

public class DBUtils {
	private DBUtils() {

	}

	public static String selectAll(String tableName) {
		return "SELECT * FROM " + tableName;
	}

	public static String selectBy(String tableName, String col) {
		return "SELECT * FROM " + tableName + " WHERE " + col + " = ?";
	}

	public static String selectWhereBetween(String tableName, String col) {
		return "SELECT * FROM " + tableName + " WHERE " + col + " BETWEEN (? AND ?)";
	}

	public static String searchBy(String tableName, String col) {
		return "SELECT * FROM " + tableName + " WHERE " + col + " LIKE ?";
	}

	public static String twoCriteriasSearch(String tableName, String col1, String col2) {
		return "SELECT * FROM " + tableName + " WHERE " + col1 + " LIKE ? OR " + col2 + " LIKE ?";
	}

	public static String insert(String tableName, String[] columns) {
		String values = "?", cols = columns[0];
		for (int i = 1; i < columns.length; i++)
			values += ",?";
		for (int i = 1; i < columns.length; i++)
			cols += ("," + columns[i]);
		return "INSERT INTO " + tableName + "(" + cols + ") VALUES(" + values + ")";
	}

	public static String updateWhere(String tableName, String colId, String[] columns) {
		String cols = columns[0] + " = ?";
		for (int i = 1; i < columns.length - 1; i++)
			cols += (", " + columns[i] + " = ?");
		return "UPDATE " + tableName + " SET " + cols + " WHERE " + colId + " = ?";
	}

	public static String deleteWhere(String tableName, String col) {
		return "DELETE FROM " + tableName + " WHERE " + col + " = ?";
	}
}