package net.leidra.atsistemas.prices.infrastructure.http.controllers;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.springframework.web.reactive.function.client.WebClient.builder;

public class StepDefinition extends SpringIntegrationTest {

  private final List<ResponseSpec> responses = new ArrayList<>();

  @Before
  public void setup() {
    client = builder()
        .baseUrl("http://localhost:" + port)
        .build();
  }

  @DataTableType
  @SuppressWarnings("unused")
  public GetPricesRequest priceRequest(final Map<String, String> requests) {
    return new GetPricesRequest(Long.valueOf(requests.get("brand-id")),
        Long.valueOf(requests.get("product-id")),
        ZonedDateTime.parse(requests.get("tariff-date")));
  }

  @DataTableType
  @SuppressWarnings("unused")
  public GetPricesResponse priceResponse(final Map<String, String> response) {
    return new GetPricesResponse(Long.valueOf(response.get("brand-id")),
        Long.valueOf(response.get("product-id")),
        Long.valueOf(response.get("tariff-id")),
        ZonedDateTime.parse(response.get("tariff-date-from")),
        ZonedDateTime.parse(response.get("tariff-date-to")),
        Double.valueOf(response.get("price")));
  }

  @When("the client makes a GET request to \\/api\\/ecommerce\\/brands\\/<brand-id>\\/products\\/<product-id>\\/prices?date=<tariff-date> providing:")
  public void the_client_makes_GET_request(List<GetPricesRequest> requests) {
    for (final GetPricesRequest request : requests) {
      responses.add(client.get()
          .uri(
              "/api/ecommerce/brands/{brand-id}/products/{product-id}/prices?date={tariff-date}",
              request.brandId(),
              request.productId(),
              request.tariffDate())
          .retrieve());
    }
  }

  @SuppressWarnings("unused")
  @Then("the client receives status code of {int} and the response contains:")
  public void the_client_receives_status_code(Integer statusCode, List<GetPricesResponse> expectedResponses) {
    responses.forEach(response -> {
      final Mono<Object> responseObject = response
          .onStatus(status -> status.value() != statusCode,
              clientResponse -> Mono.error(RuntimeException::new))
          .bodyToMono(Object.class);

      assertThat(responseObject).isNotNull();
      assertThatExceptionOfType(RuntimeException.class).isThrownBy(responseObject::block);
    });
  }
}
