package net.leidra.atsistemas.prices.infrastructure.r2dbc.prices;

import java.time.Instant;

import net.leidra.atsistemas.prices.infrastructure.r2dbc.prices.model.Prices;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SpringR2dbcProductPriceDetailRepository extends ReactiveCrudRepository<Prices, Long> {

  @Query("""
    SELECT brand_id,start_date,end_date,price_list,product_id,priority,price,curr
    FROM prices
    WHERE brand_id = $1 and product_id = $2 and $3 BETWEEN start_date and end_date
    ORDER BY priority desc
    LIMIT 1""")
  Mono<Prices> findById(Long brandId, Long productId, Instant date);
}
