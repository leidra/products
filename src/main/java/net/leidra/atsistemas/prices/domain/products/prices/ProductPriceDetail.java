package net.leidra.atsistemas.prices.domain.products.prices;

import net.leidra.atsistemas.prices.shared.domain.products.BrandId;
import net.leidra.atsistemas.prices.shared.domain.products.ProductId;

public record ProductPriceDetail(TariffId tariffId,
                                 BrandId brandId,
                                 ProductId productId,
                                 TariffDateRange tariffDateRange,
                                 TariffPriority priority,
                                 Price price) {

}
