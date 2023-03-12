package net.leidra.atsistemas.prices.application.products.prices;

import lombok.RequiredArgsConstructor;
import net.leidra.atsistemas.prices.domain.products.prices.ProductPriceDetail;
import net.leidra.atsistemas.prices.domain.products.prices.ProductPriceDetailQuery;
import net.leidra.atsistemas.prices.domain.products.prices.ProductPriceDetailRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public final class ProductPriceDetailRetriever {

  private final ProductPriceDetailRepository productPriceDetailRepository;

  public Mono<ProductPriceDetail> retrieveProductPriceDetail(final ProductPriceDetailQuery query) {
    return productPriceDetailRepository.findByQuery(query);
  }
}
