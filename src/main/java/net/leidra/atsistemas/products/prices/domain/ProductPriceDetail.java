package net.leidra.atsistemas.products.prices.domain;

import net.leidra.atsistemas.products.shared.domain.products.BrandId;
import net.leidra.atsistemas.products.shared.domain.products.ProductId;

public record ProductPriceDetail(TariffId tariffId,
                                 BrandId brandId,
                                 ProductId productId,
                                 TariffDateRange tariffDateRange,
                                 TariffPriority priority,
                                 Price price) {

}
