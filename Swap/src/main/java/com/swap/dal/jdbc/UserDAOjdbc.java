package com.swap.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.User;
import com.swap.dal.DALException;
import com.swap.dal.UserDAO;

public class UserDAOjdbc implements UserDAO {
	private final static String[] COLS = { "username", "last_name", "first_name", "email", "telephone", "street_nb",
			"street", "postcode", "city", "password", "credit", "is_admin" };
	private final static String TABLENAME = "USERS";

	@Override
	public void create(User u) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.insert(TABLENAME, COLS);
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getLastName());
			stmt.setString(3, u.getFirstName());
			stmt.setString(4, u.getEmail());
			stmt.setString(5, u.getTelephone());
			stmt.setString(6, u.getStreet());
			stmt.setString(7, u.getStreetNb());
			stmt.setString(8, u.getPostcode());
			stmt.setString(9, u.getCity());
			stmt.setString(10, u.getPassword());
			stmt.setInt(11, u.getCredit());
			stmt.setBoolean(12, u.isAdmin());
			stmt.executeUpdate();
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
				int userId = result.getInt(1);
				String username = result.getString(2);
				String lastName = result.getString(3);
				String firstName = result.getString(4);
				String email = result.getString(5);
				String telephone = result.getString(6);
				String streetNb = result.getString(7);
				String street = result.getString(8);
				String postcode = result.getString(9);
				String city = result.getString(10);
				String password = result.getString(11);
				int credit = result.getInt(12);
				boolean isAdmin = result.getBoolean(13);
				User user = new User(userId, username, lastName, firstName, email, telephone, streetNb, street,
						postcode, city, password, credit, isAdmin);
				allUsers.add(user);
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
	public void update(User s) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User s) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public User selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectByUsername(String username) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectByKeyWord(String keyWord) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectByEmail(String email) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectByCity(String city) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectAllAdmins() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
