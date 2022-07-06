FROM confluentinc/cp-enterprise-replicator:7.0.1

ENV CONNECT_PLUGIN_PATH: "/usr/local/share/kafka/plugins,/usr/share/filestream-connectors"

RUN confluent-hub install --no-prompt confluentinc/kafka-connect-replicator:7.0.1
USER appuser