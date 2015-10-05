package org.abhinav.rest.messenger.dao.message.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.abhinav.rest.messenger.dao.base.BaseDAO;
import org.abhinav.rest.messenger.dao.message.MessageDAO;
import org.abhinav.rest.messenger.model.Message;

public class MessageDAOImpl implements MessageDAO {

	private Connection connection = BaseDAO.getConnection();

	private void selectMessage(Message message) {

		ResultSet rs = null;
		String selectQuery = "SELECT * FROM MESSAGES WHERE PROFILENAME=? AND AUTHOR=? AND MESSAGE=?";

		try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
			ps.setString(1, message.getProfileName());
			ps.setString(2, message.getAuthor());
			ps.setString(3, message.getMessage());
			rs = ps.executeQuery();
			while (rs.next()) {
				message.setId(rs.getInt("MESSAGE_ID"));
				message.setCreated(rs.getTimestamp("CREATED"));
				message.setLastUpdated(rs.getTimestamp("LAST_UPDATED"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Message selectMessage(long messageId) {

		ResultSet rs = null;
		Message message = new Message();
		String selectQuery = "SELECT * FROM MESSAGES WHERE MESSAGE_ID=?";

		try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
			ps.setLong(1, messageId);
			rs = ps.executeQuery();

			while (rs.next()) {
				message.setId(messageId);
				message.setAuthor(rs.getString("AUTHOR"));
				message.setCreated(rs.getTimestamp("CREATED"));
				message.setLastUpdated(rs.getTimestamp("LAST_UPDATED"));
				message.setMessage(rs.getString("MESSAGE"));
				message.setProfileName(rs.getString("PROFILENAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return message;
	}

	@Override
	public Map<Long, Message> getMessages(String profileName) {

		ResultSet rs = null;
		Map<Long, Message> messages = new HashMap<Long, Message>();
		String selectMessages = "SELECT * FROM MESSAGES WHERE PROFILENAME=?";

		try (PreparedStatement ps = connection.prepareStatement(selectMessages)) {
			ps.setString(1, profileName);
			rs = ps.executeQuery();
			while (rs.next()) {
				Message message = new Message();
				long messageId = rs.getLong("MESSAGE_ID");
				message.setId(messageId);
				message.setAuthor(rs.getString("AUTHOR"));
				message.setCreated(rs.getTimestamp("CREATED"));
				message.setLastUpdated(rs.getTimestamp("LAST_UPDATED"));
				message.setMessage(rs.getString("MESSAGE"));
				message.setProfileName(rs.getString("PROFILENAME"));
				messages.put(messageId, message);
			}

			return messages;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Message addMessage(Message message) {

		String profileName = message.getProfileName();
		String insertMessage = "INSERT INTO MESSAGES(PROFILENAME,AUTHOR,MESSAGE,LAST_UPDATED) VALUES(?,?,?, NOW())";

		try (PreparedStatement ps = connection.prepareStatement(insertMessage)) {
			ps.setString(1, profileName);
			ps.setString(2, message.getAuthor());
			ps.setString(3, message.getMessage());
			ps.executeUpdate();
			selectMessage(message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public Message updateMessage(Message message) {

		String updateQuery = "UPDATE MESSAGES SET MESSAGE=?,LAST_UPDATED = NOW() WHERE MESSAGE_ID=?";

		try (PreparedStatement ps = connection.prepareStatement(updateQuery)) {

			ps.setString(1, message.getMessage());
			ps.setLong(2, message.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectMessage(message.getId());
	}

	@Override
	public void deleteMessage(Message message) {

		String deleteQuery = "DELETE FROM MESSAGES WHERE MESSAGE_ID=?";

		try (PreparedStatement ps = connection.prepareStatement(deleteQuery)) {
			long id = message.getId();
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
