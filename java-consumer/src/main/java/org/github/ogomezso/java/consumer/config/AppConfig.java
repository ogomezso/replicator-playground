package org.github.ogomezso.java.consumer.config;

import lombok.Data;

@Data
public class AppConfig {
   private int appPort;
   private String bootstrapServers;
   private String clientId;
   private String groupId;
   private String topic;
   private String schemaRegistryUrl;
}
