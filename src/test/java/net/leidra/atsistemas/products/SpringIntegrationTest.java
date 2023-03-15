package net.leidra.atsistemas.products;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

@ActiveProfiles("test")
@SpringBootTest(classes = PricesApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class SpringIntegrationTest {
  @LocalServerPort
  @SuppressWarnings("unused")
  protected Integer port;
  protected WebClient client;


}
