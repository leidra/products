package net.leidra.atsistemas.prices.infrastructure.r2dbc.configuration;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;

@Configuration
public class R2dbcConfiguration {
  @Bean
  public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
    final ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
    initializer.setConnectionFactory(connectionFactory);

    return initializer;
  }
}
