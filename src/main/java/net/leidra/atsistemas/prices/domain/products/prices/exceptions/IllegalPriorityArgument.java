package net.leidra.atsistemas.prices.domain.products.prices.exceptions;

public final class IllegalPriorityArgument extends RuntimeException {

  public IllegalPriorityArgument(final String message) {
    super(message);
  }

  public static IllegalPriorityArgument nonNull() {
    return new IllegalPriorityArgument("Product price priority cannot be null");
  }
}
