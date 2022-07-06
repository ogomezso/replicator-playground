package org.github.ogomezso.java.consumer.infrastructure.kafka;

import org.github.ogomezso.java.consumer.config.AppConfig;

public class UsersService implements UsersAdapter {

  private final UsersConsumer consumer;

  public UsersService(AppConfig appConfig) {
    this.consumer = new UsersConsumer(appConfig);
  }

  @Override
  public void pollMessages() {
    consumer.pollMessage();
  }

}
