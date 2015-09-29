package pl.ciruk.blog.jerseyspring.identity;

import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path(NamedIdentity.PATH)
@Named
public class NamedIdentity {

	public static final String PATH = "/identity/named";

	private List<Logger> loggers;

	@Inject
	public NamedIdentity(List<Logger> loggers) {
		this.loggers = loggers;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		return Response.ok(hashCode()).build();
	}

	@GET
	@Path("/numberOfLoggers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response numberOfInjectedLoggers() {
		return Response.ok(loggers.stream().count()).build();
	}
}
