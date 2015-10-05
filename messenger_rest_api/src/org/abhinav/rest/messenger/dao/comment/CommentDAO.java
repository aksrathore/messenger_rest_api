package org.abhinav.rest.messenger.dao.comment;

import java.util.Map;

import org.abhinav.rest.messenger.model.Comment;

public interface CommentDAO {

	Map<Long, Comment> getComments(long messageId);

	Comment addComment(Comment comment);

	Comment updateComment(Comment comment);

	void deleteComment(Comment comment);
}
