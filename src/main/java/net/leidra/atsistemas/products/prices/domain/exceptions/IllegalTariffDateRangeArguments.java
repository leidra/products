package net.leidra.atsistemas.products.prices.domain.exceptions;

public final class IllegalTariffDateRangeArguments extends RuntimeException {

  public IllegalTariffDateRangeArguments(final String message) {
    super(message);
  }

  public static IllegalTariffDateRangeArguments nonNull() {
    return new IllegalTariffDateRangeArguments("All arguments must be supplied");
  }

  public static IllegalTariffDateRangeArguments invalid() {
    return new IllegalTariffDateRangeArguments("Tariff date to value must be after tariff date from");
  }
}
