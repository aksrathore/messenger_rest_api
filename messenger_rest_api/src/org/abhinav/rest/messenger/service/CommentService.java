package org.abhinav.rest.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.abhinav.rest.messenger.dao.comment.CommentDAO;
import org.abhinav.rest.messenger.dao.comment.impl.CommentDAOImpl;
import org.abhinav.rest.messenger.model.Comment;
import org.abhinav.rest.messenger.model.ErrorMessage;

public class CommentService {

	private CommentDAO commentDAO = null;

	private CommentDAO getInstance() {
		if (commentDAO == null) {
			commentDAO = new CommentDAOImpl();
		}
		return commentDAO;
	}

	private Map<Long, Comment> getComments(long messageId) {
		return getInstance().getComments(messageId);
	}

	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = getComments(messageId);
		return new ArrayList<Comment>(comments.values());
	}

	public Comment getComment(long messageId, long commentId) {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404,
				"https://github.com/aksrathore/messenger_rest_api.git");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage).build();

		Map<Long, Comment> commentsN = getComments(messageId);
		if (commentsN == null) {
			throw new WebApplicationException(response);
		}
		Comment comment = commentsN.get(commentId);
		if (comment == null) {
			throw new NotFoundException(response);
		}
		return comment;
	}

	public Comment addComment(long messageId, Comment comment) {
		comment.setMessageId(messageId);
		return getInstance().addComment(comment);
	}

	public Comment updateComment(Comment comment) {
		return getInstance().updateComment(comment);
	}

	public Comment removeComment(long messageId, long commentId) {
		Comment comment = getComment(messageId, commentId);
		getInstance().deleteComment(comment);
		return comment;
	}

}
