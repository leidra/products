package net.leidra.atsistemas.prices.domain.products.prices;

import reactor.core.publisher.Mono;

public interface ProductPriceDetailRepository {

  Mono<ProductPriceDetail> findByQuery(final ProductPriceDetailQuery query);
}
