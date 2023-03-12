package net.leidra.atsistemas.prices.domain.products.prices;

import java.util.Objects;

import net.leidra.atsistemas.prices.domain.products.prices.exceptions.IllegalPriceArgument;

public record Price(Double value, Currency currency) {

  public Price {
    if(Objects.isNull(value) || value < 0) {
      throw IllegalPriceArgument.nonNegative();
    }
    if(Objects.isNull(currency)) {
      throw IllegalPriceArgument.nonNullCurrency();
    }
  }

  public String toPriceView() {
    return value + currency.symbol();
  }
}
