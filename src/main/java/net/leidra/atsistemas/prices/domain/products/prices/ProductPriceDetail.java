package net.leidra.atsistemas.prices.domain.products.prices;

import net.leidra.atsistemas.prices.shared.domain.Identifier;
import net.leidra.atsistemas.prices.shared.domain.products.BrandId;
import net.leidra.atsistemas.prices.shared.domain.products.ProductId;

public record ProductPriceDetail(Identifier id,
                                 BrandId brandId,
                                 ProductId productId,
                                 TariffId tariffId,
                                 TariffDateRange tariffDateRange,
                                 TariffPriority priority,
                                 Price price) {

}
