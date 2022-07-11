package org.github.ogomezso.java.consumer.infrastructure.kafka;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.CLIENT_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.github.ogomezso.java.consumer.config.AppConfig;
import org.github.ogomezso.java.consumer.infrastructure.model.Users;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KafkaConfig {

  public static final String DESERIALIZATION_STRING_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
  public static final String DESERIALIZATION_AVRO_DESERIALIZER = "io.confluent.kafka.serializers.KafkaAvroDeserializer";
  public static final String INTERCEPTOR_CLASSES = "io.confluent.connect.replicator.offsets.ConsumerTimestampsInterceptor";
  public static final String SCHEMA_REGISTRY_URL = "schema.registry.url";

  static KafkaConsumer<String, Users> createKafkaConsumer(AppConfig appConfig) {

    Properties props = new Properties();
    props.put(BOOTSTRAP_SERVERS_CONFIG, appConfig.getBootstrapServers());
    props.put(CLIENT_ID_CONFIG, appConfig.getClientId());
    props.put(GROUP_ID_CONFIG, appConfig.getGroupId());
    props.put(KEY_DESERIALIZER_CLASS_CONFIG, DESERIALIZATION_STRING_DESERIALIZER);
    props.put(VALUE_DESERIALIZER_CLASS_CONFIG, DESERIALIZATION_AVRO_DESERIALIZER);
    props.put(AUTO_OFFSET_RESET_CONFIG, appConfig.getAutoOffsetReset());
    props.put(INTERCEPTOR_CLASSES_CONFIG, INTERCEPTOR_CLASSES);
    props.put(SCHEMA_REGISTRY_URL, appConfig.getSchemaRegistryUrl());

    final KafkaConsumer<String, Users> consumer = new KafkaConsumer<>(props);
    consumer.subscribe(Collections.singletonList(appConfig.getTopic()));

    return consumer;
  }

}
