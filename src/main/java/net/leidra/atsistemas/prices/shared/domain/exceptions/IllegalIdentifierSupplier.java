package net.leidra.atsistemas.prices.shared.domain.exceptions;

public final class IllegalIdentifierSupplier extends RuntimeException {

  public IllegalIdentifierSupplier(final String message) {
    super(message);
  }

  public static IllegalIdentifierSupplier nonNull() {
    return new IllegalIdentifierSupplier("The identifier value cannot be null");
  }
}
