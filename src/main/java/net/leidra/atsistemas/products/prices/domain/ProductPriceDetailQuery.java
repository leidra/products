package net.leidra.atsistemas.products.prices.domain;

import java.time.Instant;

import net.leidra.atsistemas.products.shared.domain.products.BrandId;
import net.leidra.atsistemas.products.shared.domain.products.ProductId;

public record ProductPriceDetailQuery(BrandId brandId,
                                      ProductId productId,
                                      Instant date) {

  public static ProductPriceDetailQuery create(final Long brandId, final Long productId, final Instant date) {
    return new ProductPriceDetailQuery(new BrandId(brandId), new ProductId(productId), date);
  }
}
