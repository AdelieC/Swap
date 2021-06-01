package com.swap.dal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * IMPORTANT : initial setup was done for mariadb. To change to preconfigured
 * MysqlServer db, you need to change path for context.lookup. That's why I
 * declared it as a variable.
 *
 */
abstract class ConnectionProvider {
	private final static String CONTEXT_PATH = "java:comp/env/jdbc/pool_mariadb";
	// for sqlserver, change "pool_mariadb" into "pool_cnx" (that's the name of the
	// sqlserver resource in context.xml)
	private static DataSource dataSource;

	static {
		Context context;
		try {
			context = new InitialContext();
			ConnectionProvider.dataSource = (DataSource) context.lookup(CONTEXT_PATH);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Database can't be accessed");
		}
	}

	public static Connection getConnection() throws SQLException {
		return ConnectionProvider.dataSource.getConnection();
	}
}
