package net.leidra.atsistemas.prices.infrastructure.r2dbc.prices;

import lombok.RequiredArgsConstructor;
import net.leidra.atsistemas.prices.domain.products.prices.ProductPriceDetail;
import net.leidra.atsistemas.prices.domain.products.prices.ProductPriceDetailQuery;
import net.leidra.atsistemas.prices.domain.products.prices.ProductPriceDetailRepository;
import net.leidra.atsistemas.prices.infrastructure.r2dbc.prices.model.Prices;
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
