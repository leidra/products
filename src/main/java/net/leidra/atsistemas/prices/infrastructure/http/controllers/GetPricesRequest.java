package net.leidra.atsistemas.prices.infrastructure.http.controllers;

import java.time.ZonedDateTime;

public record GetPricesRequest(Long brandId, Long productId, ZonedDateTime tariffDate) {

}