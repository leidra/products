package net.leidra.atsistemas.products.prices.infrastructure.controllers.http;

import java.util.Objects;

import net.leidra.atsistemas.products.infrastructure.controllers.http.ProductPriceDetailResponse;
import net.leidra.atsistemas.products.prices.domain.ProductPriceDetail;
import net.leidra.atsistemas.products.infrastructure.controllers.http.ProductPriceDetailResponse.CurrencyEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public final class ProductDetailResponseConverter implements Converter<ProductPriceDetail, ProductPriceDetailResponse> {

  @NonNull
  @Override
  public ProductPriceDetailResponse convert(final ProductPriceDetail source) {
    isSourceValid(source);
    return new ProductPriceDetailResponse()
      .productId(source.productId().value())
      .brandId(source.brandId().value())
      .tariffId(source.tariffId().value())
      .tariffDateFrom(source.tariffDateRange().tariffDateFrom())
      .tariffDateTo(source.tariffDateRange().tariffDateTo())
      .priceValue(source.price().value())
      .price(source.price().toPriceView())
      .currency(CurrencyEnum.valueOf(source.price().currency().code()));
  }

  private void isSourceValid(final ProductPriceDetail source) {
    if(Objects.isNull(source)) {
      throw new IllegalArgumentException("Source cannot be null");
    }
  }
}
