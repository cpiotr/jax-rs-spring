package pl.ciruk.blog.jerseyspring.identity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import pl.ciruk.blog.jerseyspring.JerseySpringApplication;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JerseySpringApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=9080")
public class NamedIdentityTest {

	public static final String BASE_URL = "http://localhost:9080";

	private RestTemplate restTemplate = new TestRestTemplate();

	@Inject
	List<Logger> loggers;

	@Test
	public void shouldCreateManyInstancesOfResource() throws Exception {
		String url = urlToGetResourceIdentity();

		Integer firstHashCode = restTemplate.getForEntity(url, Integer.class).getBody();
		List<Integer> otherHashCodes = Stream.generate(() -> restTemplate.getForEntity(url, Integer.class))
				.mapToInt(ResponseEntity::getBody)
				.limit(5)
				.boxed()
				.collect(toList());

		assertThat(firstHashCode, not(isIn(otherHashCodes)));
	}

	@Test
	public void shouldInjectSomeLoggersIntoResource() throws Exception {
		String url = urlToGetNumberOfLoggers();

		Integer numberOfLoggers = restTemplate.getForEntity(url, Integer.class).getBody();

		assertThat(numberOfLoggers, is(equalTo(loggers.size())));
	}

	private String urlToGetResourceIdentity() {
		return BASE_URL + NamedIdentity.PATH;
	}

	private String urlToGetNumberOfLoggers() {
		return BASE_URL + NamedIdentity.PATH + "/numberOfLoggers";
	}
}