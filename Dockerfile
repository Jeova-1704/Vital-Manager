FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install maven -y

RUN apt-get install -y wget
RUN wget -qO- https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/8.0.0/flyway-commandline-8.0.0-linux-x64.tar.gz | tar xvz -C /usr/local
ENV PATH="/usr/local/flyway-8.0.0:${PATH}"

COPY . .

RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/vitalManager-0.0.1-SNAPSHOT.jar app.jar

COPY --from=build /usr/local/flyway-8.0.0 /usr/local/flyway-8.0.0

ENV PATH="/usr/local/flyway-8.0.0:${PATH}"

ENTRYPOINT ["sh", "-c", "flyway -url=$BANCO_NAME -user=$USERNAME_BANCO -password=$PASSWORD_BANCO migrate && java -jar app.jar"]
