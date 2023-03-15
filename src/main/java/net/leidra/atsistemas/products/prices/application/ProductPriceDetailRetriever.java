package net.leidra.atsistemas.products.prices.application;

import lombok.RequiredArgsConstructor;
import net.leidra.atsistemas.products.prices.domain.ProductPriceDetail;
import net.leidra.atsistemas.products.prices.domain.ProductPriceDetailQuery;
import net.leidra.atsistemas.products.prices.domain.ProductPriceDetailRepository;
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
