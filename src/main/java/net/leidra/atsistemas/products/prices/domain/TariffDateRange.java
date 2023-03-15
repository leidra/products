package net.leidra.atsistemas.products.prices.domain;

import java.time.Instant;
import java.util.Objects;

import net.leidra.atsistemas.products.prices.domain.exceptions.IllegalTariffDateRangeArguments;

public record TariffDateRange(Instant tariffDateFrom,
                             Instant tariffDateTo) {

  public TariffDateRange {
    checkRequiredArguments(tariffDateFrom, tariffDateTo);
    validateRange(tariffDateFrom, tariffDateTo);
  }

  private void validateRange(final Instant tariffDateFrom, final Instant tariffDateTo) {
    if(!isRangeValid(tariffDateFrom, tariffDateTo)) {
      throw IllegalTariffDateRangeArguments.invalid();
    }
  }

  private boolean isRangeValid(final Instant tariffDateFrom, final Instant tariffDateTo) {
    return !tariffDateFrom.equals(tariffDateTo) && tariffDateTo.isAfter(tariffDateFrom);
  }

  private void checkRequiredArguments(final Instant tariffDateFrom, final Instant tariffDateTo) {
    if(Objects.isNull(tariffDateFrom) || Objects.isNull(tariffDateTo)) {
      throw IllegalTariffDateRangeArguments.nonNull();
    }
  }
}
