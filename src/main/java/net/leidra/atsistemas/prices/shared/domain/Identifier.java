package net.leidra.atsistemas.prices.shared.domain;

import java.util.Objects;

import net.leidra.atsistemas.prices.shared.domain.exceptions.IllegalIdentifierSupplier;

public class Identifier {
  private final Long value;

  public Identifier(final Long value) {
    if(Objects.isNull(value)) {
      throw IllegalIdentifierSupplier.nonNull();
    }
    this.value = value;
  }

  public Long value() {
    return value;
  }
}
