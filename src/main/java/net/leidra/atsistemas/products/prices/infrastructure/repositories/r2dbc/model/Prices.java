package net.leidra.atsistemas.products.prices.infrastructure.repositories.r2dbc.model;

import java.time.Instant;

import net.leidra.atsistemas.products.prices.domain.Currency;
import net.leidra.atsistemas.products.prices.domain.Price;
import net.leidra.atsistemas.products.prices.domain.ProductPriceDetail;
import net.leidra.atsistemas.products.prices.domain.TariffDateRange;
import net.leidra.atsistemas.products.prices.domain.TariffId;
import net.leidra.atsistemas.products.prices.domain.TariffPriority;
import net.leidra.atsistemas.products.shared.domain.products.BrandId;
import net.leidra.atsistemas.products.shared.domain.products.ProductId;
import org.springframework.data.annotation.Id;

public record Prices(@Id Long priceList,
                     Long brandId,
                     Long productId,
                     Instant startDate,
                     Instant endDate,
                     Integer priority,
                     Double price,
                     String curr) {
  public ProductPriceDetail toProductPriceDetail() {
    return new ProductPriceDetail(new TariffId(priceList()),
      new BrandId(brandId()),
      new ProductId(productId()),
      new TariffDateRange(startDate(), endDate()),
      new TariffPriority(priority()),
      new Price(price(), Currency.valueOf(curr()))
      );
  }
}
