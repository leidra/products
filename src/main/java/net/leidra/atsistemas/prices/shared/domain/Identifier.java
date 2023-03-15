package net.leidra.atsistemas.prices.shared.domain;

import java.util.Objects;

import net.leidra.atsistemas.prices.shared.domain.exceptions.IllegalIdentifierArgument;

public class Identifier {
  private final Long value;

  public Identifier(final Long value) {
    if(Objects.isNull(value)) {
      throw IllegalIdentifierArgument.nonNull();
    }
    this.value = value;
  }

  public Long value() {
    return value;
  }
}
