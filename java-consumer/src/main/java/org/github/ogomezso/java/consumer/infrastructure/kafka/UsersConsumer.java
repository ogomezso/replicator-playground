package org.github.ogomezso.java.consumer.infrastructure.kafka;

import java.time.Duration;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.github.ogomezso.java.consumer.config.AppConfig;
import org.github.ogomezso.java.consumer.infrastructure.model.Users;

public class UsersConsumer {

  private final KafkaConsumer<String, Users> plainConsumer;

  public UsersConsumer(AppConfig appConfig) {
    this.plainConsumer = KafkaConfig.createKafkaConsumer(appConfig);
  }

  public void pollMessage() {
    while (true) {
      final ConsumerRecords<String, Users> consumerRecords = plainConsumer.poll(Duration.ofMillis(500));

      consumerRecords.forEach(record -> {
        System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
            record.key(), record.value(),
            record.partition(), record.offset());
      });
    }
  }

}
