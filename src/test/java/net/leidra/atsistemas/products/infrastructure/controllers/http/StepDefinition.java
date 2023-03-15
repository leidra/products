package net.leidra.atsistemas.products.infrastructure.controllers.http;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.leidra.atsistemas.products.SpringIntegrationTest;
import net.leidra.atsistemas.products.infrastructure.controllers.http.ProductPriceDetailResponse.CurrencyEnum;
import net.leidra.atsistemas.products.infrastructure.controllers.http.dto.GetPricesRequest;
import net.leidra.atsistemas.products.prices.domain.Currency;
import net.leidra.atsistemas.products.prices.domain.exceptions.ProductPriceNotFound;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinition extends SpringIntegrationTest {

  private final List<ResponseSpec> responses = new ArrayList<>();

  @Before
  public void setup() {
    client = WebClient.create("http://localhost:" + port);
  }

  @DataTableType
  @SuppressWarnings("unused")
  public GetPricesRequest priceRequest(final Map<String, String> requests) {
    return new GetPricesRequest(
      Long.valueOf(requests.get("brand-id")),
      Long.valueOf(requests.get("product-id")),
      Instant.parse(requests.get("tariff-date"))
    );
  }

  @DataTableType
  @SuppressWarnings("unused")
  public ProductPriceDetailResponse priceResponse(final Map<String, String> response) {
    return new ProductPriceDetailResponse()
      .productId(Long.valueOf(response.get("product-id")))
      .brandId(Long.valueOf(response.get("brand-id")))
      .tariffId(Long.valueOf(response.get("tariff-id")))
      .tariffDateFrom(Instant.parse(response.get("tariff-date-from")))
      .tariffDateTo(Instant.parse(response.get("tariff-date-to")))
      .priceValue(Double.valueOf(response.get("price-value")))
      .price(response.get("price"))
      .currency(CurrencyEnum.fromValue(
        Currency.fromSymbol(response.get("price").substring(response.get("price").length() - 1)).map(Currency::code)
          .orElse(Currency.EUR.code())));
  }

  public String messages(final Map<String, String> messages) {
    return messages.get("message");
  }

  @When("the client makes a GET request to \\/api\\/ecommerce\\/brands\\/<brand-id>\\/products\\/<product-id>\\/prices?date=<tariff-date> providing:")
  public void the_client_makes_GET_request(final List<GetPricesRequest> requests) {
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
  public void the_response_is_valid(final Integer statusCode, final List<ProductPriceDetailResponse> expectedResponses) {
    final AtomicReference<Integer> currentResponse = new AtomicReference<>(0);
    responses.forEach(response -> {
      final Mono<ProductPriceDetailResponse> responseObject = response
        .onStatus(status -> status.value() != statusCode,
          clientResponse -> Mono.error(RuntimeException::new))
        .bodyToMono(ProductPriceDetailResponse.class);

      assertThat(responseObject).isNotNull();
      final ProductPriceDetailResponse expectedResponse = expectedResponses.get(currentResponse.getAndSet(currentResponse.get() + 1));
      final ProductPriceDetailResponse productPriceDetailResponse = responseObject.block();
      assertResponseIsValid(expectedResponse, productPriceDetailResponse);
    });
  }

  private static void assertResponseIsValid(final ProductPriceDetailResponse expectedResponse,
    final ProductPriceDetailResponse productPriceDetailResponse) {
    assertThat(productPriceDetailResponse).isNotNull();
    assertThat(productPriceDetailResponse.getBrandId()).isEqualTo(expectedResponse.getBrandId());
    assertThat(productPriceDetailResponse.getProductId()).isEqualTo(expectedResponse.getProductId());
    assertThat(productPriceDetailResponse.getTariffId()).isEqualTo(expectedResponse.getTariffId());
    assertThat(productPriceDetailResponse.getTariffDateFrom()).isEqualTo(expectedResponse.getTariffDateFrom());
    assertThat(productPriceDetailResponse.getTariffDateTo()).isEqualTo(expectedResponse.getTariffDateTo());
    assertThat(productPriceDetailResponse.getTariffDateTo()).isEqualTo(expectedResponse.getTariffDateTo());
    assertThat(productPriceDetailResponse.getPrice()).isEqualTo(expectedResponse.getPrice());
    assertThat(productPriceDetailResponse.getPriceValue()).isEqualTo(expectedResponse.getPriceValue());
  }

  @Then("the price list is not found and the response contains:")
  public void the_response_is_not_found(final List<String> messages) {
    final AtomicReference<Integer> currentResponse = new AtomicReference<>(0);
    responses.forEach(response -> {
      try {
        response
          .onStatus(HttpStatusCode::isError, errorResponse ->
            errorResponse.bodyToMono(ErrorResponse.class)
              .map(error -> new ProductPriceNotFound(error.getMessage())))
          .bodyToMono(ProductPriceDetailResponse.class)
          .block();
      } catch (final Exception e) {
        assertThat(e.getCause().getMessage()).isEqualTo(messages.get(currentResponse.getAndSet(currentResponse.get() + 1)));
      }
    });
  }
}
