package com.swap.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.BOException;
import com.swap.bo.Picture;
import com.swap.dal.DALException;
import com.swap.dal.PictureDAO;

public class ImageDAOJdbc implements PictureDAO {
	private final static String[] COLS = { "id", "auction_id", "name", "extension", "width", "height" };
	private final static String TABLENAME = "PICTURES";

	@Override
	public void create(Picture p) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.insert(TABLENAME, COLS);
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery, Statement.RETURN_GENERATED_KEYS);
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
		List<Picture> pictures = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = DBUtils.selectBy(TABLENAME, "auction_id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, auctionId);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String extension = result.getString("extension");
				int width = result.getInt("width");
				int height = result.getInt("height");
				pictures.add(new Picture(id, auctionId, name, extension, width, height));
			}
		} catch (SQLException | BOException e) {
			throw new DALException("Failed to fetch pictures for auction with id = " + auctionId, e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return pictures;
	}

	@Override
	public void deleteAllByAuctionId(int auctionId) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.deleteWhere(TABLENAME, "auction_id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, auctionId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Pictures from auction with id = " + auctionId + " couldn't be deleted", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}

	}

}
