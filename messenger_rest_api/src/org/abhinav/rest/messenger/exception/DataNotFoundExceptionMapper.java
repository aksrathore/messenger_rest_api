package org.abhinav.rest.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.abhinav.rest.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements
		ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404,
				"https://github.com/aksrathore/messenger_rest_api.git");
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}

}
