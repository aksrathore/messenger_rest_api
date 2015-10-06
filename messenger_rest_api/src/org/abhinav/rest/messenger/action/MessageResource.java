package org.abhinav.rest.messenger.action;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.abhinav.rest.messenger.model.Message;
import org.abhinav.rest.messenger.service.MessageService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessages(
			@PathParam("profileName") String profileName) {
				return messageService.getAllMessages(profileName);
	}

	@POST
	public Message addMessage(@PathParam("profileName") String profileName,
			Message message) {
		message.setProfileName(profileName);
		return messageService.addMessage(message);
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("profileName") String profileName,
			@PathParam("messageId") long id) {
		Message message = messageService.getMessage(profileName, id);
		return message;

	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("profileName") String profileName,
			@PathParam("messageId") long id, Message message) {
		message.setId(id);
		message.setProfileName(profileName);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public Message deleteMessage(@PathParam("profileName") String profileName,
			@PathParam("messageId") long id) {
		return messageService.removeMessage(profileName, id);
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
