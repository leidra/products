package net.leidra.atsistemas.prices.infrastructure.r2dbc.prices.model;

import java.time.Instant;

import net.leidra.atsistemas.prices.domain.products.prices.Currency;
import net.leidra.atsistemas.prices.domain.products.prices.Price;
import net.leidra.atsistemas.prices.domain.products.prices.ProductPriceDetail;
import net.leidra.atsistemas.prices.domain.products.prices.TariffDateRange;
import net.leidra.atsistemas.prices.domain.products.prices.TariffId;
import net.leidra.atsistemas.prices.domain.products.prices.TariffPriority;
import net.leidra.atsistemas.prices.shared.domain.products.BrandId;
import net.leidra.atsistemas.prices.shared.domain.products.ProductId;
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
