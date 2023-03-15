package net.leidra.atsistemas.products.prices.domain;

import net.leidra.atsistemas.products.prices.domain.exceptions.IllegalPriorityArgument;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TariffPriorityTest {

  @Test
  void should_throw_IllegalPriorityArgument() {
    assertThrowsExactly(IllegalPriorityArgument.class, () -> new TariffPriority(null));
  }
}
