package net.leidra.atsistemas.products.prices.domain;

import java.time.Instant;

import net.leidra.atsistemas.products.prices.domain.exceptions.IllegalTariffDateRangeArguments;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TariffDateRangeTest {

  @Test
  void assert_throws_IllegalTariffDateRangeArguments_when_null_values() {
    assertThrowsExactly(IllegalTariffDateRangeArguments.class, () -> new TariffDateRange(null, null));
  }

  @Test
  void assert_throws_IllegalTariffDateRangeArguments_when_invalid_range() {
    assertThrowsExactly(IllegalTariffDateRangeArguments.class, () -> new TariffDateRange(Instant.now().plusSeconds(3600), Instant.now()));
  }
}
