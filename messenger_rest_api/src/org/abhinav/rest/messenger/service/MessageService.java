package org.abhinav.rest.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.abhinav.rest.messenger.dao.message.MessageDAO;
import org.abhinav.rest.messenger.dao.message.impl.MessageDAOImpl;
import org.abhinav.rest.messenger.model.ErrorMessage;
import org.abhinav.rest.messenger.model.Message;

public class MessageService {

	private MessageDAO messageDAO = null;

	private MessageDAO getInstance() {
		if (messageDAO == null) {
			messageDAO = new MessageDAOImpl();
		}
		return messageDAO;
	}

	public MessageService() {
	}

	private Map<Long, Message> getMessages(String profileName) {
		return getInstance().getMessages(profileName);
	}

	public List<Message> getAllMessages(String profileName) {
		return new ArrayList<Message>(getMessages(profileName).values());
	}

	public Message addMessage(Message message) {
		message = getInstance().addMessage(message);
		return message;
	}

	public Message getMessage(String profileName, long messageId) {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404,
				"https://github.com/aksrathore/messenger_rest_api.git");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage).build();
		Map<Long, Message> messagesN = getMessages(profileName);
		if (messagesN == null) {
			throw new WebApplicationException(response);
		}
		Message message = messagesN.get(messageId);
		if (message == null) {
			throw new NotFoundException(response);
		}
		return message;
	}

	public Message updateMessage(Message message) {
		return getInstance().updateMessage(message);
	}

	public Message removeMessage(String profileName, long messageId) {
		Message message = getMessage(profileName, messageId);
		getInstance().deleteMessage(message);
		return message;
	}
}
