package net.leidra.atsistemas.products.prices.infrastructure.controllers.http.dto;

import java.time.Instant;

public record GetPricesRequest(Long brandId, Long productId, Instant tariffDate) {

}
