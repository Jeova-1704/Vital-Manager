FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install maven -y

# Adicionando Flyway CLI
RUN apt-get install -y wget
RUN wget -qO- https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/8.0.0/flyway-commandline-8.0.0-linux-x64.tar.gz | tar xvz -C /usr/local
ENV PATH="/usr/local/flyway-8.0.0:${PATH}"

COPY . .

RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/vitalManager-0.0.1-SNAPSHOT.jar app.jar

# Copiando Flyway CLI
COPY --from=build /usr/local/flyway-8.0.0 /usr/local/flyway-8.0.0

ENV PATH="/usr/local/flyway-8.0.0:${PATH}"

# Rodando migrações Flyway antes de iniciar a aplicação
ENTRYPOINT ["sh", "-c", "flyway -url=jdbc:postgresql://localhost:5432/mydb -user=myuser -password=mypassword migrate && java -jar app.jar"]
