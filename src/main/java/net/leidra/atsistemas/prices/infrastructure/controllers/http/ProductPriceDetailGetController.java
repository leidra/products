package net.leidra.atsistemas.prices.infrastructure.controllers.http;

import java.time.Instant;

import lombok.RequiredArgsConstructor;
import net.leidra.atsistemas.prices.application.products.prices.ProductPriceDetailRetriever;
import net.leidra.atsistemas.prices.domain.products.prices.ProductPriceDetailQuery;
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
