package org.github.ogomezso.java.consumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class AppConfig {
   private int appPort;
   private String bootstrapServers;
   private String clientId;
   private String groupId;
   private String topic;
   private final ObjectMapper objectMapper = new ObjectMapper();
}
