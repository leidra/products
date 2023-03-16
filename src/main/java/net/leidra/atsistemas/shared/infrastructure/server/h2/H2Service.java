package net.leidra.atsistemas.shared.infrastructure.server.h2;

import org.h2.tools.Server;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
@SuppressWarnings("unused")
public class H2Service {

  private Server webServer;

  private Server tcpServer;

  @EventListener(ContextRefreshedEvent.class)
  public void start() throws java.sql.SQLException {
    this.webServer = Server.createWebServer("-webPort", "8082", "-tcpAllowOthers", "-web").start();
    this.tcpServer = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-tcp").start();
  }

  @EventListener(ContextClosedEvent.class)
  public void stop() {
    this.tcpServer.stop();
    this.webServer.stop();
  }

}
