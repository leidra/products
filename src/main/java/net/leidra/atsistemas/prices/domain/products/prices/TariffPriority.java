package net.leidra.atsistemas.prices.domain.products.prices;

import java.util.Objects;

import net.leidra.atsistemas.prices.domain.products.prices.exceptions.IllegalPriorityArgument;

public record TariffPriority(Integer value) {

  public TariffPriority {
    if(Objects.isNull(value)) {
      throw IllegalPriorityArgument.nonNull();
    }
  }
}
