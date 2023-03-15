package net.leidra.atsistemas.products.prices.infrastructure.controllers.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDetailResponseConverterTest {

  private ProductDetailResponseConverter productDetailResponseConverter;

  @BeforeEach
  void setUp() {
    productDetailResponseConverter = new ProductDetailResponseConverter();
  }

  @Test
  void should_throw_IllegalArgumentException() {
    assertThrowsExactly(IllegalArgumentException.class, () -> productDetailResponseConverter.convert(null));
  }
}
