package net.leidra.atsistemas.prices.shared.infrastructure.controllers.http;

import net.leidra.atsistemas.prices.domain.products.prices.exceptions.ProductPriceNotFound;
import net.leidra.atsistemas.prices.infrastructure.controllers.http.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@SuppressWarnings("unused")
public final class GlobalExceptionHandler {

  @ExceptionHandler(ProductPriceNotFound.class)
  public Mono<ResponseEntity<ErrorResponse>> handle(final ProductPriceNotFound productPriceNotFound) {
    return Mono.just(ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(new ErrorResponse()
        .message(productPriceNotFound.getMessage())));
  }

  @ExceptionHandler(Exception.class)
  public Mono<ResponseEntity<ErrorResponse>> handle(final Exception exception) {
    return Mono.just(ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(new ErrorResponse()
        .message(exception.getMessage())))
      .log();
  }
}
