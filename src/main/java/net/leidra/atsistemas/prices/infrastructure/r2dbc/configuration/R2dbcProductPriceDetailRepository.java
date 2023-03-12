package net.leidra.atsistemas.prices.infrastructure.r2dbc.configuration;

import net.leidra.atsistemas.prices.domain.products.prices.ProductPriceDetail;
import net.leidra.atsistemas.prices.domain.products.prices.ProductPriceDetailQuery;
import net.leidra.atsistemas.prices.domain.products.prices.ProductPriceDetailRepository;
import net.leidra.atsistemas.prices.domain.products.prices.exceptions.ProductPriceNotFound;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@SuppressWarnings("unused")
public final class R2dbcProductPriceDetailRepository implements ProductPriceDetailRepository {

  @Override
  public Mono<ProductPriceDetail> findByQuery(final ProductPriceDetailQuery query) {
    final String message = String.format("Product price not found for query: brand-id > %d, product-id > %d, tariff-date > %s",
      query.brandId().value(), query.productId().value(), query.date());
    return Mono.error(new ProductPriceNotFound(message));
  }
}
