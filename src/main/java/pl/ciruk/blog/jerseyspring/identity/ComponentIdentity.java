package pl.ciruk.blog.jerseyspring.identity;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path(ComponentIdentity.PATH)
@Component
public class ComponentIdentity {

	public static final String PATH = "/identity/component";

	private List<Logger> loggers;

	@Autowired
	public ComponentIdentity(List<Logger> loggers) {
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
