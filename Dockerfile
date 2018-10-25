FROM openjdk:8-jdk-alpine

VOLUME /tmp

#ARG JAR_FILE
ARG JAR_FILE=target/todo-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar
COPY ./docker-entrypoint.sh /

ENTRYPOINT ["sh", "/docker-entrypoint.sh"]

# docker build -t cgrings/todo-service:0.0.1-SNAPSHOT .
# docker push cgrings/todo-service:0.0.1-SNAPSHOT