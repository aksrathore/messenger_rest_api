package org.abhinav.rest.messenger.dao.message;

import java.util.Map;

import org.abhinav.rest.messenger.model.Message;

public interface MessageDAO {

	Map<Long, Message> getMessages(String profileName);

	Message addMessage(Message message);

	Message updateMessage(Message message);

	void deleteMessage(Message message);
}
