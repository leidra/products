package net.leidra.atsistemas.prices.domain.products.prices.exceptions;

public final class IllegalCurrencyArgument extends RuntimeException {

  public IllegalCurrencyArgument(final String message) {
    super(message);
  }

  public static IllegalCurrencyArgument nonValidSymbol(final String symbol) {
    return new IllegalCurrencyArgument("Unexpected symbol '" + symbol + "'");
  }
}
