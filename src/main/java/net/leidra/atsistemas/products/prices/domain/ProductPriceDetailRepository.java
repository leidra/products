package net.leidra.atsistemas.products.prices.domain;

import reactor.core.publisher.Mono;

public interface ProductPriceDetailRepository {

  Mono<ProductPriceDetail> findByQuery(final ProductPriceDetailQuery query);
}
