package org.abhinav.rest.messenger.dao.comment.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.abhinav.rest.messenger.dao.base.BaseDAO;
import org.abhinav.rest.messenger.dao.comment.CommentDAO;
import org.abhinav.rest.messenger.model.Comment;

public class CommentDAOImpl implements CommentDAO {

	private Connection connection = BaseDAO.getConnection();

	private void selectComment(Comment comment) {
		ResultSet rs = null;
		String selectComment = "SELECT * FROM COMMENTS WHERE MESSAGE_ID_FK=? AND AUTHOR=? AND MESSAGE=?";
		try (PreparedStatement ps = connection.prepareStatement(selectComment)) {
			ps.setLong(1, comment.getMessageId());
			ps.setString(2, comment.getAuthor());
			ps.setString(3, comment.getMessage());
			rs = ps.executeQuery();
			while (rs.next()) {
				comment.setId(rs.getInt("COMMENT_ID"));
				comment.setCreated(rs.getTimestamp("CREATED"));
				comment.setLastUpdated(rs.getTimestamp("LAST_UPDATED"));
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

	private Comment selectComment(long commentId) {
		ResultSet rs = null;
		Comment comment = new Comment();
		String selectQuery = "SELECT * FROM COMMENTS WHERE COMMENT_ID=?";
		try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
			ps.setLong(1, commentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				comment.setId(rs.getLong("COMMENT_ID"));
				comment.setCreated(rs.getTimestamp("CREATED"));
				comment.setLastUpdated(rs.getTimestamp("LAST_UPDATED"));
				comment.setAuthor(rs.getString("AUTHOR"));
				comment.setMessage(rs.getString("MESSAGE"));
				comment.setMessageId(rs.getLong("MESSAGE_ID_FK"));
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
		return comment;
	}

	@Override
	public Map<Long, Comment> getComments(long messageId) {

		ResultSet rs = null;
		Map<Long, Comment> comments = new HashMap<Long, Comment>();
		String selectComents = "SELECT * FROM COMMENTS WHERE MESSAGE_ID_FK=?";
		try (PreparedStatement ps = connection.prepareStatement(selectComents)) {
			ps.setLong(1, messageId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				long commentId = rs.getLong("COMMENT_ID");
				comment.setId(commentId);
				comment.setAuthor(rs.getString("AUTHOR"));
				comment.setCreated(rs.getTimestamp("CREATED"));
				comment.setLastUpdated(rs.getTimestamp("LAST_UPDATED"));
				comment.setMessage(rs.getString("MESSAGE"));
				comment.setMessageId(rs.getLong("MESSAGE_ID_FK"));
				comments.put(commentId, comment);
			}

			return comments;

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
	public Comment addComment(Comment comment) {
		String insertMessage = "INSERT INTO COMMENTS(AUTHOR,MESSAGE,MESSAGE_ID_FK,LAST_UPDATED) VALUES(?,?,?,NOW())";
		try (PreparedStatement ps = connection.prepareStatement(insertMessage)) {
			ps.setString(1, comment.getAuthor());
			ps.setString(2, comment.getMessage());
			ps.setLong(3, comment.getMessageId());
			ps.executeUpdate();
			selectComment(comment);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
	}

	@Override
	public Comment updateComment(Comment comment) {
		String updateComment = "UPDATE COMMENTS SET MESSAGE=?,LAST_UPDATED = NOW() WHERE COMMENT_ID=?";
		try (PreparedStatement ps = connection.prepareStatement(updateComment)) {
			ps.setString(1, comment.getMessage());
			ps.setLong(2, comment.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectComment(comment.getId());
	}

	@Override
	public void deleteComment(Comment comment) {
		String deleteQuery = "DELETE FROM COMMENTS WHERE COMMENT_ID=?";
		try (PreparedStatement ps = connection.prepareStatement(deleteQuery)) {
			long id = comment.getId();
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
