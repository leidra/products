package net.leidra.atsistemas.prices.domain.products.prices;

import java.util.Arrays;

import net.leidra.atsistemas.prices.domain.products.prices.exceptions.IllegalCurrencyArgument;

public enum Currency {
  EUR("€");
  private final String symbol;

  Currency(final String symbol) {
    this.symbol = symbol;
  }

  @SuppressWarnings("unused")
  public static Currency fromSymbol(final String symbol) {
    return Arrays.stream(values())
      .filter(currency -> currency.symbol.equals(symbol))
      .findFirst().orElseThrow(() -> IllegalCurrencyArgument.nonValidSymbol(symbol));
  }

  public String symbol() {
    return symbol;
  }
}
