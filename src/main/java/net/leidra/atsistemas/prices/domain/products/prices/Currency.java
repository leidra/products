package net.leidra.atsistemas.prices.domain.products.prices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import net.leidra.atsistemas.prices.domain.products.prices.exceptions.IllegalCurrencyArgument;

public enum Currency {
  EUR("EUR");

  private final java.util.Currency currency;

  Currency(final String code) {
    try {
      this.currency = java.util.Currency.getInstance(code);
    } catch (IllegalArgumentException e) {
      throw IllegalCurrencyArgument.nonValidCode(code);
    }
  }

  public static Optional<Currency> fromSymbol(String symbol) {
    return Stream.of(Currency.values())
      .filter(currency -> currency.symbol().equals(symbol))
      .findFirst();
  }

  @SuppressWarnings("unused")
  public String symbol() {
    return currency.getSymbol();
  }

  public String code() {
    return currency.getCurrencyCode();
  }
}
