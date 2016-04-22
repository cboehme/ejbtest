package de.dnb.test.web;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import de.dnb.test.ejb.AddBrackets;

@Path("/")
public class AddBracketsResource {

	@EJB
	private AddBrackets addBrackets;

	@GET
	public Response get(final @QueryParam("text") String text) {
		final String textWithBrackets = addBrackets.processText(text);
		return Response.ok(textWithBrackets).build();
	}

}
