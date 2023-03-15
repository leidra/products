package net.leidra.atsistemas.products.prices.domain;

import net.leidra.atsistemas.products.prices.domain.exceptions.IllegalPriceArgument;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

  @Test
  void throws_IllegalPriceArgument_when_negative() {
    assertThrowsExactly(IllegalPriceArgument.class, () -> new Price(-1d, Currency.EUR));
  }

  @Test
  void throws_IllegalPriceArgument_when_null_currency() {
    assertThrowsExactly(IllegalPriceArgument.class, () -> new Price(0d, null));
  }
}
