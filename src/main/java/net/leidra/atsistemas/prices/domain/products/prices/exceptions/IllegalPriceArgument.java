package net.leidra.atsistemas.prices.domain.products.prices.exceptions;

public final class IllegalPriceArgument extends RuntimeException {

  public IllegalPriceArgument(final String message) {
    super(message);
  }

  public static IllegalPriceArgument nonNegative() {
    return new IllegalPriceArgument("Price must be a positive number");
  }

  public static IllegalPriceArgument nonNullCurrency() {
    return new IllegalPriceArgument("Currency is mandatory");
  }
}
