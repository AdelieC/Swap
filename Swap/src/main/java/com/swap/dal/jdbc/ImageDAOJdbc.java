package com.swap.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.swap.bo.Picture;
import com.swap.dal.DALException;
import com.swap.dal.ImageDAO;

public class ImageDAOJdbc implements ImageDAO {
	private final static String[] COLS = { "id", "auction_id", "name", "extension", "width", "height" };
	private final static String TABLENAME = "PICTURES";

	@Override
	public void create(Picture p) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.insert(TABLENAME, COLS);
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, p.getAuctionId());
			stmt.setString(2, p.getName());
			stmt.setString(3, p.getExtension());
			stmt.setInt(4, p.getWidth());
			stmt.setInt(5, p.getHeight());
			int nbRows = stmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet result = stmt.getGeneratedKeys();
				while (result.next()) {
					p.setId(result.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Picture named " + p.getName() + "couldn't be inserted in dbTable PICTURES", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public List<Picture> read() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Picture picture) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Picture p) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.deleteWhere(TABLENAME, "id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, p.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Picture named " + p.getName() + " couldn't be deleted", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}

	}

	@Override
	public List<Picture> selectByAuctionId(int auctionId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
