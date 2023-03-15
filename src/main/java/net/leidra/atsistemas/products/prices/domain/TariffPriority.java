package net.leidra.atsistemas.products.prices.domain;

import java.util.Objects;

import net.leidra.atsistemas.products.prices.domain.exceptions.IllegalPriorityArgument;

public record TariffPriority(Integer value) {

  public TariffPriority {
    if(Objects.isNull(value)) {
      throw IllegalPriorityArgument.nonNull();
    }
  }
}
