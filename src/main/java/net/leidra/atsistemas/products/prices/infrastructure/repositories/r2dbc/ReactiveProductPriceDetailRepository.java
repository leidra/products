package net.leidra.atsistemas.products.prices.infrastructure.repositories.r2dbc;

import lombok.RequiredArgsConstructor;
import net.leidra.atsistemas.products.prices.domain.ProductPriceDetail;
import net.leidra.atsistemas.products.prices.domain.ProductPriceDetailQuery;
import net.leidra.atsistemas.products.prices.domain.ProductPriceDetailRepository;
import net.leidra.atsistemas.products.prices.infrastructure.repositories.r2dbc.model.Prices;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@SuppressWarnings("unused")
public final class ReactiveProductPriceDetailRepository implements ProductPriceDetailRepository {

  private final SpringR2dbcProductPriceDetailRepository productPriceDetailRepository;

  @Override
  public Mono<ProductPriceDetail> findByQuery(final ProductPriceDetailQuery query) {
    return productPriceDetailRepository.findById(query.brandId().value(), query.productId().value(), query.date())
      .log()
      .map(Prices::toProductPriceDetail)
      .onErrorResume(error -> true, Mono::error);
  }

}
