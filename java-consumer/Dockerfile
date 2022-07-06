ARG BUILD_HOME=/app

# Builder ############################################
FROM maven:3.6.0-jdk-11-slim AS builder

RUN  apt-get update \
  && apt-get install -y wget \
  && rm -rf /var/lib/apt/lists/*

ENV APP_HOME=$BUILD_HOME
WORKDIR $APP_HOME  

COPY src $APP_HOME/src
COPY config $APP_HOME/config
COPY pom.xml $APP_HOME
RUN mvn -f $APP_HOME/pom.xml clean package -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn


# App ############################################
FROM adoptopenjdk/openjdk11:ubi  

COPY --from=builder $APP_HOME/target/java-consumer-jar-with-dependencies.jar app.jar
COPY --from=builder $APP_HOME/config/application-docker.yml application-docker.yml

CMD ["java", "-jar", "app.jar","application-docker.yml"]
