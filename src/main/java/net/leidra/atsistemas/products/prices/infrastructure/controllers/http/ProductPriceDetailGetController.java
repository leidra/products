package net.leidra.atsistemas.products.prices.infrastructure.controllers.http;

import java.time.Instant;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import net.leidra.atsistemas.products.infrastructure.controllers.http.PricesApi;
import net.leidra.atsistemas.products.infrastructure.controllers.http.ProductPriceDetailResponse;
import net.leidra.atsistemas.products.prices.application.ProductPriceDetailRetriever;
import net.leidra.atsistemas.products.prices.domain.ProductPriceDetailQuery;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public final class ProductPriceDetailGetController implements PricesApi {
  private final ProductPriceDetailRetriever productPriceDetailRetriever;
  private final ProductDetailResponseConverter responseConverter;

  @Override
  public Mono<ProductPriceDetailResponse> getProductPrice(final Long brandId, final Long productId, final Instant date,
    final ServerWebExchange exchange) {
    final ProductPriceDetailQuery query = ProductPriceDetailQuery.create(brandId, productId, date);
    return productPriceDetailRetriever.retrieveProductPriceDetail(query)
      .flatMap(productPriceDetail -> Mono.just(responseConverter.convert(productPriceDetail)));
  }
}
