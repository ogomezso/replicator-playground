# App ############################################
FROM adoptopenjdk/openjdk11:ubi  

COPY target/java-consumer-jar-with-dependencies.jar app.jar
COPY config/application-docker.yml application-docker.yml

CMD ["java", "-jar", "app.jar","application-docker.yml"]
