package net.leidra.atsistemas.prices.shared.domain.exceptions;

public final class IllegalIdentifierArgument extends RuntimeException {

  public IllegalIdentifierArgument(final String message) {
    super(message);
  }

  public static IllegalIdentifierArgument nonNull() {
    return new IllegalIdentifierArgument("The identifier value cannot be null");
  }
}
