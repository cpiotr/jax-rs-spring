package pl.ciruk.blog.jerseyspring;

import jersey.repackaged.com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import pl.ciruk.blog.jerseyspring.identity.ComponentIdentity;
import pl.ciruk.blog.jerseyspring.identity.NamedIdentity;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JerseySpringApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=9080")
public class JerseySpringApplicationTests {


	public static final String BASE_URL = "http://localhost:9080";

	private RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void shouldCreateOneInstanceOfResourceAnnotatedWithComponent() throws Exception {
		String url = BASE_URL + ComponentIdentity.PATH;

		Integer firstHashCode = restTemplate.getForEntity(url, Integer.class).getBody();
		Integer secondHashCode = restTemplate.getForEntity(url, Integer.class).getBody();

		assertThat(firstHashCode, is(equalTo(secondHashCode)));
	}

	@Test
	public void shouldCreateManyInstancesOfResourceAnnotatedWithNamed() throws Exception {
		String url = BASE_URL + NamedIdentity.PATH;

		Integer firstHashCode = restTemplate.getForEntity(url, Integer.class).getBody();
		List<Integer> otherHashCodes = Lists.newArrayList(
				restTemplate.getForEntity(url, Integer.class).getBody(),
				restTemplate.getForEntity(url, Integer.class).getBody(),
				restTemplate.getForEntity(url, Integer.class).getBody(),
				restTemplate.getForEntity(url, Integer.class).getBody());


		assertThat(firstHashCode, not(isIn(otherHashCodes)));
	}

	@Test
	public void shouldInjectSomeLoggers() throws Exception {
		String url = BASE_URL + NamedIdentity.PATH + "/numberOfLoggers";

		Integer numberOfLoggers = restTemplate.getForEntity(url, Integer.class).getBody();

		assertThat(numberOfLoggers, is(greaterThan(0)));
	}
}
