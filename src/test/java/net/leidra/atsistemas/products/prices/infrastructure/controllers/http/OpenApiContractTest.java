package net.leidra.atsistemas.products.prices.infrastructure.controllers.http;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.springweb.client.OpenApiValidationClientHttpRequestInterceptor;
import com.atlassian.oai.validator.springweb.client.OpenApiValidationClientHttpRequestInterceptor.OpenApiValidationException;
import net.leidra.atsistemas.products.SpringIntegrationTest;
import net.leidra.atsistemas.products.infrastructure.controllers.http.ProductPriceDetailResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import static com.atlassian.oai.validator.OpenApiInteractionValidator.createForSpecificationUrl;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class OpenApiContractTest extends SpringIntegrationTest {

  private RestTemplate restTemplate;

  @BeforeEach
  void setUp() {
    final String specUrl = Paths.get("src", "main", "resources", "public", "openapi.yml").toString();
    final OpenApiInteractionValidator openApiInteractionValidator = createForSpecificationUrl(specUrl).build();
    final OpenApiValidationClientHttpRequestInterceptor requestInterceptor = new OpenApiValidationClientHttpRequestInterceptor(
      openApiInteractionValidator);
    final DefaultUriBuilderFactory handler = new DefaultUriBuilderFactory("http://localhost:" + port);
    restTemplate = new RestTemplateBuilder()
      .interceptors(List.of(requestInterceptor))
      .uriTemplateHandler(handler)
      .build();
  }

  @Test
  void should_validate_openapi() {
    final ResponseEntity<ProductPriceDetailResponse> response = restTemplate.exchange(
      UriComponentsBuilder.fromUriString("/api/ecommerce/brands/{brand-id}/products/{product-id}/prices?date={tariff-date}")
        .uriVariables(Map.of("brand-id", "1",
          "product-id", "35455",
          "tariff-date", "2020-06-14T10:00:00Z"))
        .build(false).toString(), HttpMethod.GET, null, ProductPriceDetailResponse.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void should_not_validate_openapi() {
    assertThrows(OpenApiValidationException.class, () ->
      restTemplate.exchange(
        UriComponentsBuilder.fromUriString("/api/ecommerce/brands/{brand-id}/products/{product-id}/prices?date={tariff-date}")
          .uriVariables(Map.of("brand-id", "1",
            "product-id", "INVALID",
            "tariff-date", "2020-06-14T10:00:00Z"))
          .build(false).toString(), HttpMethod.GET, null, ProductPriceDetailResponse.class)
    );
  }
}
