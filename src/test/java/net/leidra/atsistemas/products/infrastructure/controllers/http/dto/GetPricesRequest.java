package net.leidra.atsistemas.products.infrastructure.controllers.http.dto;

import java.time.Instant;

public record GetPricesRequest(Long brandId, Long productId, Instant tariffDate) {

}
