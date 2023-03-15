package net.leidra.atsistemas.products.prices.domain.exceptions;

import java.time.Instant;

import net.leidra.atsistemas.products.shared.domain.products.BrandId;
import net.leidra.atsistemas.products.shared.domain.products.ProductId;

public final class ProductPriceNotFound extends Exception {

  public ProductPriceNotFound(final String message) {
    super(message);
  }

  public static ProductPriceNotFound byCriteria(BrandId brandId, ProductId productId, Instant date) {
    final String message = String.format("Cannot find price list searching by: brandId => %d, productId => %d, application date => %s", brandId.value(), productId.value(), date);
    return new ProductPriceNotFound(message);
  }
}
