FROM openjdk:17-jdk-slim

WORKDIR /app

# COPY target/*.jar app.jar
#luego cambiar * a nombre del proyecto descargado
COPY target/T2-DAWII-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8085

CMD [ "java", "-jar", "app.jar" ]