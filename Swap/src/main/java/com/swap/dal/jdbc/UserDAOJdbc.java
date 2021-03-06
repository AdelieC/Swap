package com.swap.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.User;
import com.swap.dal.DALException;
import com.swap.dal.UserDAO;

public class UserDAOJdbc implements UserDAO {
	private final static String[] COLS = { "user_id", "username", "last_name", "first_name", "email", "telephone",
			"street", "postcode", "city", "password", "salt", "balance", "is_admin", "was_disabled" };
	private final static String[] UPDATABLE_COLS = { "user_id", "username", "last_name", "first_name", "email",
			"telephone", "street", "postcode", "city" };
	private final static String TABLENAME = "USERS";

	@Override
	public void create(User u) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.insert(TABLENAME, COLS);
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getLastName());
			stmt.setString(3, u.getFirstName());
			stmt.setString(4, u.getEmail());
			stmt.setString(5, u.getTelephone());
			stmt.setString(6, u.getStreet());
			stmt.setString(7, u.getPostcode());
			stmt.setString(8, u.getCity());
			stmt.setString(9, u.getPassword());
			stmt.setString(10, u.getSalt());
			stmt.setInt(11, u.getBalance());
			stmt.setBoolean(12, u.isAdmin());
			stmt.setBoolean(13, u.wasDisabled());
			int nbRows = stmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet result = stmt.getGeneratedKeys();
				while (result.next()) {
					u.setUserId(result.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("User named " + u.getUsername() + "couldn't be inserted in dbTable USER", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public List<User> read() throws DALException {
		List<User> allUsers = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQLQuery = DBUtils.selectAll(TABLENAME);
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			result = stmt.executeQuery();
			while (result.next()) {
				int userId = result.getInt("user_id");
				String username = result.getString("username");
				String lastName = result.getString("last_name");
				String firstName = result.getString("first_name");
				String email = result.getString("email");
				String telephone = result.getString("telephone");
				String street = result.getString("street");
				String postcode = result.getString("postcode");
				String city = result.getString("city");
				String password = result.getString("password");
				String salt = result.getString("salt");
				int balance = result.getInt("balance");
				boolean isAdmin = result.getBoolean("is_admin");
				boolean wasDisabled = result.getBoolean("was_disabled");
				allUsers.add(new User(userId, username, lastName, firstName, email, telephone, street, postcode, city,
						password, salt, balance, isAdmin, wasDisabled));
			}
		} catch (SQLException e) {
			throw new DALException("Read failed - couldn't retrieve list of all users from db", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return allUsers;
	}

	@Override
	public void update(User u) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.updateWhere(TABLENAME, "user_id", UPDATABLE_COLS);
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getLastName());
			stmt.setString(3, u.getFirstName());
			stmt.setString(4, u.getEmail());
			stmt.setString(5, u.getTelephone());
			stmt.setString(6, u.getStreet());
			stmt.setString(7, u.getPostcode());
			stmt.setString(8, u.getCity());
			stmt.setInt(9, u.getUserId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("User named " + u.getUsername() + " couldn't be updated", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public void delete(int userId) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.deleteWhere(TABLENAME, "user_id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("User with id " + userId + " couldn't be deleted", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public void delete(User u) throws DALException {
		this.delete(u.getUserId());
	}

	@Override
	public User selectById(int id) throws DALException {
		User user = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQLQuery = DBUtils.selectBy(TABLENAME, "user_id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			if (result.next()) {
				int userId = result.getInt("user_id");
				String username = result.getString("username");
				String lastName = result.getString("last_name");
				String firstName = result.getString("first_name");
				String email = result.getString("email");
				String telephone = result.getString("telephone");
				String street = result.getString("street");
				String postcode = result.getString("postcode");
				String city = result.getString("city");
				String password = result.getString("password");
				String salt = result.getString("salt");
				int balance = result.getInt("balance");
				boolean isAdmin = result.getBoolean("is_admin");
				boolean wasDisabled = result.getBoolean("was_disabled");
				user = new User(userId, username, lastName, firstName, email, telephone, street, postcode, city,
						password, salt, balance, isAdmin, wasDisabled);
			}
		} catch (SQLException e) {
			throw new DALException("User with id " + id + " couldn't be fetched from dbTable USERS", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return user;
	}

	@Override
	public User selectByUsername(String username) throws DALException {
		User user = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQLQuery = DBUtils.selectBy(TABLENAME, "username");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setString(1, username);
			result = stmt.executeQuery();
			if (result.next()) {
				int userId = result.getInt("user_id");
				String lastName = result.getString("last_name");
				String firstName = result.getString("first_name");
				String email = result.getString("email");
				String telephone = result.getString("telephone");
				String street = result.getString("street");
				String postcode = result.getString("postcode");
				String city = result.getString("city");
				String password = result.getString("password");
				String salt = result.getString("salt");
				int balance = result.getInt("balance");
				boolean isAdmin = result.getBoolean("is_admin");
				boolean wasDisabled = result.getBoolean("was_disabled");
				user = new User(userId, username, lastName, firstName, email, telephone, street, postcode, city,
						password, salt, balance, isAdmin, wasDisabled);
			}
		} catch (SQLException e) {
			throw new DALException("User with name " + username + " couldn't be fetched from dbTable USERS", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return user;
	}

	@Override
	public List<User> searchByUsername(String username) throws DALException {
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQLQuery = DBUtils.searchBy(TABLENAME, "username");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setString(1, "%" + username + "%");
			result = stmt.executeQuery();
			while (result.next()) {
				int userId = result.getInt("user_id");
				String lastName = result.getString("last_name");
				String firstName = result.getString("first_name");
				String email = result.getString("email");
				String telephone = result.getString("telephone");
				String street = result.getString("street");
				String postcode = result.getString("postcode");
				String city = result.getString("city");
				String password = result.getString("password");
				String salt = result.getString("salt");
				int balance = result.getInt("balance");
				boolean isAdmin = result.getBoolean("is_admin");
				boolean wasDisabled = result.getBoolean("was_disabled");
				users.add(new User(userId, username, lastName, firstName, email, telephone, street, postcode, city,
						password, salt, balance, isAdmin, wasDisabled));
			}
		} catch (SQLException e) {
			throw new DALException("User with name " + username + " couldn't be fetched from dbTable USERS", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return users;
	}

	@Override
	public List<User> searchByCity(String city) throws DALException {
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQLQuery = DBUtils.searchBy(TABLENAME, "city");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setString(1, "%" + city + "%");
			result = stmt.executeQuery();
			while (result.next()) {
				int userId = result.getInt("user_id");
				String username = result.getString("username");
				String lastName = result.getString("last_name");
				String firstName = result.getString("first_name");
				String email = result.getString("email");
				String telephone = result.getString("telephone");
				String street = result.getString("street");
				String postcode = result.getString("postcode");
				String password = result.getString("password");
				String salt = result.getString("salt");
				int balance = result.getInt("balance");
				boolean isAdmin = result.getBoolean("is_admin");
				boolean wasDisabled = result.getBoolean("was_disabled");
				users.add(new User(userId, username, lastName, firstName, email, telephone, street, postcode, city,
						password, salt, balance, isAdmin, wasDisabled));
			}
		} catch (SQLException e) {
			throw new DALException("Read failed - couldn't retrieve list of users from city " + city, e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return users;
	}

	@Override
	public List<User> selectAllAdmins() throws DALException {
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQLQuery = DBUtils.selectBy(TABLENAME, "is_admin");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, 1);
			result = stmt.executeQuery();
			while (result.next()) {
				int userId = result.getInt("user_id");
				String username = result.getString("username");
				String lastName = result.getString("last_name");
				String firstName = result.getString("first_name");
				String email = result.getString("email");
				String telephone = result.getString("telephone");
				String street = result.getString("street");
				String postcode = result.getString("postcode");
				String city = result.getString("city");
				String password = result.getString("password");
				String salt = result.getString("salt");
				int balance = result.getInt("balance");
				boolean isAdmin = result.getBoolean("is_admin");
				boolean wasDisabled = result.getBoolean("was_disabled");
				users.add(new User(userId, username, lastName, firstName, email, telephone, street, postcode, city,
						password, salt, balance, isAdmin, wasDisabled));
			}
		} catch (SQLException e) {
			throw new DALException("Read failed - couldn't retrieve list of all admin users ", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return users;
	}

	@Override
	public void updatePasswordAndSalt(User u) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.updateTwoColsWhere(TABLENAME, "password", "salt", "user_id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setString(1, u.getPassword());
			stmt.setString(2, u.getSalt());
			stmt.setInt(3, u.getUserId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Couldn't update password for User named " + u.getUsername(), e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}

	}

	@Override
	public boolean successfullySetPasswordData(User u) throws DALException {
		Boolean found = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQLQuery = DBUtils.getTwoColsBy(TABLENAME, "password", "salt", "username");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setString(1, u.getUsername());
			result = stmt.executeQuery();
			found = result.next();
			if (found) {
				u.setPassword(result.getString("password"));
				u.setSalt(result.getString("salt"));
			}
		} catch (SQLException e) {
			throw new DALException("User with name " + u.getUsername() + " wasn't found", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return found;
	}

	@Override
	public boolean exists(User u) throws DALException {
		Boolean found = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQLQuery = DBUtils.twoCriteriaSearch(TABLENAME, "username", "email");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getEmail());
			result = stmt.executeQuery();
			found = result.next();
		} catch (SQLException e) {
			throw new DALException("User with name " + u.getUsername() + " wasn't found", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return found;
	}

	@Override
	public void updateBalance(int userId, int newBalance) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.updateWhere(TABLENAME, "balance", "user_id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, newBalance);
			stmt.setInt(2, userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Failed to update user with id " + userId + "'s balance.", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public void updateWasDisabled(User user, boolean disable) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.updateWhere(TABLENAME, "was_disabled", "user_id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setBoolean(1, disable);
			stmt.setInt(2, user.getUserId());
			stmt.executeUpdate();
			user.setWasDisabled(disable);
		} catch (SQLException e) {
			throw new DALException("Failed to disable/enable user with id " + user.getUserId(), e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
	}
}
