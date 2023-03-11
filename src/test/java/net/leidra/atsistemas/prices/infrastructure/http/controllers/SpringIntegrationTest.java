package net.leidra.atsistemas.prices.infrastructure.http.controllers;

import io.cucumber.spring.CucumberContextConfiguration;
import net.leidra.atsistemas.prices.PricesApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest(classes = PricesApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class SpringIntegrationTest {
  @LocalServerPort
  @SuppressWarnings("unused")
  protected Integer port;
  protected WebClient client;


}
