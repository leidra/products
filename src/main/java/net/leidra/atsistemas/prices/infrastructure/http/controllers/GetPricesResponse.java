package net.leidra.atsistemas.prices.infrastructure.http.controllers;

import java.time.ZonedDateTime;

public record GetPricesResponse(Long brandId,
                                Long productId,
                                Long tariffId,
                                ZonedDateTime tariffDateFrom,
                                ZonedDateTime tariffDateTo,
                                Double price) {

}
