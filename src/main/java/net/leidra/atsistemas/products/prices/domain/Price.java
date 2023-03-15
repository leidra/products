package net.leidra.atsistemas.products.prices.domain;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

import net.leidra.atsistemas.products.prices.domain.exceptions.IllegalPriceArgument;

public record Price(Double value, Currency currency) {

  public Price(Double value, Currency currency) {
    nonNegative(value);
    nonNullCurrency(currency);

    this.value = value;
    this.currency = currency;
  }

  private static void nonNullCurrency(Currency currency) {
    if (Objects.isNull(currency)) {
      throw IllegalPriceArgument.nonNullCurrency();
    }
  }

  private static void nonNegative(Double value) {
    if (Objects.isNull(value) || value < 0) {
      throw IllegalPriceArgument.nonNegative();
    }
  }

  public String toPriceView() {
    final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
    final DecimalFormat decimalFormat = new DecimalFormat("#.00", symbols);
    return decimalFormat.format(value) + currency.symbol();
  }
}
