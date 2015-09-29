package pl.ciruk.blog.jerseyspring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Spring {
	@Bean
	public Logger logger() {
		return LoggerFactory.getLogger("Common");
	}

	@Bean
	public Logger customLogger() {
		return LoggerFactory.getLogger("Custom");
	}
}
