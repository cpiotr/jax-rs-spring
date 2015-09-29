package pl.ciruk.blog.jerseyspring.config;

import org.glassfish.jersey.server.ResourceConfig;
import pl.ciruk.blog.jerseyspring.identity.ComponentIdentity;
import pl.ciruk.blog.jerseyspring.identity.NamedIdentity;

import javax.inject.Named;

@Named
public class Jersey extends ResourceConfig {
	public Jersey() {
		register(ComponentIdentity.class);
		register(NamedIdentity.class);
	}
}
