package com.swap.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.PickUpPoint;
import com.swap.dal.DALException;
import com.swap.dal.PickUpPointDAO;

public class PickUpPointDAOJdbc implements PickUpPointDAO {
	private static String[] columns = { "id", "auction_id", "street", "postcode", "city" };
	private static String tableName = "PICK_UP_POINTS";

	@Override
	public void create(PickUpPoint s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.insert(tableName, columns);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, s.getAuctionId());
			stmt.setString(2, s.getStreet());
			stmt.setString(3, s.getPostcode());
			stmt.setString(4, s.getCity());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Pick-up Point --" + s.getStreet() + "-- insertion failed", e);
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
	public List<PickUpPoint> read() throws DALException {
		List<PickUpPoint> list = new ArrayList<PickUpPoint>();
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectAll(tableName);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				int auctionId = result.getInt("auction_id");
				String street = result.getString("street");
				String postcode = result.getString("postcode");
				String city = result.getString("city");
				PickUpPoint pu = new PickUpPoint(id, auctionId, street, postcode, city);
				list.add(pu);
			}
		} catch (SQLException e) {
			throw new DALException("READ - Pick-up Points List failed ");
		}
		return list;
	}

	@Override
	public void update(PickUpPoint s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.updateWhere(tableName, "id", columns);
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, s.getAuctionId());
			stmt.setString(2, s.getStreet());
			stmt.setString(3, s.getPostcode());
			stmt.setString(4, s.getCity());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Pick-up Point --" + s.getStreet() + "-- update failed", e);
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
	public void delete(PickUpPoint s) throws DALException {
		Connection cn = null;
		PreparedStatement stmt = null;
		String query = DBUtils.deleteWhere(tableName, "id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, s.getId());
			stmt.setInt(2, s.getAuctionId());
			stmt.setString(3, s.getStreet());
			stmt.setString(3, s.getPostcode());
			stmt.setString(3, s.getCity());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Pick-up Point --" + s.getStreet() + "-- deletion failed", e);
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
	public PickUpPoint selectById(int id) throws DALException {
		PickUpPoint pu = null;
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			int auctionId = result.getInt("auction_id");
			String street = result.getString("street");
			String postcode = result.getString("postcode");
			String city = result.getString("city");
			pu = new PickUpPoint(id, auctionId, street, postcode, city);
		} catch (SQLException e) {
			throw new DALException("READ - Pick-up Point by ID failed ");
		}
		return pu;
	}

	@Override
	public PickUpPoint selectByAuctionId(int auctionId) throws DALException {
		PickUpPoint pup = null;
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "auction_id");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, auctionId);
			result = stmt.executeQuery();
			int id = result.getInt("id");
			String street = result.getString("street");
			String postcode = result.getString("postcode");
			String city = result.getString("city");
			pup = new PickUpPoint(id, auctionId, street, postcode, city);
		} catch (SQLException e) {
			throw new DALException("READ - Pick-up Point by AUCTION ID failed ");
		}
		return pup;
	}

	@Override
	public List<PickUpPoint> selectByStreet(String street) throws DALException {
		List<PickUpPoint> list = new ArrayList<PickUpPoint>();
		PickUpPoint pu = null;
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "street");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, street);
			result = stmt.executeQuery();
			int id = result.getInt("id");
			int auctionId = result.getInt("auction_id");
			String postcode = result.getString("postcode");
			String city = result.getString("city");
			pu = new PickUpPoint(id, auctionId, street, postcode, city);
			list.add(pu);
		} catch (SQLException e) {
			throw new DALException("READ - Pick-up Point by STREET failed ");
		}
		return list;
	}

	@Override
	public List<PickUpPoint> selectByPostcode(String postcode) throws DALException {
		List<PickUpPoint> list = new ArrayList<PickUpPoint>();
		PickUpPoint pu = null;
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "postcode");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, postcode);
			result = stmt.executeQuery();
			int id = result.getInt("id");
			int auctionId = result.getInt("auction_id");
			String street = result.getString("street");
			String city = result.getString("city");
			pu = new PickUpPoint(id, auctionId, street, postcode, city);
			list.add(pu);
		} catch (SQLException e) {
			throw new DALException("READ - Pick-up Point by POSTCODE failed ");
		}
		return list;
	}

	@Override
	public List<PickUpPoint> selectByCity(String city) throws DALException {
		List<PickUpPoint> list = new ArrayList<PickUpPoint>();
		PickUpPoint pu = null;
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(tableName, "city");
		try {
			cn = ConnectionProvider.getConnection();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, city);
			result = stmt.executeQuery();
			int id = result.getInt("id");
			int auctionId = result.getInt("auction_id");
			String street = result.getString("street");
			String postcode = result.getString("postcode");
			pu = new PickUpPoint(id, auctionId, street, postcode, city);
			list.add(pu);
		} catch (SQLException e) {
			throw new DALException("READ - Pick-up Point by POSTCODE failed ");
		}
		return list;
	}

}
