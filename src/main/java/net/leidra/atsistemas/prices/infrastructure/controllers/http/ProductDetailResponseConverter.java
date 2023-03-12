package net.leidra.atsistemas.prices.infrastructure.controllers.http;

import java.util.Objects;

import net.leidra.atsistemas.prices.domain.products.prices.ProductPriceDetail;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class ProductDetailResponseConverter implements Converter<ProductPriceDetail, ProductPriceDetailResponse> {

  @Override
  public ProductPriceDetailResponse convert(final ProductPriceDetail source) {
    isSourceValid(source);
    return new ProductPriceDetailResponse()
      .id(source.id().value())
      .brandId(source.brandId().value())
      .tariffId(source.tariffId().value())
      .tariffDateFrom(source.tariffDateRange().tariffDateFrom())
      .tariffDateTo(source.tariffDateRange().tariffDateTo())
      .priceValue(source.price().value())
      .price(source.price().toPriceView());
  }

  private void isSourceValid(final ProductPriceDetail source) {
    if(Objects.isNull(source)) {
      throw new IllegalArgumentException("Source cannot be null");
    }
  }
}
