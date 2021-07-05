package com.swap.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.swap.bo.Notification;
import com.swap.dal.DALException;
import com.swap.dal.NotificationDAO;

public class NotificationDAOJdbc implements NotificationDAO {
	private final static String[] COLS = { "id", "recipient_id", "sender_id", "type", "content", "is_read",
			"auction_id" };
	// timestamp only needs to be fetched so I don't need it in COLS
	private final static String TABLENAME = "NOTIFICATIONS";

	@Override
	public void create(Notification n) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.insert(TABLENAME, COLS);
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, n.getRecipientId());
			stmt.setInt(2, n.getSenderId());
			stmt.setString(3, n.getType());
			stmt.setString(4, n.getContent());
			stmt.setBoolean(5, n.isRead());
			stmt.setInt(6, n.getAuctionId());
			int nbRows = stmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet result = stmt.getGeneratedKeys();
				while (result.next()) {
					n.setId(result.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new DALException("Notification of type " + n.getType() + " for user with id " + n.getRecipientId()
					+ " couldn't be created", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public List<Notification> read() throws DALException {
		List<Notification> notifications = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQLQuery = DBUtils.selectAll(TABLENAME);
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				int recipientId = result.getInt("recipient_id");
				int senderId = result.getInt("sender_id");
				String type = result.getString("type");
				String content = result.getString("content");
				boolean isRead = result.getBoolean("is_read");
				int auctionId = result.getInt("auction_id");
				java.sql.Timestamp timestamp = result.getTimestamp("timestamp");
				notifications
						.add(new Notification(id, recipientId, senderId, type, content, isRead, auctionId, timestamp));
			}
		} catch (SQLException e) {
			throw new DALException("Read failed - couldn't retrieve list of all notifications", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return notifications;
	}

	@Override
	public void update(Notification n) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.updateWhere(TABLENAME, "id", COLS);
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, n.getRecipientId());
			stmt.setInt(2, n.getSenderId());
			stmt.setString(3, n.getType());
			stmt.setString(4, n.getContent());
			stmt.setBoolean(5, n.isRead());
			stmt.setInt(1, n.getAuctionId());
			stmt.setInt(6, n.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(
					"Notification of type " + n.getType() + " number " + n.getId() + " couldn't be updated", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public Notification selectById(int id) throws DALException {
		Notification notification = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQLQuery = DBUtils.selectBy(TABLENAME, "id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			if (result.next()) {
				int recipientId = result.getInt("recipient_id");
				int senderId = result.getInt("sender_id");
				String type = result.getString("type");
				String content = result.getString("content");
				Boolean isRead = result.getBoolean("is_read");
				int auctionId = result.getInt("auction_id");
				java.sql.Timestamp timestamp = result.getTimestamp("timestamp");
				notification = new Notification(id, recipientId, senderId, type, content, isRead, auctionId, timestamp);
			}
		} catch (SQLException e) {
			throw new DALException("Notification with id " + id + " couldn't be fetched", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return notification;
	}

	@Override
	public List<Notification> selectByRecipient(int recipientId) throws DALException {
		List<Notification> notifications = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQLQuery = DBUtils.selectBy(TABLENAME, "recipient_id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, recipientId);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				int senderId = result.getInt("sender_id");
				String type = result.getString("type");
				String content = result.getString("content");
				Boolean isRead = result.getBoolean("is_read");
				int auctionId = result.getInt("auction_id");
				java.sql.Timestamp timestamp = result.getTimestamp("timestamp");
				notifications
						.add(new Notification(id, recipientId, senderId, type, content, isRead, auctionId, timestamp));
			}
		} catch (SQLException e) {
			throw new DALException("Notifications for user with id " + recipientId + " couldn't be fetched", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return notifications;
	}

	@Override
	public List<Notification> selectBySender(int senderId) throws DALException {
		List<Notification> notifications = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQLQuery = DBUtils.selectBy(TABLENAME, "sender_id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, senderId);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				int recipientId = result.getInt("recipient_id");
				String type = result.getString("type");
				String content = result.getString("content");
				Boolean isRead = result.getBoolean("is_read");
				int auctionId = result.getInt("auction_id");
				java.sql.Timestamp timestamp = result.getTimestamp("timestamp");
				notifications
						.add(new Notification(id, recipientId, senderId, type, content, isRead, auctionId, timestamp));
			}
		} catch (SQLException e) {
			throw new DALException("Notifications sent by user with id " + senderId + " couldn't be fetched", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return notifications;
	}

	@Override
	public List<Notification> selectByTypeAndRecipient(String type, int recipientId) throws DALException {
		List<Notification> notifications = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQLQuery = DBUtils.selectByTwoCols(TABLENAME, "type", "recipient_id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setString(1, type);
			stmt.setInt(2, recipientId);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				int senderId = result.getInt("sender_id");
				String content = result.getString("content");
				Boolean isRead = result.getBoolean("is_read");
				int auctionId = result.getInt("auction_id");
				java.sql.Timestamp timestamp = result.getTimestamp("timestamp");
				notifications
						.add(new Notification(id, recipientId, senderId, type, content, isRead, auctionId, timestamp));
			}
		} catch (SQLException e) {
			throw new DALException(
					"Notifications of type " + type + " for user with id " + recipientId + " couldn't be fetched", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return notifications;
	}

	@Override
	public List<Notification> selectByTypeAndSender(String type, int senderId) throws DALException {
		List<Notification> notifications = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQLQuery = DBUtils.selectByTwoCols(TABLENAME, "type", "sender_id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setString(1, type);
			stmt.setInt(2, senderId);
			result = stmt.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				int recipientId = result.getInt("recipient_id");
				String content = result.getString("content");
				Boolean isRead = result.getBoolean("is_read");
				int auctionId = result.getInt("auction_id");
				java.sql.Timestamp timestamp = result.getTimestamp("timestamp");
				notifications
						.add(new Notification(id, recipientId, senderId, type, content, isRead, auctionId, timestamp));
			}
		} catch (SQLException e) {
			throw new DALException(
					"Notifications of type " + type + " from user with id " + senderId + " couldn't be fetched", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
		return notifications;
	}

	@Override
	public void delete(int id) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.deleteWhere(TABLENAME, "id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Notification number " + id + " couldn't be deleted", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public void delete(Notification n) throws DALException {
		this.delete(n.getId());
	}

	@Override
	public void deleteAllByRecipient(int recipientId) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.deleteWhere(TABLENAME, "recipient_id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, recipientId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Notifications of user number " + recipientId + " couldn't be deleted", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public void deleteBySenderId(int userId) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.deleteWhere(TABLENAME, "sender_id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Notification from user with id " + userId + " couldn't be deleted", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public void deleteByRecipientId(int userId) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.deleteWhere(TABLENAME, "recipient_id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Notification for user with id " + userId + " couldn't be deleted", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}
	}

	@Override
	public void updateIsRead(Notification n) throws DALException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String SQLQuery = DBUtils.updateWhere(TABLENAME, "is_read", "id");
		try {
			conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setBoolean(1, true);
			stmt.setInt(2, n.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Notification number " + n.getId() + " couldn't be marked as read", e);
		} finally {
			DBUtils.closePrepStmt(stmt);
			DBUtils.closeConnection(conn);
		}

	}
}
